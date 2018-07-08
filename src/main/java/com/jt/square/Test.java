package com.jt.square;

import java.util.List;

import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.Configuration;
import com.squareup.connect.api.V1ItemsApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.V1Item;

public class Test {

	public static void main(String[] args) {

		ApiClient defaultClient = Configuration.getDefaultApiClient();

		final String accessToken = "sq0atp-r-tMnh1wMMJSrSPGEeSWeQ";

		OAuth oauth2 = (OAuth) defaultClient.getAuthentication("oauth2");
		oauth2.setAccessToken(accessToken);

		V1ItemsApi apiInstance = new V1ItemsApi();
		final String locationId = "2K1RWWN75BTPT"; // String | The ID of the location to list items for.
		String batchToken = ""; // String | A pagination cursor to retrieve the next set of results
								// for your
								// original query to the endpoint.
		try {
			List<V1Item> result = apiInstance.listItems(locationId, batchToken);
			System.out.println(result);
		} catch (ApiException e) {
			System.err.println("Exception when calling V1ItemsApi#listItems");
			e.printStackTrace();
		}
	}
}
