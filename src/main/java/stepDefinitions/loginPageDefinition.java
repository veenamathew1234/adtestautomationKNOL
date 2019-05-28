package stepDefinitions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import utils.StartUp;
import pageRepository.loginPage;

public class loginPageDefinition{
	
	loginPage lp=new loginPage();
	
	@Given("^User is already in login page$")
	public void user_is_already_in_login_page(){
	
		lp.launchPage();
   	}
	
	@When("^Title of the page is Login to AktivLearn Plus$")
	public void title_of_the_page_is_Login_to_AktivLearnPlus() {
	   
		lp.validateLoginPage();
	}
	
	@Then("^User enters email and password$")
	public void user_enters_email_and_password(){
		
		lp.enterUserCredentials();
    }
	
	@Then("^User clicks on signin button$")
	public void user_clicks_on_signin_button(){
		
		lp.clickOnSignButton();
    }
	
	@Then("^User is on AktivLearn Plus home page$")
	public void user_is_on_AktivLearnPlus_home_page() {
		
		lp.reloadDemographics();
    }
	

}



