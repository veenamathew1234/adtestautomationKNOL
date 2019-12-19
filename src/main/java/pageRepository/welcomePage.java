package pageRepository;

import java.io.FileInputStream;
import org.junit.Assert;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TimeBombSkipException;

import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class welcomePage extends StartUp {

	ObjectFactory objmap;
	StartUp st = new StartUp();
	public Properties prop;
	WebElement e;
	String isNewUser;
	int statusCode ;
	List<WebElement> l;
	CommonMethods cm=new CommonMethods(); 
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	
		
public welcomePage(){
			
	this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/WelcomePage.properties");
	Map<String,Object> DataObj=st.beforeClass("testData.json");	
	
}

 public void validateWelcomePage() throws Exception{
	 
	 Boolean flag=false;
	 try
	 {
		 cm.checkErrorComponents();
		 Thread.sleep(2000);
		 String currenturl=driver.getCurrentUrl();
		 System.out.println("currenturl"+currenturl);
		 if(currenturl.startsWith("https://stg-aktivplatform.knolskape.io/")){
			 System.out.println("Inside validate welcome page");
			 flag=true;
			 isNewUser = DataObj.get("isNewUser").toString();
			 if(isNewUser.equalsIgnoreCase("yes")){
				 System.out.println("Inside new user");
				 l=driver.findElements(objmap.getLocator("lbl_greetings"));
				 Assert.assertEquals("Welcome Page not loaded after clicking on accept invitation",1,l.size());
				 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_ContinueLearning"))).click();
			 }
		 }
		 else
			 Assert.assertTrue("User not redirected after Accept Invitation page",flag);
	 }
	 catch(NoSuchElementException ne)
	 {
		 Assert.assertNull("Continue/Start learning button not available to click", ne);
		 ne.printStackTrace();
	 }
	 catch(TimeoutException te)
	 {
		 Assert.assertNull("Continue/Start learning button not available to click even after waiting for 30 seconds", te);
		 te.printStackTrace();
	 }
	 
 }
 
 public void clickAcceptInvitation() throws Exception{
 
	 try{
		 if(isNewUser.equalsIgnoreCase("yes")){
				System.out.println("User is new user and Accept invitation button is found");
				wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_AcceptInvitation"))).click();
		 }
			
	 }
		 catch(NoSuchElementException ne)
			{
				Assert.assertNull("Accept Invitation button is not found in Welcome Page", ne);
				ne.printStackTrace();
				
			}
			catch(TimeoutException te)
			{
				Assert.assertNull("Accept Invitation button is not found in Welcome Page", te);
				te.printStackTrace();
				
			}

		
 }

}
