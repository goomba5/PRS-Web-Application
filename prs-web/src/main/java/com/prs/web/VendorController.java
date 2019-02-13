package com.prs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.vendor.VendorRepository;

@RestController
@RequestMapping(path="/vendor")
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
}
