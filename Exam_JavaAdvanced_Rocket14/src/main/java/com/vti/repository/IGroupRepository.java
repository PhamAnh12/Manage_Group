package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Group;



public interface IGroupRepository extends JpaRepository<Group, Short>,JpaSpecificationExecutor<Group> {
	public Group findByName(String groupName);
	public boolean existsByName(String groupName);
    @Modifying
    @Transactional
	@Query("Delete From  Group Where id IN (:ids)")
	public void deleteByIds(@Param("ids") List <Short> ids);
	
}
