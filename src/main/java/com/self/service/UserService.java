package com.self.service;

import com.self.domain.User;


public interface UserService {
	public User login(String username,String password);
}
