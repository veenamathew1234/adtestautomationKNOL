package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.StartUp;

public class LoginPage {
	
	StartUp st = new StartUp();
	private RemoteWebDriver driver=st.setUp();
	Map<String,Object> DataObj=st.beforeClass();
	public Properties prop;
	WebElement e;
	
	public LoginPage(){
		
		this.prop=new Properties();
		FileInputStream objfile;
			try {
				objfile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/uiMap/LoginPage.properties");
				prop.load(objfile);
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	}

	public void launchPage(){
		
		driver.get(DataObj.get("url").toString());
	}
	
	public void validateLoginPage(){
		String title=driver.getTitle();
		System.out.println("Page title :"+title);
		Assert.assertEquals("Login to Kompass", title);
	}
	
	public void enterUserCredentials(){
		
		System.out.println("Entering credentials");
	    e=driver.findElement(By.xpath(prop.getProperty("txt_Email")));
	    e.sendKeys(DataObj.get("email").toString());
	    e=driver.findElement(By.xpath(prop.getProperty("txt_Password")));
	    e.sendKeys(DataObj.get("password").toString());
	}
	
	public void clickOnSignButton(){
		System.out.println("User clicks on signin button");
		e=driver.findElement(By.xpath(prop.getProperty("btn_Signin")));
		e.click();
	}
	
	public void reloadDemographics(){
		String currenturl=driver.getCurrentUrl();
		
		if (currenturl.startsWith(DataObj.get("Demographics_url").toString()))
			driver.get(DataObj.get("url").toString());
	}

}
