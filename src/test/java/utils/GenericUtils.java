package utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtils {
	
public WebDriver driver;
TestContextSetup testContextSetup;
	
	public GenericUtils(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void waitForElementToAppear(By locator,long duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToDisAppear(By locator, long duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void clickJavaScript(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void hoverOnElement(By locator) {
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(locator)).perform();
	}
	
	public void switchWindowToChild() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
	}
	
	public void scrolltoElement(WebElement ele){
		/*
		 * Actions actions = new Actions(driver); actions.moveToElement(ele);
		 * actions.perform();
		 */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				// TODO Auto-generated method stub
				return ((JavascriptExecutor) driver).executeScript("return document.readState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(pageLoadCondition);
	}

}
