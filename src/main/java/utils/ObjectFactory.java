package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectFactory {
	 Properties properties;
	  public ObjectFactory(String mapFile) 
	  {
		  properties = new Properties();
	      try 
	      {
	    	  FileInputStream Master = new FileInputStream(mapFile);
	    	  properties.load(Master);
	    	  Master.close();
	      }
	      catch(IOException e) 
	      {
	           System.out.println(e.getMessage());
	      }
	  }

	 public By getLocator(String ElementName) throws Exception 
	 {
		String locator = properties.getProperty(ElementName);
        String locatorType = locator.split("-->")[0];
        String locatorValue = locator.split("-->")[1];
          if(locatorType.toLowerCase().equals("id"))
                return By.id(locatorValue);
          else if(locatorType.toLowerCase().equals("name"))
                return By.name(locatorValue);
          else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
                return By.className(locatorValue);
          else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
                return By.className(locatorValue);
          else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
                return By.linkText(locatorValue);
          else if(locatorType.toLowerCase().equals("partiallinktext"))
                return By.partialLinkText(locatorValue);
          else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
                return By.cssSelector(locatorValue);
          else if(locatorType.toLowerCase().equals("xpath"))
          {	
                return By.xpath(locatorValue);
          }
          else
                  throw new Exception("Locator type '" + locatorType + "' not defined!!");
      }
	 
	 public String getLocatorType(String ElementName) throws Exception 
	 {
        //Read value using the logical name as Key
        String locator = properties.getProperty(ElementName);
        //Split the value which contains locator type and locator value
        String locatorType = locator.split("-->")[0];
        return locatorType;
     }
	 
	 public String getLocatorValue(String ElementName) throws Exception 
	 {
        //Read value using the logical name as Key
        String locator = properties.getProperty(ElementName);
        //Split the value which contains locator type and locator value
        String locatorValue = locator.split("-->")[1];
        return locatorValue;
     }
	 
	 public String getElement(String ElementName) throws Exception 
	 {
        //Read value using the logical name as Key
        String locator = properties.getProperty(ElementName);
        //Split the value which contains locator type and locator value
        String locatorValue = locator.split("-->")[1];
        return locatorValue;
     }


}
