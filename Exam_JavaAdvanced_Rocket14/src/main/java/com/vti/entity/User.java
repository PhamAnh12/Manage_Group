package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="`User`",catalog = "TestingSystem" )
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "email", length = 50, nullable = false, unique = true, updatable = false)
	private String email;

	@Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
	private String userName;

	@Column(name = "`password`", length = 800, nullable = false, updatable = false)
	private String password;

	@Column(name = "firstName", length = 50, nullable = false)
	private String fisrtName;

	@Column(name = "lastName", length = 50, nullable = false)
	private String lastName;

	@Column(name = "phone", length = 15)
	private String phone;
//	@OneToMany(mappedBy = "user")
//	private List<Group> listGroups;
    public User() {
		// TODO Auto-generated constructor stub
	}
    
    
    
	public User(short id, String email, String userName, String password, String fisrtName, String lastName,
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
	
//	public List<Group> getListGroups() {
//		return listGroups;
//	}
//	public void setListGroups(List<Group> listGroups) {
//		this.listGroups = listGroups;
//	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", fisrtName=" + fisrtName + ", lastName=" + lastName + ", phone=" + phone + "]";
	}
    
    
	

}
