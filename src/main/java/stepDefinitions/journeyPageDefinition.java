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

	@When("^User clicks on each of the phase items$")
	public void user_clicks_on_each_of_the_phase_items() throws Throwable {
	   
		jp.clickPhaseItem();
	}

	@Then("^User should be displayed with correct page$")
	public void user_should_be_displayed_with_correct_page() throws Throwable {
	  
		
	}


}
