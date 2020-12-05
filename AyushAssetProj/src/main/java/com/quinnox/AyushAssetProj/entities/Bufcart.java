package com.quinnox.AyushAssetProj.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Bufcart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bufcartId;

	@Column(nullable = true)
	private int orderId;
	private String email;

	private Date dateAdded;

	private int quantity;
	private double price;
	private int productId;

	private String productname;
	
	
	
	
}
