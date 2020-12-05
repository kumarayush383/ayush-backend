package com.quinnox.AyushAssetProj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quinnox.AyushAssetProj.entities.Product;
@Repository
@Transactional
public interface ProductDao  extends JpaRepository<Product, Long> {

	
	public Product findByProductid(int productid);
	public	void deleteByProductid(int productid);
}
