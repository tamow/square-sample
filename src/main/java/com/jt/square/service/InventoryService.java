package com.jt.square.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.V1ItemsApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.V1InventoryEntry;

@Service
public class InventoryService {

	public List<V1InventoryEntry> listInventory() {

		ApiClient defaultClient = Configuration.getDefaultApiClient();

		final String accessToken = "sq0atp-r-tMnh1wMMJSrSPGEeSWeQ";

		OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
		oauth2.setAccessToken(accessToken);

		V1ItemsApi apiInstance = new V1ItemsApi();

		// String | The ID of the item's associated location.
		final String locationId = "2K1RWWN75BTPT";
		// Integer | The maximum number of inventory entries to return in a single response. This value cannot exceed 1000.
		final Integer limit = 50;
		// String | A pagination cursor to retrieve the next set of results for your original query to the endpoint.
		final String batchToken = "";

		try {
			List<V1InventoryEntry> result = apiInstance.listInventory(locationId, limit, batchToken);
			return result;
		} catch (ApiException e) {
			System.err.println("Exception when calling V1ItemsApi#listInventory");
			e.printStackTrace();
		}

		return null;
	}
}