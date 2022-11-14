package com.gft.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.mvc.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>  {


}