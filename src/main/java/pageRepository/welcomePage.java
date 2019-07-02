package pageRepository;

import java.io.FileInputStream;
import org.junit.Assert;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpResponse;

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
	
		
public welcomePage(){
			
	this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/WelcomePage.properties");
	Map<String,Object> DataObj=st.beforeClass("testData.json");	
	
}

 public void validateWelcomePage() throws Exception{
	 cm.checkErrorComponents();
	 isNewUser = DataObj.get("isNewUser").toString();
	 if(isNewUser.equalsIgnoreCase("yes")){
		 l=driver.findElements(objmap.getLocator("lbl_greetings"));
	 Assert.assertEquals("Incorrect Welcome Page",l.size(),1);
	 }
	
	 
 }
 
 public void clickAcceptInvitation() throws Exception{
 
		if(isNewUser.equalsIgnoreCase("yes")){
			System.out.println("User is new user and Accept invitation button is found");
			Thread.sleep(7000);
			
			e=driver.findElement(objmap.getLocator("btn_AcceptInvitation"));
			Assert.assertNotNull("Accept Invitation button Not found", e);
			e.click();
		}
		
		else{
			System.out.println("User is an old user and Accept invitation button is not found");
			Assert.assertEquals("Invitation button found even for old user",0,driver.findElements(objmap.getLocator("btn_AcceptInvitation")).size());
		}
 }

}
