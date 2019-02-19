package business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseRequestLineItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="purchaseRequestID")
	private PurchaseRequest purchaseRequest;
//	private int purchaseRequestID;
	
	@ManyToOne
	@JoinColumn(name="productID")
	private Product product;
//	private int productID;
	
	private int quantity;
	
	public PurchaseRequestLineItem() {
		super();
	}

	public PurchaseRequestLineItem(int id, PurchaseRequest purchaseRequest, Product product, int quantity) {
		super();
		this.id = id;
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return "PurchaseLineItem [id=" + id + ", purchaseRequest=" + purchaseRequest + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
	
}
