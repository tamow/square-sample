package com.jt.square.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebhooksDto {

	private String locationId;

	private String merchantId;

	private String eventType;

	private String entityId;

}
