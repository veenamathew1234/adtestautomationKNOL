package stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class WelcomePage {
	WebDriver driver;
	String script="";
	String CatchS=null;
	String url;
	int flag;
	JavascriptExecutor js;
	public WelcomePage(RemoteWebDriver driver) 
	{
		this.driver=driver;
		
	}
	@Given("^User is in Welcome Page$")
	public void user_is_in_Welcome_Page() {
		 script="return document.querySelectorAll('div[class$=\"journeyItemIntroduction-module-button\"]')[1];";
		 js =(JavascriptExecutor) driver;
		 CatchS= js.executeScript(script).toString();
		 boolean a;
		 flag=0;
		 if(CatchS!=null)
			  flag=1;
		  Assert.assertEquals(1, flag);
	    
	}

	@Then("^Click on Accept Invitation$")
	public void click_on_Accept_Invitation()  {
	    
		 script="document.querySelectorAll('div[class$=\"journeyItemIntroduction-module-button\"]')[1].click();";
		  js =(JavascriptExecutor) driver;
		  js.executeScript(script);
	}

}
