package stepDefinitions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import pageRepository.loginPage;
import pageRepository.loginPage_excel;

public class loginPageDefinition {
	
	loginPage_excel lp=new loginPage_excel();
	
	@Given("^User is already in login page$")
	public void user_is_already_in_login_page() throws IOException{
	
		lp.launchPage();
   	}
	
	@When("^Title of the page is Login to AktivLearn Plus$")
	public void title_of_the_page_is_Login_to_AktivLearnPlus() {
	   
		lp.validateLoginPage();
	}
	
	@Then("^User enters email and password$")
	public void user_enters_email_and_password() throws Exception{
		
		lp.enterUserCredentials();
    }
	
	@Then("^User clicks on signin button$")
	public void user_clicks_on_signin_button() throws Exception{
		
		lp.clickOnSignButton();
  }
	

}



