package com.gft.mvc.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gft.mvc.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.email= (:email)")
    public User findByUsername(@Param ("email") String email);
}