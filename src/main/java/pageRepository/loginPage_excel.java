package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.jsoup.helper.HttpConnection.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.CommonMethods;
import utils.ObjectFactory;
import io.restassured.RestAssured;
import utils.StartUp;

public class loginPage_excel extends StartUp{

	ObjectFactory objmap;
	StartUp st = new StartUp();
	public Properties prop;
	WebElement e;
	String username,password;
	CommonMethods cm=new CommonMethods();
	String path = System.getProperty("user.dir")+"/src/main/java/dataRepository/logindata.xlsx";
	
	public loginPage_excel(){
		
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/LoginPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
		
		try {
			readexcel.setExcelFile(path, "logindata");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public void launchPage(){
		
		driver.get(DataObj.get("url").toString());
	}
	
	public void validateLoginPage(){
		String title=driver.getTitle();
		System.out.println("Page title :"+title);
        Assert.assertEquals("Incorrect Login page","Login to Kompass", title);
		
	}
	
	public void enterUserCredentials() throws Exception{
		
		username = readexcel.getCellData(1,0);
		password = readexcel.getCellData(1,1);
		
		System.out.println("username from excel: "+username);
		System.out.println("password from excel: "+password);
	    e=driver.findElement(objmap.getLocator("txt_Email"));
	    e.sendKeys(username);
	    e=driver.findElement(objmap.getLocator("txt_Password"));
	    e.sendKeys(password);
}
	
	public void clickOnSignButton() throws Exception{
		System.out.println("User clicks on signin button");
		e=driver.findElement(objmap.getLocator("btn_Signin"));
		e.click();
		Thread.sleep(1000);
		Assert.assertEquals("Incorrect user credentials",0,driver.findElements(By.xpath("//div[contains(@class,'error-box')]")).size());
		readexcel.deleteRow(path);
	}
	


}



