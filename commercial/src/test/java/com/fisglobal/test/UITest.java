package com.fisglobal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fisglobal.uiautomation.driver.DriverUtil;
import com.fisglobal.uiautomation.page.EbayPage;
import com.microsoft.playwright.Page;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UITest {

	private DriverUtil util;
	private Page page;
	private EbayPage ebaypage;
	@BeforeAll
	public void setup() {
		util = new DriverUtil();
		page =util.launchApp("https://www.ebay.com/");		
		ebaypage = new EbayPage(page,util);
		util.getBrowserContext()
		.setDefaultTimeout(9000);
		page.setDefaultTimeout(9000);
	}

	
	@Test
	public void cartvalidation() {	    
		ebaypage.enterText("book");
        ebaypage.selectFirstOption();
        ebaypage.selectFirstBook();
        ebaypage.addToCard();
        int size = ebaypage.addToCartItemsSize();
        assertEquals(1, size);		
	}
}
