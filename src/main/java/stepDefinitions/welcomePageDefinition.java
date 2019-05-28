package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.welcomePage;

public class welcomePageDefinition {
	
	welcomePage wp = new welcomePage();
	
	@Given("^User is on home Page$")
	public void user_is_on_home_Page() throws Throwable {
		
		wp.validateWelcomePage();
	}

	@When("^User clicks on Accepts Invitation button$")
	public void user_clicks_on_Accepts_Invitation_button() throws Throwable {
	    
		wp.clickAcceptInvitation();
	}

//	@Then("^User is navigated to Journey Page$")
//	public void user_is_navigated_to_Journey_Page() throws Throwable {
//	   
//		wp.validateJourneyPage();
//	}



}
