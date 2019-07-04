package utils;

import java.util.Map;

import junit.framework.Assert;

public class CommonMethods extends StartUp {

	ObjectFactory objmap;
	
	public CommonMethods(){
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/CommonErrors.properties");
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
	}

}
