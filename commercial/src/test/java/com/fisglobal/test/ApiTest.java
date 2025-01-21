package com.fisglobal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fisglobal.apiautomation.APIUtil;
import com.fisglobal.uiautomation.ScriptExecution;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTest {

	private APIRequestContext apidriver;
	private APIUtil util;
	private APIResponse response;
	private JSONObject object;
	private String responseText;
	
	static String baseurl = "https://api.coindesk.com/v1/bpi/currentprice.json";
	String descr = "British Pound Sterling";
	
	public static Set<String> actual = new HashSet<>();	
	
	public static void setBpis() {
		actual.add("EUR");
		actual.add("GBP");
		actual.add("USD");		
	}

	@BeforeAll
	public void setup() {
		util = new APIUtil();
		apidriver = util.createAPIRequestContext(baseurl);
		response = apidriver.get("/v1/bpi/currentprice.json");
		responseText = response.text();
		assertTrue(response.ok());
		setBpis();
	}
	
	@Test
	public void validateBPIs() {
		object = new JSONObject(responseText);
		JSONObject bpiObject = object.getJSONObject("bpi");
		int keyCount = bpiObject.keySet().size();
		//validating bpi counts
		assertEquals(3, keyCount);
		//placing all bpis into set 
		Set<String> bpis = new HashSet<>();
		bpiObject.keys().forEachRemaining(k -> {
			bpis.add(k);
		});
		//valiadte bpis
		assertTrue(bpis.containsAll(actual));
		
		String description = bpiObject.getJSONObject("GBP").getString("description");
	    //validating description
		assertTrue(description.equalsIgnoreCase(descr));
		util.getPlaywright().close();
	
}
}
