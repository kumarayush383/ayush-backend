package com.quinnox.AyushAssetProj.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PlaceOrder")
public class PlaceOrder {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String email, orderStatus;
	private Date orderDate;
	private double totalCost;
	
	
	
	public PlaceOrder(int orderId, String email, String orderStatus, Date orderDate, double totalCost) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
	}

	public PlaceOrder() {
		super();
	}
	
	

}
