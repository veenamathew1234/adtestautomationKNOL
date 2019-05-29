package stepDefinitions;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.remote.RemoteWebDriver;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;

import junit.framework.Assert;

public class demographicsPageDefinition {

		public RemoteWebDriver driver;

		String script="";

		String CatchS=null;

		String url;

		int flag;



		public demographicsPageDefinition(RemoteWebDriver driver)

		{

			this.driver=driver;

		}

		

		@Given("^The user has landed on the demographics page$")

		public void the_user_has_landed_on_the_demographics_page()  {

			flag=0;

			url=driver.getCurrentUrl();

			if(url.startsWith("https://accounts-test.knolskape.com/user/fill_demographics?"))

				flag=1;

				

		}



		@Then("^Refresh the demographics page by redirecting again to the same url$")

		public void refresh_the_demographics_page_by_redirecting_again_to_the_same_url(){

			driver.get("http://stg-aktivplatform.knolskape.io/");

		}



		@Then("^Verify if the user is navigated to the Welcome page$")

		public void verify_if_the_user_is_navigated_to_the_Welcome_page()  {

			script="return document.querySelectorAll('div[class$=\"journeyItemIntroduction-module-button\"]')[1];";

			JavascriptExecutor js =(JavascriptExecutor) driver;

			CatchS= js.executeScript(script).toString();

			boolean a;

			flag=0;

			if(CatchS!=null)

				  flag=1;

			Assert.assertEquals(1, flag);

		    

		}



		

	}

