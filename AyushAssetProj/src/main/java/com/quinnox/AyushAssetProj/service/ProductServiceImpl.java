package com.quinnox.AyushAssetProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.AyushAssetProj.dao.ProductDao;
import com.quinnox.AyushAssetProj.entities.Product;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao prodDao;
	

	@Override
	public Product findByProductid(int productid) {
		
		return prodDao.findByProductid(productid);
	}

	

	@Override
	public List<Product> findAll() {
	
		return prodDao.findAll();
	}

	@Override
	public Product save(Product prod) {
		return prodDao.save(prod);
		

	
	

}


	@Override
	public void deleteByProductid(int productid) {
		
		
	}
}
