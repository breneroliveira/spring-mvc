package com.gft.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.entities.Item;
import com.gft.mvc.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public void salvarItem(Item item) {
		
		itemRepository.save(item);
	}
	
	public List<Item> listarItem() {
		
		return itemRepository.findAll();
	}
}