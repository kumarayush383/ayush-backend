package com.quinnox.AyushAssetProj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quinnox.AyushAssetProj.entities.Bufcart;


@Repository
@Transactional
public interface CartDao extends JpaRepository<Bufcart, Long>{

	
	
	public	List<Bufcart>findByEmail(String email);

	public	Bufcart findByBufcartIdAndEmail(int bufcartId, String email);

	public	void deleteByBufcartIdAndEmail(int bufcartId, String email);

	public	List<Bufcart> findAllByEmail(String email);

	public	List<Bufcart> findAllByOrderId(int orderId);
}
