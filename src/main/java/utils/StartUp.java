package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cucumber.api.java.Before;


public class StartUp {
	
	  Map<String,Object> DataObj;
	  String filepath=System.getProperty("user.dir")+"/src/main/java/dataRepository/";
	  public RemoteWebDriver driver;
	  String chromefilepath=System.getProperty("user.dir")+"/chromedriver";
	  ObjectWriter writer;
	
	@Before
	public RemoteWebDriver setUp(){
		
		System.setProperty("webdriver.chrome.driver",chromefilepath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		beforeClass();
		return driver;
		
	}

	public Map<String,Object> beforeClass()
	{
		try 
		{
			ObjectMapper mapperForWrite = new ObjectMapper();
			writer = mapperForWrite.writer(new DefaultPrettyPrinter());
			JsonFactory fUser = new JsonFactory();
			JsonParser jp1;
			jp1 = fUser.createParser(new File(filepath+"testData.json"));
			jp1.nextToken();
			DataObj = mapperForWrite.readValue(jp1, Map.class);
		}
			
			catch (IOException e) 
		{
				e.printStackTrace();
		}
		return DataObj;
	}

}
