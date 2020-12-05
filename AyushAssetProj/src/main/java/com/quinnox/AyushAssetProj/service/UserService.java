package com.quinnox.AyushAssetProj.service;

import com.quinnox.AyushAssetProj.entities.User;

public interface UserService {

	
public	User findByEmailAndPasswordAndUsertype(String email, String password, String usertype);

public User save(User user);


}
