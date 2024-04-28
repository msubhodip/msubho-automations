package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.TestContextSetup;

public class CoreProductPage {
	
	static Logger log = LogManager.getLogger(CoreProductPage.class);
	public WebDriver driver;
	TestContextSetup testContextSetup=new TestContextSetup();
	
	
	public CoreProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By dialogCloseButton = By.xpath("//div[@role='dialog']//div[contains(@class,'cursor-pointer')]");
	By homePagekaiserLogo = By.xpath("//a[contains(@title,'Kaiser')]");
	By threeDotOption = By.xpath("//li[contains(@data-testid,'nav-item-#')]//a[contains(@rel,'noreferrer')]//span");
	String newsNFeaturesOption = "(//a[@title='ITEM'])[1]";
	//By newsNFeaturePage = By.xpath("//h3[contains(text(),'NEWS')]");
	String allVideoFeed = "//h3[contains(text(),'ITEM')]/../following-sibling::div//time[contains(@aria-label,'day')]";
	String conditionalVideoFeeds = "//h3[contains(text(),'ITEM')]/../following-sibling::div//li//div[contains(@data-testid,'tile-meta')]//span";
	By shopMenu = By.xpath("//li[@role='menuitem']//span[contains(text(),'Shop')]");
	By mensOptionInShopMenu = By.xpath("(//a[contains(@title,'Men')])[1]");
	By mensFilter = By.xpath("//a[@aria-label='Remove Men filter']");
	By searchPlaceholder = By.xpath("//input[@id='typeahead-input-desktop']");
	String selectFilterOption = "//a[@data-trk-id='ITEMS']//span";
	By genderAgeMensFilter = By.xpath("//a[@data-trk-id='men']//span");
	By jacketTitle = By.xpath("//div[@data-trk-id='product-grid']//div[contains(@class,'product-card-title')]");
	By topPaginationButton = By.xpath("//div[contains(@class,'top-area')]//li[contains(@class,'next-page')]//a");
	String itemPrice = "//div[contains(@class,\"TITLE\")]/../..//span[@class='price']/span/span[contains(@class,'sr-only')]";
	String topSellerMssg = "//div[contains(@class,\"TITLE\")]/../..//span[@class='top-seller-vibrancy-message'])[1]";
	
	public void closeDialogBox() {
			driver.findElement(dialogCloseButton).click();
			testContextSetup.genericUtils.waitForElementToAppear(homePagekaiserLogo, 10);	
	}
	
	public void clickOnMenuItems(String item) {
		testContextSetup.genericUtils.hoverOnElement(threeDotOption);
		testContextSetup.genericUtils.clickJavaScript(By.xpath(newsNFeaturesOption.replaceAll("ITEM", item)));
	}
	
	public void countFeedItems(String items) {
		TestContextSetup testContextSetup= new TestContextSetup();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = driver.findElements(By.xpath(allVideoFeed.replaceAll("ITEM", items.toUpperCase()))).size();
		Assert.assertTrue(count>0);
		System.out.println("Total count: "+count);
		log.info(count);
	}
	
	public void countConditionalFeedItems (String items, String condition) {
		List<WebElement> list = driver.findElements(By.xpath(conditionalVideoFeeds.replaceAll("ITEM", items.toUpperCase())));
		Assert.assertTrue(list.size()>0);
		int counter=0;
		for(WebElement ele:list) {
			if(Integer.parseInt(ele.getAttribute("innerText").replace("d", ""))>=Integer.parseInt(condition.replace("d",""))) {
				counter++;
			}
		}
		System.out.println("Count with >=3d: "+counter);
		log.info(counter);
	}

	public void navigateToMensSectionFromShopMenu() {
		testContextSetup.genericUtils.hoverOnElement(shopMenu);
		driver.findElement(mensOptionInShopMenu).click();
		testContextSetup.genericUtils.switchWindowToChild();
		testContextSetup.genericUtils.waitForElementToAppear(mensFilter, 10);
		
	}

	public void searchItems(String string) {
		driver.findElement(searchPlaceholder).clear();
		driver.findElement(searchPlaceholder).sendKeys(string);
		driver.findElement(searchPlaceholder).sendKeys(Keys.ENTER);
		
	}

	public void filterItem(String item) {
		driver.findElement(By.xpath(selectFilterOption.replace("ITEMS", item.toLowerCase()))).click();
		driver.findElement(genderAgeMensFilter).click();
		
	}

	public void getItemsDetailsNAttachFile(String item) {
		try {
			String fileName = System.getProperty("user.dir")+"//src//test//resources//Product.txt";
			File f = new File(fileName);
			if (f.exists()) {
				f.delete();
			} else {
				f.createNewFile();
			}
			FileWriter w = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(w);
			String flag = driver.findElement(topPaginationButton).getAttribute("aria-disabled");
			//System.out.println("Button: "+flag);
			while (!Boolean.valueOf(flag)) {
				List<WebElement> list = driver.findElements(jacketTitle);
				for(WebElement ele:list) {
					String productTitle = ele.getAttribute("title");
					//System.out.println("Product: "+productTitle);
					bw.append(productTitle+ " ");
					String productPrice = driver.findElement(By.xpath(itemPrice.replace("TITLE", productTitle))).getAttribute("innerText");
					bw.append(productPrice+ " ");
					String mssg="";
					List<WebElement> el = driver.findElements(By.xpath(topSellerMssg.replace("TITLE", productTitle)));
					if(el.size()>0) {
						mssg = driver.findElement(By.xpath(topSellerMssg.replace("TITLE", productTitle))).getAttribute("innerText");
					}
					/*
					 * try {
					 * 
					 * } catch (NoSuchElementException e) { // TODO: handle exception }
					 */
					bw.append(mssg+ " ");
					bw.newLine();
				}
				driver.findElement(topPaginationButton).click();
			}
			bw.close();
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
