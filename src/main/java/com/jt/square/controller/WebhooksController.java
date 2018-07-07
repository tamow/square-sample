package com.jt.square.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.square.dto.WebhooksDto;
import com.jt.square.service.InventoryService;
import com.squareup.connect.models.V1InventoryEntry;

@Controller
public class WebhooksController {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/square/api/webhooks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> webhooks(
			// @RequestHeader(value="HTTP_X_SQUARE_SIGNATURE") String squareSignature,
			@RequestBody WebhooksDto dto) {

		System.out.println(dto.getLocationId());

		List<V1InventoryEntry> inventoryList = inventoryService.listInventory();

		if (inventoryList != null) {
			System.out.println(inventoryList);
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}