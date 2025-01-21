package com.fisglobal.apiautomation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class APIUtil {
	private Playwright playwright;
	private APIRequestContext request;
	
	public void tearDown() {
		playwright.close();
	}

	public APIRequestContext createAPIRequestContext(String url) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		playwright = Playwright.create();
		request = playwright.request()
				.newContext(new APIRequest.NewContextOptions().setBaseURL(url).setExtraHTTPHeaders(headers));
	 return request;
	}

	public APIRequestContext getRequest() {
		return request;
	}

	public Playwright getPlaywright() {
		return playwright;
	}
	
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * Set<String> actual = new HashSet<>(); actual.add("EUR"); actual.add("GBP");
	 * actual.add("USD");
	 * 
	 * String descr = "British Pound Sterling";
	 * 
	 * APIUtil apiDriver = new APIUtil(); apiDriver.createAPIRequestContext(
	 * "https://api.coindesk.com/v1/bpi/currentprice.json"); APIResponse response =
	 * apiDriver.getRequest().get("/v1/bpi/currentprice.json"); //
	 * System.out.println(response.status()); //
	 * System.out.println(response.text()); JSONObject object = new
	 * JSONObject(response.text()); JSONObject bpiObject =
	 * object.getJSONObject("bpi"); int keyCount = bpiObject.keySet().size();
	 * System.out.println("key length " + keyCount); Set<String> keys = new
	 * HashSet<>(); bpiObject.keys().forEachRemaining(k -> { keys.add(k); }); String
	 * description = bpiObject.getJSONObject("GBP").getString("description");
	 * System.out.println("description - \t" + description);
	 * System.out.println("BPIs Matched - " + keys.containsAll(actual));
	 * System.out.println("des matched - " + description.equalsIgnoreCase(descr));
	 * apiDriver.tearDown(); }
	 */
}
