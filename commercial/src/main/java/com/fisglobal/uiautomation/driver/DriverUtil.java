
package com.fisglobal.uiautomation.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class DriverUtil {
	private BrowserContext context;
	private Page mainpage;
	private Page subpage;
	private BrowserType chromium;

	
	public void intiateDriver(String url) {
		Playwright playwright = Playwright.create();
		chromium = playwright.chromium();
		Browser browser = chromium.launch(new LaunchOptions().setHeadless(false));
		context = browser.newContext();
		mainpage = context.newPage();
		mainpage.navigate(url);

	}

	public Page launchApp(String url) {
		intiateDriver(url);
		return mainpage;

	}

	public String getPageTitle() {
		return mainpage.title();
	}

	public Page switchToNewPage(String locator) {
		subpage = getBrowserContext().waitForPage(() -> {
			mainpage.locator(locator).click();
		});
		return subpage;
	}

	public void close() {
		mainpage.close();
		context.close();
	}

	public Page swithToMainPage() {
		return mainpage;
	}
	
	public BrowserContext getBrowserContext() {
		System.out.println("context value " + context);
		return context;
	}
}
