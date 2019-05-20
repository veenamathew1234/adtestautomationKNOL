package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.Before;


public class StartUp {
	
	  ObjectMapper mapper = new ObjectMapper();
	  JsonFactory f = new JsonFactory();
	  Map<String,Object> DataObj;
	  String filepath=System.getProperty("user.dir")+"/src/main/java/dataRepository/";
	  String testCaseName= new Object(){}.getClass().getEnclosingClass().getSimpleName();
	  public RemoteWebDriver driver;
	  String chromefilepath=System.getProperty("user.dir")+"/chromedriver";
	
	@Before
	public RemoteWebDriver setUp() throws JsonParseException, IOException{
		
		System.setProperty("webdriver.chrome.driver",chromefilepath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		beforeClass();
		return driver;
		
	}
	public void beforeClass() {
		  JsonParser jp;
		try {
			JsonParser jp = f.createParser(new File(filepath+"testData.json"));
			jp.nextToken();
			DataObj = mapper.readValue(jp, Map.class);
		}
		catch(){
			
		
		}
		   
		  

}
}
