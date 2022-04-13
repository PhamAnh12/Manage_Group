package com.vti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vti.dto.GroupDTO;
import com.vti.entity.Group;
import com.vti.entity.Parameters;
import com.vti.service.IGroupService;

@RestController
@RequestMapping(value = "api/v1/groups")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class GroupController {
	
	@Autowired
	private IGroupService service;
	
	@GetMapping()
	public ResponseEntity<?> getAllGroups(Parameters parameters) {
		List<GroupDTO> entities = service.getAllGroups(parameters);
		return new ResponseEntity<List<GroupDTO>>(entities, HttpStatus.OK);
		
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getGroupByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<GroupDTO>(service.getGroupByID(id), HttpStatus.OK);
	}
//	@GetMapping(value = "/name")
//	public ResponseEntity<?> getGrouptByName(@RequestParam("name") String name) {
//		Group group = service.getGroupByName(name);
//		return new ResponseEntity<Group>(group, HttpStatus.OK);
//	}
	@PostMapping()
	public ResponseEntity<?> createGroup(@RequestBody Group group) {
		service.createGroup(group);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updatenewGroupName(@PathVariable(name = "id") short id, String newGroupName ) {
		
		service.updateGroupNewName(id, newGroupName);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<?> update(@PathVariable(name = "id") short id, @RequestBody GroupDTO dto) {
//		// em chua set id nen no thanh them moi group
//		dto.setId(id);
//		Group group = dto.convertToGroup();
//		service.updateGroup(group);
//		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
//	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable(name = "id") short id) {
		service.deleteGroup(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteMulti")
	public ResponseEntity<?> deleteMultiGroups(@RequestBody List<Short> ids) {
		service.deleteMultiGroups(ids);
		return new ResponseEntity<String>("delete success !", HttpStatus.OK);
	}
	
}
