package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Group;
import com.vti.entity.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Short>, JpaSpecificationExecutor<Group> {
    public User findByUserName(String username);
   
}