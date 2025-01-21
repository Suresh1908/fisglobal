package com.fisglobal.uiautomation;



import com.fisglobal.uiautomation.driver.DriverUtil;
import com.fisglobal.uiautomation.page.EbayPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

/**
 * Hello world!
 */
public class ScriptExecution {

	
	
	public static void main(String[] args) {
		DriverUtil util = new DriverUtil();
		Page page =util.launchApp("https://www.ebay.com/");
        EbayPage ebaypage = new EbayPage(page,util);
        ebaypage.enterText("book");
        sleep(5);
        ebaypage.selectFirstOption();
        sleep(5);
        ebaypage.selectFirstBook();
        sleep(5);
        ebaypage.addToCard();
        int size = ebaypage.addToCartItemsSize();
        if(size==0) {
        	sleep(5);
        System.out.println("retry" + ebaypage.addToCartItemsSize());
        	
        }
		
	}

	public static void sleep(int n) {

		try {
			Thread.sleep(n * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
