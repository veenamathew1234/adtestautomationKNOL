package stepDefinitions;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;

import junit.framework.Assert;
import pageRepository.demographicsPage;

public class demographicsPageDefinition{
	
	demographicsPage dp = new demographicsPage();
	
	@Given("^The user has landed on the demographics page$")

		public void the_user_has_landed_on_the_demographics_page()  {
			
			dp.validateDemographicsPageLoad();

		}

//		@Then("^Refresh the demographics page by redirecting again to the same url$")
//
//		public void refresh_the_demographics_page_by_redirecting_again_to_the_same_url() throws Exception{
//
//			//dp.pageRefresh();
//			
//		}
		
		@Then("^fill the demographics page and policy page$")

		public void fill_the_demographics_page_and_policy_page() throws Exception{

			dp.fillDemographicsPage();

		}
		
}

