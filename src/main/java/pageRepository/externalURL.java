package pageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class externalURL extends StartUp {

	ObjectFactory objmap;
	StartUp st = new StartUp();
	List externalURLList;
	WebElement e,e1;
	Map<String,Object> courseObj;
	CommonMethods cm=new CommonMethods();
	
	public externalURL()
	{
		System.out.println("Inside External page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/externalURL.properties");
		courseObj=st.beforeClass("coursedata.json");
		
	}
	
	public void openExternalURL(String itemName) throws IOException
	{
		try
		{
		ArrayList courseDetails=(ArrayList) courseObj.get("Normal Course");
		for(int i=0;i<courseDetails.size();i++)
		{
			Map<String,Object>q1=(Map<String, Object>) courseDetails.get(i);
			System.out.println("ItemName from Json="+q1.get("itemName"));
			if(q1.get("itemName").toString().equalsIgnoreCase(itemName))
			{
				if(q1.get("openInNewTab")!=null)
				{
					String parentWindow = driver.getWindowHandle();
					driver.findElement(objmap.getLocator("btn_OpenInNewTab")).click();

					for(String childWindow:driver.getWindowHandles())
					if(!childWindow.equals(parentWindow))
					{
						
					    driver.switchTo().window(parentWindow);
					}
				}
			}
			}
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Unable to open an external URL in development phase. For QA-function to check openExternalURL ", e);
		}
	}
	
}
