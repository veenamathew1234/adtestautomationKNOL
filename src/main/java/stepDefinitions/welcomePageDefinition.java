package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageRepository.welcomePage1;

public class welcomePageDefinition {
	
	welcomePage1 wp = new welcomePage1();
	
	@Given("^User is on welcome Page$")
	public void user_is_on_welcome_Page() throws Throwable {
		
		wp.validateWelcomePage();
	}

	@Then("^User clicks on Accepts Invitation button$")
	public void user_clicks_on_Accepts_Invitation_button() throws Throwable {
	    
		wp.clickAcceptInvitation();
	}


}
