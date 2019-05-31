package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import utils.StartUp;

public class demographicsPage extends StartUp{
	
	int flag;
	String currenturl;
	StartUp st = new StartUp();
	Map<String,Object> DataObj=st.beforeClass();
	public Properties prop;
	WebElement e;
	
	public demographicsPage(){
		
		this.prop=new Properties();
		FileInputStream objfile;
			try {
				objfile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/uiMap/DemographicsPage.properties");
				prop.load(objfile);
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	}
	
	public void validateDemographicsPageLoad(){
		
		currenturl=driver.getCurrentUrl();
		flag=0;
		if(currenturl.startsWith(DataObj.get("Demographics_url").toString()))
			flag=1;
		
	}
	
	public void pageRefresh(){
		
		driver.get(DataObj.get("url").toString());
	}

}
