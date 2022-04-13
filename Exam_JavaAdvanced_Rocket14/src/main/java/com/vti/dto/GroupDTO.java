package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Group;
import com.vti.entity.User;

public class GroupDTO {
	private short id;

	private String name;

	private int member;
    @JsonFormat(pattern="dd-MM-yyy")
	private Date createDate;

	private User user;

	public GroupDTO() {

	}

	public GroupDTO(short groupId, String groupName, int member, Date createDate, User user) {
		super();
		this.id = groupId;
		this.name = groupName;
		this.member = member;
		this.createDate = createDate;
		this.user = user;
	}

	
	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Group convertToGroup() {
		return new Group(id, name, member, createDate, user);
	}

	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", name=" + name + ", member=" + member + ", createDate=" + createDate + ", user="
				+ user + "]";
	}

}
