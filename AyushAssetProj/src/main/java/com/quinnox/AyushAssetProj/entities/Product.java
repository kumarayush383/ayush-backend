package com.quinnox.AyushAssetProj.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Product")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	private String description;
	private String productname;
	private double price;
	private int quantity;
	@Lob 
	private byte[] productimage;
	
	
	public Product() {
		super();
	}

	public Product(int productid, String description, String productname, double price, int quantity,
			byte[] productimage) {
		super();
		this.productid = productid;
		this.description = description;
		this.productname = productname;
		this.price = price;
		this.quantity = quantity;
		this.productimage = productimage;
	}
	
	




}
