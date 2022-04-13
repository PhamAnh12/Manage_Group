package com.vti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.dto.GroupDTO;
import com.vti.entity.Group;
import com.vti.entity.Parameters;
import com.vti.entity.User;
import com.vti.repository.IGroupRepository;

@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupRepository repository;

	@Override
	public List<GroupDTO> getAllGroups(Parameters parameters) {
		List<GroupDTO> listGroupDTOs = new ArrayList<>();
		List<Group> listGroup = null;
		Pageable pageable = null;
		Specification<Group> where = null;
		Sort sort = null;

		if (parameters.getField() != null && parameters.getField().isEmpty()) {
			if (parameters.getTypeSort() != null && parameters.getTypeSort().equals("DESC")) {
				sort = Sort.by(parameters.getTypeSort()).descending();
			} else {
				sort = Sort.by(parameters.getTypeSort()).ascending();
			}
		}

		if (parameters.getSearch() != null && !parameters.getSearch().isEmpty()) {
			where = searchByGroupName(parameters.getSearch());
		}

		if (parameters.getMaxId() > 0) {
			if (where == null) {
				where = lessThanEqualMaxId((short) parameters.getMaxId());
			} else {
				where = where.and(lessThanEqualMaxId((short) parameters.getMaxId()));
			}
		}

		if (parameters.getMinId() > 0) {
			if (where == null) {
				where = greaterThanEqualMinId((short) parameters.getMinId());
			} else {
				where = where.and(greaterThanEqualMinId((short) parameters.getMinId()));
			}
		}

		if (parameters.getPage() >= 0 && parameters.getPageSize() > 0) {
			if (sort != null) {
				pageable = PageRequest.of(parameters.getPage(), parameters.getPageSize(), sort);
			} else {
				pageable = PageRequest.of(parameters.getPage(), parameters.getPageSize());
			}

			if (where != null) {
				listGroup = repository.findAll(where, pageable).toList();
			} else {
				listGroup = repository.findAll(pageable).toList();
			}

		} else {
			if(sort !=null &&  where !=null) {
				listGroup = repository.findAll(where,sort);
			}else if(sort !=null && where ==null) {
				listGroup = repository.findAll(sort);
			}else if(sort ==null && where !=null) {
				listGroup = repository.findAll(where);
			}else {
				listGroup = repository.findAll();
			}
			
		}
		
		// Do du lieu 
		if (listGroup.size() > 0) {
			for (Group group : listGroup) {
				User user = null;
				if(group.getUser() != null) {
					user = new User(
							group.getUser().getId(),
							group.getUser().getEmail(),
							group.getUser().getUserName(),
							group.getUser().getPassword(),
							group.getUser().getFisrtName(),
							group.getUser().getLastName(), 
							group.getUser().getPhone()
							);
				}
				 
				GroupDTO groupDTO = new GroupDTO(
						group.getId(),
						group.getName(),
						group.getMember(),
						group.getCreateDate(),
						user
						);
				listGroupDTOs.add(groupDTO);
			}
			
		}

		return listGroupDTOs;
	}

	public Specification<Group> searchByGroupName(String groupName) {
		return new Specification<Group>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.like(root.get("name"), "%" + groupName + "%");
			}
		};
	}

	public Specification<Group> greaterThanEqualMinId(short minId) {
		return new Specification<Group>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.ge(root.get("id"), minId);
			}
		};
	}

	public Specification<Group> lessThanEqualMaxId(short maxId) {
		return new Specification<Group>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.le(root.get("id"), maxId);
			}
		};
	}

	@Override
	public GroupDTO getGroupByID(short groupId) {
        Group group = repository.findById(groupId).get();
        User user = null;
		if(group.getUser() != null) {
			user = new User(
					group.getUser().getId(),
					group.getUser().getEmail(),
					group.getUser().getUserName(),
					group.getUser().getPassword(),
					group.getUser().getFisrtName(),
					group.getUser().getLastName(), 
					group.getUser().getPhone()
					);
		}

		GroupDTO groupDTO = new GroupDTO(
				group.getId(),
				group.getName(),
				group.getMember(),
				group.getCreateDate(),
				user
				);
		return groupDTO ;
	}

//	@Override
//	public Group getGroupByName(String groupName) {
//
//		return repository.findByGroupName(groupName);
//	}

	@Override
	public void createGroup(Group group) {

		repository.save(group);
	}

	@Override
	public void updateGroupNewName(short id, String newGroupName) {
        Group group = repository.findById(id).get();
        group.setName(newGroupName);
        repository.save(group);
	}

	@Override
	public void updateGroup(Group group) {
		repository.save(group);
	}

	@Override
	public void deleteGroup(short groupId) {
		repository.deleteById(groupId);
	}

	@Override
	public boolean isGroupExistsByID(short groupId) {

		return repository.existsById(groupId);
	}

	@Override
	public boolean isGroupExistsByName(String groupName) {

		return repository.existsByName(groupName);
	}

	@Override
	public void deleteMultiGroups(List<Short> ids) {
		repository.deleteByIds(ids);
	}

}
