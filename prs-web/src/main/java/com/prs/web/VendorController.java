package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/vendors")
public class VendorController {
	
	@Autowired
	VendorRepository vendorRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		
		try {
			jr = JsonResponse.getInstance(vendorRepo.findAll());
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
			Optional<Vendor> vendor = vendorRepo.findById(id);
			if(vendor.isPresent()) {
				jr = JsonResponse.getInstance(vendor);
			}
			else {
				jr = JsonResponse.getInstance("No vendor for ID: " + id);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("")
	public JsonResponse vendorPagination(@PathVariable int start, @PathVariable int limit) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(vendorRepo.findAll(PageRequest.of(start, limit)));
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addVendor(@RequestBody Vendor v) {
		JsonResponse jr = null;
		
		if(vendorRepo.existsById(v.getId())) {
			jr = JsonResponse.getInstance("Oops! This vendor already exists.");
		}
		else {
			jr = saveVendor(v);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse updateVendor(@RequestBody Vendor v) {
		return saveVendor(v);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deleteVendor(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(v.isPresent()) {
				vendorRepo.deleteById(id);
				jr = JsonResponse.getInstance(v);
			}
			else {
				jr = JsonResponse.getInstance("Delete unsuccessful. No vendor for ID: " + id);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse saveVendor(Vendor v) {
		JsonResponse jr = null;
		try {
			vendorRepo.save(v);
			jr = JsonResponse.getInstance(v);
		}
		catch(DataIntegrityViolationException cve) {
			jr = JsonResponse.getInstance(cve.getMessage());
		}
		return jr;
	}
}
