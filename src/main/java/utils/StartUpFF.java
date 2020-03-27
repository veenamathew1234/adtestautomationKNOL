package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cucumber.api.java.Before;
import io.restassured.RestAssured;


public class StartUpFF {
	
	  protected static Map<String,Object> DataObj;
	  protected static Map<String,Object> CourseDataObj;
	  protected static WebDriver driver;
	  String chromepath=System.getProperty("user.dir")+"/chromedriver";
	  String firepath=System.getProperty("user.dir")+"/geckodriver";
	  String filename;

	  public StartUpFF()
	  {
		  getDriver();
		  	  
	  }
		
	  protected WebDriver getDriver(){
		  

		 if(driver==null){
			 
				System.setProperty("webdriver.chrome.driver",chromepath);
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				options.addArguments("-incognito");
				//options.addArguments("window-size=1855x965");
				//options.addArguments("window-size=1536x754");
				options.addArguments("window-size=5000x5000");
				options.addArguments("headless");
		        //options.addArguments("--no-sandbox");
				options.setAcceptInsecureCerts(true);
				options.addArguments("--allow-insecure-localhost");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("disable-gpu");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setAcceptInsecureCerts(true);
				driver = new ChromeDriver(options);
				driver.manage().window().maximize(); 
		 
		 }
			return driver;
 
			
		}
	  
	  private static class BrowserCleanup implements Runnable {
		    public void run() {
		        close();
		    }
		}
	  
	  public static void close() {
		    try {
		    	System.out.println("Going to quit the browser");
		        driver.quit();
		        driver = null;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        
		    }
		}
	  
	  
	  
	  
	  
	  String filepath=System.getProperty("user.dir")+"/src/main/java/dataRepository/";
	  ObjectWriter writer;
	  ObjectMapper mapper = new ObjectMapper();

	public Map<String,Object> beforeClass(String dataFileName)
	{
		
		try 
		{
			ObjectMapper mapperForWrite = new ObjectMapper();
			writer = mapperForWrite.writer(new DefaultPrettyPrinter());
			JsonFactory fUser = new JsonFactory();
			JsonParser jp1;
			jp1 = fUser.createParser(new File(filepath + dataFileName));
			jp1.nextToken();
			DataObj = mapperForWrite.readValue(jp1, Map.class);
		}
			
			catch (IOException e) 
		{
				e.printStackTrace();
		}
		return DataObj;
	}
	
	public List datalist(String ListName)
	{
		ArrayList<Object> journeydetails=((ArrayList)DataObj.get(ListName));
		return journeydetails;
		
	}
	

}


