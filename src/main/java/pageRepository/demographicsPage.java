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
		System.out.println("hellooo");
		//Enter Gender
		WebElement e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'personalDetails-form-field input-field')][1]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click();  

		
		
		//Enter Country
		
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][1]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click();  
		
		//Enter Education
		
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][3]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
		
		//Enter Industry
		
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][5]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
		
		//Enter Job Category
		
        e=driver.findElement(By.xpath("//div[contains(@class,'signup-container')]//div[contains(@class,'signup-right')]//form//div[contains(@class,'demographics-container skin-grey2')]//div[contains(@class,'demographicDetails-form-field input-field')][7]//div[contains(@class,'select-wrapper')]//input[contains(@class,'select-dropdown dropdown-trigger')]"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(e));
        e.click(); 
		
		//WorkExperience
		
        driver.findElement(By.xpath("//div[contains(@class,'demographicDetails-form-field-custom')][1]//div[contains(@class,'experience-container')]//div[contains(@class,'exp-options-container')]//div[contains(@id,'ei-option-22')]/input")).click();
		
		//People Management
		
		driver.findElement(By.xpath("//div[contains(@class,'demographicDetails-form-field-custom')][2]//div[contains(@class,'experience-container')]//div[contains(@id,'ei-option-280')]/input")).click();
		
		//Next Button
		
		driver.findElement(By.xpath("//div[contains(@class,'next-button-container')]/button")).click();
		
	}
}


