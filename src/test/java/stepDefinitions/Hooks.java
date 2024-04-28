package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import constants.GlobalConstants;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContextSetup;

public class Hooks {

TestContextSetup testContextSetup;
	
	public Hooks(TestContextSetup testContextSetup)
	{
		
		this.testContextSetup = testContextSetup;
	}
	
	
	
	
	
	  /*@Before 
	  public void beforeScenario() throws IOException {
		  //TestContextSetup testContextSetup= new TestContextSetup();
		  //testContextSetup.baseTest.webDriverManager();
		  BaseTest baseTest = new BaseTest();
		  driver = baseTest.webDriverManager();
	  }*/
	  
	 
	 
	 
	
	
	@Before("@cp")
	public void coreProducts() throws IOException {
		String url = GlobalConstants.getTestUrl("coreProduct");
		testContextSetup.baseTest.webDriverManager().get(url);
		
	}
	
	
	  @Before("@dp2") public void derivedProductTwo() throws IOException { 
		  String url = GlobalConstants.getTestUrl("derivedProduct2"); 
		  testContextSetup.baseTest.webDriverManager().get(url);
	  
	  }
	 
	
	
	  @After public void AfterScenario() throws IOException {
		  testContextSetup.baseTest.webDriverManager().quit();
	  
	  }
	 
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException
	{
		WebDriver driver =testContextSetup.baseTest.webDriverManager();
		if(scenario.isFailed())
		{
		//screenshot
		File sourcePath= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
		
		}
		
	}
	
}
