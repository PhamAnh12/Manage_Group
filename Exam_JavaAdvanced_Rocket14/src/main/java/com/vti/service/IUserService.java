package com.vti.service;

import java.util.List;

import com.vti.dto.UserDTO;
import com.vti.entity.User;

public interface IUserService {
	public UserDTO  findByUserName(String username);
	public List <UserDTO> getAllUsers();
	public void createUser( User user);
	
}
