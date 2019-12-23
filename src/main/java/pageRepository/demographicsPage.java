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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class demographicsPage extends StartUp{
	
	Boolean flag;
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
	WebDriverWait wait=new WebDriverWait(driver,30);
	
	
	public demographicsPage(){
		
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/DemographicsPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	public void validateDemographicsPageLoad(){
		
		currenturl=driver.getCurrentUrl();
		flag=false;
		if(currenturl.startsWith(DataObj.get("Demographics_url").toString()))
		flag=true;
		Assert.assertTrue("Demographics page not loading", flag);
		
	}
	
	public void pageRefresh() throws Exception{
		
		driver.get(DataObj.get("url").toString());
		Thread.sleep(2000);
		cm.checkErrorComponents();		
	
}

	public void fillDemographicsPage() throws Exception

    {
		try{
			
	        System.out.println("Inside Demographics Page");
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("dropdown_gender"))).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_gender"))).click();
	
	        //Enter Country
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("dropdown_country"))).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_country"))).click();
	       
	        //Enter Education
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("dropdown_education"))).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_education"))).click();

	        //Enter Industry

	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("dropdown_industry"))).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_industry"))).click();

	        //Enter Job Category
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("dropdown_jobcategory"))).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_jobcategory"))).click();


	        //WorkExperience

	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_workexperience"))).click();

	        //People Management

	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("option_peoplemanagement"))).click();

	        //Next Button 
	        wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_next"))).click();
	        
	        //policy check box
	         wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("checkbox_policy"))).click();
	        
	        //Proceed button
	        
	         wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_proceed"))).click();
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("One of the elements is not found in demographics/policy page", ne);
			ne.printStackTrace();
			
		}
		catch(TimeoutException te)
		{
			Assert.assertNull("One of the elements is not found in demographics/policy page", te);
			te.printStackTrace();
			
		}
		

    }
}


