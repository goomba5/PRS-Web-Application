package com.prs.business.prli;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.purchaserequest.PurchaseRequest;

public interface PRLIRepository extends CrudRepository<PurchaseRequestLineItem, Integer> {
	
	// method retrieves list of all prlis for the pr argument
	List<PurchaseRequestLineItem> findByPurchaseRequest(PurchaseRequest pr);
}
