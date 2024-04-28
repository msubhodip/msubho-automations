package utils;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;


public class TestContextSetup {
	
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	public BaseTest baseTest;
	public GenericUtils genericUtils;

	
	public TestContextSetup()
	{
		try {
			baseTest = new BaseTest();
			pageObjectManager = new PageObjectManager(baseTest.webDriverManager());
			genericUtils = new GenericUtils(baseTest.webDriverManager());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			

	}

}
