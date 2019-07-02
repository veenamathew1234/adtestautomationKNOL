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
	StartUp st = new StartUp();
	String currenturl;
	int statusCode;
	public Properties prop;
	WebElement e;
	HttpURLConnection huc = null;
	ExecutorService executor;
	public static List<String> errorList = new ArrayList<String>();
	
	
	public demographicsPage(){
		
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/DemographicsPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	public void validateDemographicsPageLoad(){
		
		System.out.println("Validate Demographics Page");
		currenturl=driver.getCurrentUrl();
		flag=0;
		if(currenturl.startsWith(DataObj.get("Demographics_url").toString()))
		flag=1;
		Assert.assertEquals("Incorrect Demographics Page",1,flag);
		
	}
	
	public void pageRefresh() throws InterruptedException, IOException{
		
		driver.get(DataObj.get("url").toString());
		Thread.sleep(2000);
		List<WebElement> l=driver.findElements(By.xpath("//div[contains(@class,'_1igs3lz module-s6sm1kqha136yk484neh1qe46a8r2c8hrnkwhdtyh7dazwwzct8mfaaudbrfety6hwc7rpaa47wq4r2mjyjzntkzdshar83gg5p9fs-userError-module-error-heading')]"));
		Assert.assertEquals("Error while refreshing page", 0, l.size());
		
	}	
}


