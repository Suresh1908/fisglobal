package com.fisglobal.uiautomation.page;

import com.fisglobal.uiautomation.driver.DriverUtil;
import com.microsoft.playwright.Page;

public class EbayPage {
	DriverUtil util;
	private Page page;
	private String search = "[placeholder='Search for anything']";
	private String first_option = "//ul[@role='listbox']//li";
	private String first_book = "(//ul[contains(@class, 'srp-results')]//li//a)[3]";
	private String select_path = "[aria-label='Select a category for search']";
	private String book_path = "#srp-river-results>ul>li";
	private String add_to_cart = "//span[contains(text(),'Add to cart')]";
	private String add_to_cart_message = "[aria-label='Your shopping cart contains 1 items']";

	public EbayPage(Page page , DriverUtil util) {		
		this.page = page;
		this.util = util;
	}

	public void enterText(String text) {
		System.out.println("page  " + page);
		page.locator(search).fill(text);
	}

	public void selectFirstOption() {
		page.locator(first_option).first().dblclick();
	}

	public void selectFirstBook() {
		page = util.switchToNewPage(first_book);
		sleep(8);
		System.out.println("Title " + page.title());
	}

	public void addToCard() {
		page.locator(add_to_cart).click();
		sleep(10);
		
	}

	public int addToCartItemsSize() {
		int size = page.locator(add_to_cart_message).all().size();
		page = util.swithToMainPage();
		return size;
	}

	
	public void sleep(int n) {
		try {
			Thread.sleep(n*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
