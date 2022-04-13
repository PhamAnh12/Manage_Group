package com.vti.service;

import java.util.List;

import com.vti.dto.GroupDTO;
import com.vti.entity.Group;
import com.vti.entity.Parameters;

public interface IGroupService {
	public List<GroupDTO> getAllGroups(Parameters parameters);
	public GroupDTO getGroupByID(short groupId);

//	public GroupDTO getGroupByName(String groupName);

	public void createGroup(Group group);

	public void updateGroupNewName(short id, String newGroupName);

	public void updateGroup(Group group);

	public void deleteGroup(short groupId);

	public boolean isGroupExistsByID(short groupId);

	public boolean isGroupExistsByName(String groupName);
	public void deleteMultiGroups(List<Short> ids);
}
