package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "stepDefinitions",
		monochrome=true,
		plugin = { "html:target/cucumber.html","json:target/cucumber.json", "pretty:target/run/cucumber-pretty.txt", 
				   "junit:target/run/cucumber-results.xml",
				   "rerun:target/failed_scenarios.txt"})			   

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
	
	
	
}
