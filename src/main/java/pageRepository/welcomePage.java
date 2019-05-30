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
import utils.StartUp;

public class welcomePage extends StartUp {
	
	WebDriver driver=getDriver();
	StartUp st = new StartUp();
	Map<String,Object> DataObj=st.beforeClass();
	//Map<String,Object> DataObj;
	public Properties prop;
	WebElement e;
	String isNewUser;
		
public welcomePage(){
			
		System.out.println("Inside welcome page constructor");
		this.prop=new Properties();
		FileInputStream objfile;
		try {
				objfile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/uiMap/WelcomePage.properties");
				prop.load(objfile);
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	}

 public void validateWelcomePage(){
	    String WelcomePageTitle= driver.getTitle();
		System.out.println("Welcome page title is "+WelcomePageTitle);
		
		if (driver.getTitle().equals("AktivLearn Plus"))
			System.out.println("User landed on correct page");
 }
 
 public void clickAcceptInvitation() throws InterruptedException{
	
	    isNewUser = DataObj.get("isNewUser").toString();
		Thread.sleep(5000);

		if(isNewUser.equalsIgnoreCase("yes")){
			System.out.println("User is new user and Accept invitation is found");
			Thread.sleep(6000);
			e=driver.findElement(By.xpath(prop.getProperty("btn_AcceptInvitation")));
			e.click();
			validateJourneyPage();
		}
		else{
			System.out.println("User is an old user and Accept invitation button is not found");
			validateJourneyPage();
		}
		

 }
 
 public void validateJourneyPage(){
	 
	 System.out.println("Inside Journey Page");
	 e=driver.findElement(By.xpath(prop.getProperty("lbl_journeypage")));
	 System.out.println("Welcome page label is " +e.getText());
	 if(e.getText().startsWith("Welcome to"))
		 System.out.println("Sucessfully landed on journey page");
		 
	 //Assert.assertEquals("Welcome to", e.getText());
 }

}
