package stepDefinitions;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.journeyPage1;
import utils.CommonMethods;

public class journeyPageDefinition {
	
	journeyPage1 jp=new journeyPage1();
	CommonMethods cm=new CommonMethods();

	@Given("^User is already in journey page$")
	public void user_is_already_in_journey_page() throws Throwable {
	    
		jp.validateJourneyPage();
	}

	@Then("^User able to navigate through each phase$")
	public void user_able_to_navigate_through_each_phase() throws Throwable {
	   
		
		jp.navigateThroughPhases();
	        }
	
	}

