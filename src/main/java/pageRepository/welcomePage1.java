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

public class welcomePage1 extends StartUp {

	ObjectFactory objmap;
	StartUp st = new StartUp();
	public Properties prop;
	WebElement e;
	String isNewUser;
	int statusCode ;
	List<WebElement> l;
	CommonMethods cm=new CommonMethods(); 
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	
		
public welcomePage1(){
			
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

			 }
		 }
		 else
			 Assert.assertTrue("User not redirected after Accept Invitation page",flag);
	 }
	 catch(NoSuchElementException ne)
	 {
		 cm.screenShot();
		 Assert.assertNull("Continue/Start learning button not available to click", ne);
		 ne.printStackTrace();
	 }
	 catch(TimeoutException te)
	 {
		 cm.screenShot();
		 Assert.assertNull("Continue/Start learning button not available to click even after waiting for 30 seconds", te);
		 te.printStackTrace();
	 }
	 
 }
 
 public void clickAcceptInvitation() throws Exception{
 
	 try{
		 if(isNewUser.equalsIgnoreCase("yes")){
				System.out.println("User is new user and Accept invitation button is found");
				e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'_sq6t1r') and contains(text(),'Start')]")));
				e.click();
				Thread.sleep(3000);
				e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'_1bkxflw module-4f785y9t2f6v4kr5x4vg22n6gc6pdj97j1x5p4am8jfzt85yw3hpj8hpdqdsugeywgdhfee1gu6rzs73wcapu4j2jm59vzkxdhscek1-journeyItemIntroduction-module-button')]")));
				e.click(); 
		 }
			
	 }
		 catch(NoSuchElementException ne)
			{
			 	cm.screenShot();
				Assert.assertNull("Accept Invitation button is not found in Welcome Page", ne);
				ne.printStackTrace();
				
			}

		
 }

}
