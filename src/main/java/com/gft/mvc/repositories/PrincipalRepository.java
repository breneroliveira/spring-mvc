package com.gft.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.mvc.entities.Principal;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

	
}