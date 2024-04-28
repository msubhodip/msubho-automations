package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CoreProductPage;
import utils.TestContextSetup;

public class CoreProductPageStepDefinition {
	
	TestContextSetup testContextSetup;
	CoreProductPage coreProductPage;
	
	public CoreProductPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
		this.coreProductPage=testContextSetup.pageObjectManager.getCoreProductPage();
	}
	
	@Given("I am in CP home page")
	public void i_am_in_CP_home_page() {
			coreProductPage.closeDialogBox();
	}
	
	@When("I navigate to {string} from more options")
	public void i_navigate_to_from_more_options(String string) {
	    coreProductPage.clickOnMenuItems(string.trim());
	}
	
	@Then("I counted {string} feed")
	public void i_counted_videos_feed(String item) {
	    coreProductPage.countFeedItems(item);
	}
	
	@Then("I counted {string} feed with greater than equals {string}")
	public void i_counted_videos_feed_with(String item, String string) {
	    coreProductPage.countConditionalFeedItems(item, string);
	}
	
	@When("I navigate to Mens section from Shop Menu")
	public void i_navigate_to_Mens_section_from_Shop_Menu() {
		coreProductPage.navigateToMensSectionFromShopMenu();
	}
	
	@And("I searched {string} in the search option")
	public void i_searched_jackets_in_the_search_option(String string) {
		coreProductPage.searchItems(string);
	}
	
	@And("I selected {string} options for filter")
	public void i_selected_Jackets_options_for_filter(String item) {
		coreProductPage.filterItem(item);
	}
	
	@Then("I took each {string} Price, Title and Top Seller message to a text file")
	public void i_took_each_Jacket_Price_Title_and_Top_Seller_message_to_a_text_file(String item) {
		coreProductPage.getItemsDetailsNAttachFile(item);
	}

}
