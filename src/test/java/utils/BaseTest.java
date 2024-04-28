package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
	
public static WebDriver driver;

	private static String environment;
	public WebDriver webDriverManager() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String environment_maven=System.getProperty("environment");
		String environment_properties = prop.getProperty("environment");
		String browser_properties = prop.getProperty("browser");
		String browser_maven=System.getProperty("browser");
		
		String browser = browser_maven!=null ? browser_maven : browser_properties;
		environment = environment_maven!=null ? environment_maven : environment_properties;
		
		
		if(driver == null)
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--js-flags=--expose-gc");
				options.addArguments("test-type=browser");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-notifications");
			    options.addArguments("disable-infobars");
				
				driver= new ChromeDriver(options); 
			}
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//test//resources//drivers//geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--js-flags=--expose-gc");
				options.addArguments("test-type=browser");
				options.addArguments("--remote-allow-origins=*");
				
				driver= new FirefoxDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
		}
		return driver;
		
	}
	
	public String testEnvironment() {
		return environment.toUpperCase();
	}

	

}
