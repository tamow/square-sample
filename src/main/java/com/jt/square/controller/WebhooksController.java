package com.jt.square.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jt.square.dto.WebhooksDto;
import com.jt.square.service.InventoryService;
import com.squareup.connect.models.V1InventoryEntry;

@Controller
public class WebhooksController {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/square/api/webhooks", method = RequestMethod.POST)
	public ResponseEntity<String> webhooks(@RequestHeader("X-Square-Signature") String squareSignature,
			@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {

		System.out.println(squareSignature);

		ObjectMapper mapper = new ObjectMapper();
		WebhooksDto dto = mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).readValue(body,
				WebhooksDto.class);

		System.out.println(dto.getLocationId());
		System.out.println(dto.getEventType());
		System.out.println(dto.getMerchantId());
		System.out.println(dto.getEntityId());

		List<V1InventoryEntry> inventoryList = inventoryService.listInventory();

		if (inventoryList != null) {
			System.out.println(inventoryList);
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}