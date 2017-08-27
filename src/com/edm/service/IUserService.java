package com.edm.service;

import com.edm.vo.User;

public interface IUserService {
	User login(User user);

	boolean register(User user);

	boolean query(String username);
}
