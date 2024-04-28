package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.TestContextSetup;

public class DerivedProductTwoPage {
	
	static Logger log = LogManager.getLogger(DerivedProductTwoPage.class);
	private WebDriver driver;
	TestContextSetup testContextSetup= new TestContextSetup();
	
	
	public DerivedProductTwoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By dpHomePage = By.xpath("//img[@alt='Chicago Bulls']");
	By footer = By.xpath("//footer");
	By footerlinks = By.xpath("//footer//a[contains(@href,'/')]");

	public void validateDPTwoHomePage() {
		testContextSetup.genericUtils.waitForElementToAppear(dpHomePage, 10);
		
	}


	public void scrollToFooter() {
		WebElement ele = driver.findElement(footer);
		testContextSetup.genericUtils.scrolltoElement(ele);
		testContextSetup.genericUtils.waitForElementToAppear(footer, 10);
		
	}


	public void getAllFooterlinksInFileNReportDuplicate() {
		try {
			File f = new File(System.getProperty("user.dir")+"//src//test//resources//ITemsDetail.csv");
			if (f.exists()) {
				f.delete();
			} else {
				f.createNewFile();
			}
			List<String> duplicates = new ArrayList<>();
			Set<String> set = new HashSet<>();
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw =  new BufferedWriter(fw);
		List<WebElement> list = driver.findElements(footerlinks);
		for(WebElement ele: list) {
			String link = ele.getAttribute("href");
			
			if(set.contains(link)) {
				duplicates.add(link);
			}
			set.add(link);
				bw.write(link);
				bw.newLine();
				
			}
		bw.newLine();
		bw.write("\n"+"Duplicates: "+duplicates);
		bw.close();
		fw.close();
		log.info(duplicates);
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
