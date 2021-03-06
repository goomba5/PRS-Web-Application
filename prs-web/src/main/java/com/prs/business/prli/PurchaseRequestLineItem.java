package com.prs.business.prli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.prs.business.product.Product;
import com.prs.business.purchaserequest.PurchaseRequest;

@Entity
public class PurchaseRequestLineItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="purchaseRequestID")
	private PurchaseRequest purchaseRequest;
	
	@ManyToOne
	@JoinColumn(name="productID")
	private Product product;
	
	private int quantity;
	
	@Column(name="isActive")
	private boolean active;
	
	public PurchaseRequestLineItem() {
		super();
	}

	public PurchaseRequestLineItem(int id, PurchaseRequest purchaseRequest, 
			Product product, int quantity, boolean active) {
		super();
		this.id = id;
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}

	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}

	public Product getProduct() {
		return product;
	}

	public void setProductID(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "PurchaseLineItem [id=" + id + ", purchaseRequest=" + purchaseRequest + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
	
}
