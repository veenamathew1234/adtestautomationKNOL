package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.text.log.SysoCounter;

import junit.framework.Assert;

public class CommonMethods extends StartUp {

	ObjectFactory objmap;
	StartUp st = new StartUp();
	
	public CommonMethods(){
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/CommonErrors.properties");
	}
	
	
	
	public String executingJavaScript(String script)
	{
		System.out.println("inside executingJavaScriptMethod");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 String s=js.executeScript(script).toString();
		 System.out.println("The result after javascript execution="+s);
		 return script;
	}
	
	public void checkErrorComponents() throws Exception
	{

			System.out.println("Inside checkErrorComponents");
			if(driver.findElements(objmap.getLocator("lbl_errorcom1")).size()!=0)
			{
				Assert.assertEquals("Error 1 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom2")).size()!=0)
			{
				
				Assert.assertEquals("Error 2 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom3")).size()!=0)
			{
				
				Assert.assertEquals("Error 3 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom4")).size()!=0)
			{
				Assert.assertEquals("Error 4 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom5")).size()!=0)
			{
				Assert.assertEquals("Error 5 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom6")).size()!=0)
			{
				Assert.assertEquals("Error 6 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom7")).size()!=0)
			{
				Assert.assertEquals("Error 7 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom8")).size()!=0)
			{
				Assert.assertEquals("Error 8 ", 0,1);
			}
			if(driver.findElements(objmap.getLocator("lbl_errorcom9")).size()!=0)
			{
				Assert.assertEquals("Error 9 ", 0,1);
			}
		
		
	}
	
	public void verifyElementPresent(String locator,boolean objfactorylocator,String notPresentMessage) throws Exception
	{
		
		int size=0;
		if(objfactorylocator)
		{
			String l="\""+locator+"\"";
			System.out.println("inside verify with Object factory"+l);
			size=driver.findElements(objmap.getLocator(l)).size();
			System.out.println("size="+size);
		}
		
		else
		{
			size=driver.findElements(By.xpath(locator)).size();
		}
		Assert.assertNotSame(notPresentMessage, 0, size);
		
	}
	
	public boolean changeToNewUser()
	  {
		Map<String,Object> DataObj=st.beforeClass("testData.json");
		 String username= DataObj.get("email").toString();
		 String user_namefirsthalf=username.substring(0,username.lastIndexOf("_")+1);
		 
		 int user_num = Integer.parseInt(username.substring(username.lastIndexOf("_")+1,username.lastIndexOf("@")));
		 
		 String user_namesecondhalf=username.substring(username.lastIndexOf("@")+1, username.length());
		 user_num++;
		 String newUserName=user_namefirsthalf.concat(Integer.toString(user_num).concat("@").concat(user_namesecondhalf));
		 DataObj.replace("email", username, newUserName);
		 try 
		 {
			 mapper.writeValue(new File(filepath+"testData.json"), DataObj);
		 }
		 catch (JsonProcessingException e1) {
			 e1.printStackTrace();
			 return false;
		 } 
		 catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		  return true;
	  }

}
