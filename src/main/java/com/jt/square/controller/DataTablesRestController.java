package com.jt.square.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.square.dto.ItemDto;
import com.jt.square.service.ItemService;

@RestController
public class DataTablesRestController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/getitemlist")
	public List<ItemDto> getitemlist() {

		List<ItemDto> itemList = itemService.listItems();
		if (itemList == null) {
			return null;
		}

		return itemList;
	}
}