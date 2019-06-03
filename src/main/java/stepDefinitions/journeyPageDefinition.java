package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.journeyPage1;

public class journeyPageDefinition {
	
	journeyPage1 jp=new journeyPage1();

	@Given("^User is already in journey page$")
	public void user_is_already_in_journey_page() throws Throwable {
	    
		jp.validateJourneyPage();
	}

	@Then("^User clicks on each of the phase items$")
	public void user_clicks_on_each_of_the_phase_items() throws Throwable {
	   
		jp.clickPhaseItem();
	}

}
