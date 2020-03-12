package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.welcomePage;

public class welcomePageDefinition {
	
	welcomePage wp = new welcomePage();
	
	@Given("^User is on welcome Page$")
	public void user_is_on_welcome_Page() throws Throwable {
		
		wp.validateWelcomePage();
	}

	@Then("^User clicks on Accepts Invitation button$")
	public void user_clicks_on_Accepts_Invitation_button() throws Throwable {
	    
		wp.clickAcceptInvitation();
	}


}
