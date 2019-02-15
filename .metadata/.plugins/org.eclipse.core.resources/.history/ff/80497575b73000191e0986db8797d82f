package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;

@RestController
@RequestMapping(path="/product")
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
	
	private JsonResponse saveProduct(@RequestBody Product p) {
		JsonResponse jr = null;
		try {
			productRepo.save(p);
			jr = JsonResponse.getInstance(p);
		}
		catch(Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse addProduct(@RequestBody Product p) {
		return saveProduct(p);
	}
	
	@PutMapping("/")
	public JsonResponse updateProduct(@RequestBody Product p) {
		return saveProduct(p);
	}
}
