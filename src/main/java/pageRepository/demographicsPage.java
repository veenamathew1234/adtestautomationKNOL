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
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



import utils.ObjectFactory;
import utils.StartUp;

public class demographicsPage extends StartUp{
	
	int flag;
	ObjectFactory objmap;
	String currenturl;
	int statusCode;
	public Properties prop;
	WebElement e;
	HttpURLConnection huc = null;
	ExecutorService executor;
	public static List<String> errorList = new ArrayList<String>();
	
	
	public demographicsPage(){
		
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/DemographicsPage.properties");
	}
	
	public void validateDemographicsPageLoad(){
		
		currenturl=driver.getCurrentUrl();
		flag=0;
		if(currenturl.startsWith(DataObj.get("Demographics_url").toString()))
			flag=1;
		
	}
	
	public void pageRefresh() throws InterruptedException, IOException{
		
		driver.get(DataObj.get("url").toString());
		Thread.sleep(2000);
		
	
	}	
}


