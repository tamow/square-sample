package com.jt.square.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jt.square.dto.ItemDto;
import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.CatalogApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.CatalogObject;

@Service
public class ItemService {

	@Value("${square.accessToken}")
	String accessToken;

	public List<ItemDto> listItems() {

		ApiClient defaultClient = Configuration.getDefaultApiClient();

		OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
		oauth2.setAccessToken(accessToken);

		CatalogApi apiInstance = new CatalogApi();
		String cursor = "";
		String types = "ITEM";

		try {
			List<ItemDto> items = new ArrayList<>();
			while (cursor != null) {
				List<CatalogObject> result = apiInstance.listCatalog(cursor, types).getObjects();
				for (CatalogObject obj : result) {
					ItemDto item = new ItemDto();
					item.setName(obj.getItemData().getName());
					items.add(item);
				}
			}
			return items;

		} catch (ApiException e) {
			System.err.println("Exception when calling CatalogApi#listCatalog");
		}

		return null;

	}
}