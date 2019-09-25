package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpGet;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class demographicsPage extends StartUp{
	
	int flag;
	ObjectFactory objmap;
	StartUp st = new StartUp();
	String currenturl;
	int statusCode;
	public Properties prop;
	WebElement e;
	HttpURLConnection huc = null;
	ExecutorService executor;
	CommonMethods cm=new CommonMethods();
	public static List<String> errorList = new ArrayList<String>();
	
	
	public demographicsPage(){
		
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/DemographicsPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	public void validateDemographicsPageLoad(){
		
		currenturl=driver.getCurrentUrl();
		flag=0;
		if(currenturl.startsWith(DataObj.get("Demographics_url").toString()))
		flag=1;
		Assert.assertEquals("Incorrect Demographics Page",1,flag);
		
	}
	
	public void pageRefresh() throws Exception{
		
		driver.get(DataObj.get("url").toString());
		Thread.sleep(2000);
		cm.checkErrorComponents();		
	
}


public void fillDemographicsPage() throws InterruptedException

    {
		WebDriverWait wait;
        System.out.println("Inside Demographics Page");

        WebElement e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'personalDetails-form-field input-field')][1]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click();  
        driver.findElement(By.xpath("//li[contains(@id,'select-options')]/span[contains(text(),'Male')]")).click();

        //Enter Country
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][1]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
        driver.findElement(By.xpath("//li[contains(@id,'select-options')]/span[(text()='Afghanistan')]")).click();
        
        //Enter Education
 
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][3]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
        driver.findElement(By.xpath("//li[contains(@id,'select-options')]/span[(text()='Less than a high school diploma')]")).click();

        //Enter Industry

        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][5]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
        driver.findElement(By.xpath("//li[contains(@id,'select-options')]/span[(text()='Agriculture, Forestry and Fishing')]")).click();

        //Enter Job Category
        
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][6]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
        driver.findElement(By.xpath("//li[contains(@id,'select-options')]/span[(text()='Individual Contributors')]")).click();


        //WorkExperience

        driver.findElement(By.xpath("//div[contains(@class,'demographicDetails-form-field-custom')][1]//div[contains(@class,'experience-container')]//div[contains(@class,'exp-options-container')]//div[contains(@id,'ei-option-22')]")).click();

       //People Management

        driver.findElement(By.xpath("//div[contains(@class,'demographicDetails-form-field-custom')][2]//div[contains(@class,'experience-container')]//div[contains(@class,'exp-options-container')]//div[contains(@id,'ei-option-279')]")).click();

       //Next Button 

        driver.findElement(By.xpath("//div[contains(@class,'next-button-container')]/button")).click();
        Thread.sleep(2000);
        
        //policy check box
        
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'policy-container')]//div[contains(@class,'policy-box')]//div[contains(@class,'agree-conditions')]//label//span[contains(@class,'agree-conditions-text skin-grey1')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click();
        
        //Proceed button
        
        e=driver.findElement(By.xpath("//button[(@id='policySubmitBtn')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click();

    }
}


