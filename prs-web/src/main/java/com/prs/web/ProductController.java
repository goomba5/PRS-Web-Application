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

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		
		try {
			jr = JsonResponse.getInstance(productRepo.findAll());
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
			Optional<Product> product = productRepo.findById(id);
			if(product.isPresent()) {
				jr = JsonResponse.getInstance(product);
			}
			else {
				jr = JsonResponse.getInstance("No product for ID: " + id);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("")
	public JsonResponse productPagination(@PathVariable int start, @PathVariable int limit) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(productRepo.findAll(PageRequest.of(start, limit)));
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addProduct(@RequestBody Product p) {
		JsonResponse jr = null;
		if(productRepo.existsById(p.getId())) {
			jr = JsonResponse.getInstance("Oops! This product already exists.");
		}
		else {
			jr = saveProduct(p);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse updateProduct(@RequestBody Product p) {
		return saveProduct(p);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deleteProduct(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Product> product = productRepo.findById(id);
			if(product.isPresent()) {
				productRepo.deleteById(id);
				jr = JsonResponse.getInstance(product);
			}
			else {
				jr = JsonResponse.getInstance("Error deleting product for ID: " + id);
			}
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	private JsonResponse saveProduct(@RequestBody Product p) {
		JsonResponse jr = null;
		try {
			productRepo.save(p);
			jr = JsonResponse.getInstance(p);
		}
		catch(DataIntegrityViolationException cve) {
			jr = JsonResponse.getInstance(cve);
		}
		return jr;
	}
}
