package com.quinnox.AyushAssetProj.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



import lombok.Data;
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String email;
	private String username;
	private String password;
	private String usertype;
	private int age;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Address address;

	public User() {
		super();
	}

	public User(int userid, String email, String username, String password, int age, Address address) {
		super();
		this.userid = userid;
		this.email = email;
		this.username = username;
		this.password = password;
		this.age = age;
		this.address = address;
	}
	
	
}
