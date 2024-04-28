package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DerivedProductTwoPage;
import utils.TestContextSetup;

public class DerivedProductTwoStepDefinition {
	TestContextSetup testContextSetup;
	DerivedProductTwoPage derivedProductTwoPage;
	
	public DerivedProductTwoStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
		this.derivedProductTwoPage=testContextSetup.pageObjectManager.getDerivedProductTwoPage();
	}
	
	@Given("I am in DPTwo home page")
	public void i_am_in_DPTwo_home_page() {
		derivedProductTwoPage.validateDPTwoHomePage();
	}
	
	@When("I scrolled down to Footer section")
	public void i_scrolled_down_to_Footer_section() {
		derivedProductTwoPage.scrollToFooter();
	}
	
	@Then("I took all the footer links in a file, also reported the duplicate hyperlinks")
	public void i_took_all_the_footer_links_in_a_file() {
		derivedProductTwoPage.getAllFooterlinksInFileNReportDuplicate();
	}
}
