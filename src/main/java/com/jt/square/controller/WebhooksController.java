package com.jt.square.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.square.dto.WebhooksDto;
import com.jt.square.service.InventoryService;
import com.squareup.connect.models.V1InventoryEntry;

@Controller
public class WebhooksController {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/square/api/webhooks", method = RequestMethod.POST)
	public ResponseEntity<String> webhooks(
			// @RequestHeader(value="HTTP_X_SQUARE_SIGNATURE") String squareSignature,
			@RequestBody String body) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			WebhooksDto dto = mapper.readValue(body, WebhooksDto.class);
			System.out.println(dto.getLocationId());
		} catch (IOException e) {

		}

		List<V1InventoryEntry> inventoryList = inventoryService.listInventory();

		if (inventoryList != null) {
			System.out.println(inventoryList);
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}