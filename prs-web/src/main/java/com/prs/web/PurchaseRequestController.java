package com.prs.web;

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

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;

@RestController
@RequestMapping("/purchaserequest")
public class PurchaseRequestController {
	
	@Autowired
	private PurchaseRequestRepository prRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(prRepo.findAll());
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequest> pr = prRepo.findById(id);
			if(pr.isPresent()) {
				jr = JsonResponse.getInstance(pr);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse savePurchaseRequest(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			prRepo.save(pr);
			jr = JsonResponse.getInstance(pr);
		}
		catch(DataIntegrityViolationException cve) {
			jr = JsonResponse.getInstance(cve);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addPurchaseRequest(@RequestBody PurchaseRequest pr) {
		return savePurchaseRequest(pr);
	}
	
	@PutMapping("/")
	public JsonResponse updatePurchaseRequest(@RequestBody PurchaseRequest pr) {
		return savePurchaseRequest(pr);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deletePurchaseRequest(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequest> pr = prRepo.findById(id);
			if(pr.isPresent()) {
				prRepo.deleteById(id);
				jr = JsonResponse.getInstance(pr);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}