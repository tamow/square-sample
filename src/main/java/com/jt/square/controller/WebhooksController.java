package com.jt.square.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
import com.jt.square.service.SignatureService;
import com.squareup.connect.models.V1InventoryEntry;

@Controller
public class WebhooksController {

	@Autowired
	private SignatureService signatureService;

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/square/api/webhooks", method = RequestMethod.POST)
	public ResponseEntity<String> webhooks(@RequestHeader("X-Square-Signature") String squareSignature,
			@RequestBody String body) throws JsonParseException, JsonMappingException, IOException, InvalidKeyException,
			NoSuchAlgorithmException {

		if (!signatureService.validate(squareSignature, body)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		ObjectMapper mapper = new ObjectMapper();
		WebhooksDto dto = mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).readValue(body,
				WebhooksDto.class);

		List<V1InventoryEntry> inventoryList = inventoryService.listInventory(dto.getLocationId());
		if (inventoryList != null) {
			System.out.println(inventoryList);
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}