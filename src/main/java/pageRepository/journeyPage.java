package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;

import io.restassured.RestAssured;

import utils.StartUp;

public class journeyPage extends StartUp {
	
	WebDriver driver= getDriver();
	public Properties prop;
	WebElement e;
	int statusCode;
	
	public journeyPage(){
		System.out.println("Inside journey page constructor");
		this.prop=new Properties();
		FileInputStream objfile;
		try {
				objfile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/uiMap/JourneyPage.properties");
				prop.load(objfile);
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	}
	
	public void validatingJourneyPage(){
		
		//-----------Revisit this---------
		
		System.out.println("duplication of code in both welcome page and journey page");
	}
	
	public void clickPhaseItem() throws InterruptedException{
		int count=0,i;
		List<WebElement> lst;
		Thread.sleep(1000);
		
		count=driver.findElements(By.xpath(prop.getProperty("phaseitem_count"))).size();
		System.out.println("Number of phase items: "+count);
		lst=driver.findElements(By.xpath(prop.getProperty("phase_items")));
		System.out.println("size of list "+lst.size());
		lst.get(0).click();
		
		for(i=0;i<count-1;i++){
			System.out.println("value of i "+i);
						
			Thread.sleep(1000);
			//lst.get(i).click();
		
		statusCode=new HttpResponse().getStatus();
		System.out.println("Status code for current url "+statusCode);
		Thread.sleep(1000);
		e=driver.findElement(By.xpath(prop.getProperty("btn_nextitem")));
		if (e!=null){
			e.click();
		//System.out.println("clicked on next button");
					
		}
			
		}
		
		
		

}
	
	public void validatePhaseItem(){
		
		 
		
	}

}
