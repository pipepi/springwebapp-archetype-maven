package com.azt.base.dao;

import com.azt.base.model.User;

public interface UserDao {
	public int add(User user);
	public void delete(int id);
	public void update(User user);
	public User query(int id);
}
