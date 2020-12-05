package com.quinnox.AyushAssetProj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quinnox.AyushAssetProj.entities.Address;
import com.quinnox.AyushAssetProj.entities.User;
@Repository
@Transactional
public interface AddressDao extends JpaRepository<Address, Long> {
	public Address findByUser(User user);
}