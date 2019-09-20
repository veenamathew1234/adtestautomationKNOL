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
}


