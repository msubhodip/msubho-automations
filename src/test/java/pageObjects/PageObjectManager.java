package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	public WebDriver driver;
	public CoreProductPage coreProductPage;
	public DerivedProductTwoPage derivedProductTwoPage;
	
	public PageObjectManager(WebDriver driver)
	{
		this.driver = driver;
	}

	
	
	  public CoreProductPage getCoreProductPage() {
	  
		  coreProductPage= new CoreProductPage(driver); 
		  return coreProductPage; 
	  }
	 
	  public DerivedProductTwoPage getDerivedProductTwoPage() {
		  derivedProductTwoPage = new DerivedProductTwoPage(driver);
		  return derivedProductTwoPage;
	  }
}
