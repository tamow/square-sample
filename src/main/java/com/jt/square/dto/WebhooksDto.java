package com.jt.square.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class WebhooksDto implements Serializable {

	private String locationId;

	private String merchantId;

	private String eventType;

	private String entityId;

}
