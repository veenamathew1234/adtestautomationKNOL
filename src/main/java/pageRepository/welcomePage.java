package pageRepository;

import java.io.FileInputStream;
import org.junit.Assert;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpResponse;

import utils.ObjectFactory;
import utils.StartUp;

public class welcomePage extends StartUp {
	
	//WebDriver driver=getDriver();
	ObjectFactory objmap;
	//StartUp st = new StartUp();
	//Map<String,Object> DataObj=st.beforeClass();
	public Properties prop;
	WebElement e;
	String isNewUser;
	int statusCode ;
		
public welcomePage(){
			
	this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/WelcomePage.properties");
	}

 public void validateWelcomePage(){
	    String WelcomePageTitle= driver.getTitle();
		System.out.println("Welcome page title is "+WelcomePageTitle);
		
		if (driver.getTitle().equals("AktivLearn Plus"))
			System.out.println("User landed on correct page");
 }
 
 public void clickAcceptInvitation() throws Exception{
 
	 	isNewUser = DataObj.get("isNewUser").toString();
		if(isNewUser.equalsIgnoreCase("yes")){
			System.out.println("User is new user and Accept invitation button is found");
			Thread.sleep(7000);
			e=driver.findElement(objmap.getLocator("btn_AcceptInvitation"));
			e.click();
			
		}
		
		else{
			System.out.println("User is an old user and Accept invitation button is not found");
		}
 }

}
