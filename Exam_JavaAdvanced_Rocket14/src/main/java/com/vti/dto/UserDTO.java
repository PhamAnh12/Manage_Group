package com.vti.dto;

import com.vti.entity.User;

public class UserDTO {
	private short id;
	private String email;
	private String userName;
	private String password;
	private String fisrtName;
	private String lastName;
	private String phone;

	

	public UserDTO(short id, String email, String userName, String password, String fisrtName, String lastName,
			String phone) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.phone = phone;
	}

	public short getId() {
		return id;
	}



	public void setId(short id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFisrtName() {
		return fisrtName;
	}
	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", fisrtName=" + fisrtName + ", lastName=" + lastName + ", phone=" + phone + "]";
	}



	


	public User toEntity() {
		return new User(id,email,userName,password,fisrtName,lastName,phone); 
	}
}
