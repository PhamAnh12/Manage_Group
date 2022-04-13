package com.vti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.repository.IUserRepository;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository repository;
	@Override
	public UserDTO findByUserName(String username) {
		// TODO Auto-generated method stub
		User user =  repository.findByUserName(username);
		UserDTO userDto = new UserDTO(user.getId(),user.getEmail(),user.getUserName(),user.getPassword(),user.getFisrtName(),user.getLastName(),user.getPhone());
		return userDto;
	}
	@Override
	public void createUser(User user) {
		repository.save(user);
		
	}
	@Override
	public List <UserDTO> getAllUsers() {
		List <UserDTO> dtos =  new ArrayList<>();
		List <User> users = repository.findAll();
		for(User user : users) {
			UserDTO userDto = new UserDTO(user.getId(),user.getEmail(),user.getUserName(),user.getPassword(),user.getFisrtName(),user.getLastName(),user.getPhone());
            dtos.add(userDto);
		}
		
		
		return dtos;
		
	}


}
