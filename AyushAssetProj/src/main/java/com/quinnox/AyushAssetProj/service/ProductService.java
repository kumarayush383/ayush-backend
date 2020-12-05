package com.quinnox.AyushAssetProj.service;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.Product;

public interface ProductService {
//
//public	Product findByProductid(int productid);
//
//public	void deleteByProductid(int productid);

public List<Product> findAll();

public Product save(Product prod);

	
public Product findByProductid(int productid);
public	void deleteByProductid(int productid);


}
