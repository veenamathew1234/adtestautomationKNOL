package stepDefinitions;
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

public class login {
	
	StartUp st = new StartUp();
	private RemoteWebDriver driver=st.setUp();
	Map<String,Object> DataObj=st.beforeClass();
	
	public Properties obj;
		
	@Given("^User is already in login page$")
	public void user_is_already_in_login_page()
	{
		driver.get(DataObj.get("url").toString());
	   
	}
	
	@When("^Title of the page is Login to Kompass$")
	public void title_of_the_page_is_Login_to_Kompass() {
	   
		String title=driver.getTitle();
		System.out.println("Page title :"+title);
		Assert.assertEquals("Login to Kompass", title);
	}
	
	@Then("^User enters email and password$")
	public void user_enters_email_and_password(){
	    
		
	}
	

}
