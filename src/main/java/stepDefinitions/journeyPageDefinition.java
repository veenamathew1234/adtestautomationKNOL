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

	@Then("^User clicks on each of the phases$")
	public void user_clicks_on_each_of_the_phases() throws Throwable {
	   
		jp.navigateThroughPhases();
	}

}
