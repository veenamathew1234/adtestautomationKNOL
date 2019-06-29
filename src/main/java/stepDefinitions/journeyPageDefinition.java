package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.journeyPage;

public class journeyPageDefinition {
	
	journeyPage jp=new journeyPage();

	@Given("^User is already in journey page$")
	public void user_is_already_in_journey_page() throws Throwable {
	    
		jp.validateJourneyPage();
	}

	@Then("^User able to navigate through each phase$")
	public void user_able_to_navigate_through_each_phase() throws Throwable {
	   
		jp.navigateThroughPhases();
	}

}
