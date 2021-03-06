package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.prli.PRLIRepository;
import com.prs.business.prli.PurchaseRequestLineItem;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;


@RestController
@RequestMapping("/purchase-request-line-items")
public class PRLIController {
	
	@Autowired
	PRLIRepository prliRepo;
	
	@Autowired
	PurchaseRequestRepository prRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(prliRepo.findAll());
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse getPRLI(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> prli = prliRepo.findById(id);
			if(prli.isPresent()) {
				jr = JsonResponse.getInstance(prli);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addPRLI(@RequestBody PurchaseRequestLineItem prli) {
		return savePRLI(prli);
	}
	
	@PutMapping("/")
	public JsonResponse updatePRLI(@RequestBody PurchaseRequestLineItem prli) {
		return savePRLI(prli);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deletePRLI(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> prli = prliRepo.findById(id);
			if(prli.isPresent()) {
				prliRepo.deleteById(id);
				jr = JsonResponse.getInstance(prli);
				
				PurchaseRequestLineItem prl = prli.get();
				recalculateTotal(prl);
			}
			else {
				jr = JsonResponse.getInstance("Delete unsuccessful. PRLI with ID " + id + " does not exist.");
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	
	private void recalculateTotal(PurchaseRequestLineItem prli) {
		// get the purchase request
		PurchaseRequest pr = prli.getPurchaseRequest();
		
		
		// create list of prlis from the given purchase request
		List<PurchaseRequestLineItem> prliList = prliRepo.findByPurchaseRequest(pr);
		double total = 0.0;
		
		// loop through all prlis to recalculate the total
		for(PurchaseRequestLineItem prlis: prliList) {
			
			// subtotal will be price * quantity
			double subtotal = prlis.getProduct().getPrice() * prlis.getQuantity();
			
			total += subtotal;
		}
		
		// set the new total for purchase request
		pr.setTotal(total);
		
		// save the total
		prRepo.save(pr);
	}
	
	private JsonResponse savePRLI(PurchaseRequestLineItem prli) {
		JsonResponse jr = null;
		try {
			prliRepo.save(prli);
			jr = JsonResponse.getInstance(prli);
			
			recalculateTotal(prli);
		}
		catch(DataIntegrityViolationException cve) {
			jr = JsonResponse.getInstance(cve);
		}
		return jr;
	}
}
