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
	Boolean result;
	
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
	
	public void validateJourneyPage() throws InterruptedException{
		
		int count=0;
		Thread.sleep(7000);
		 count=driver.findElements(By.xpath(prop.getProperty("phaseitem_count"))).size();
		 System.out.println("Number of phase items: "+count);
		 if(count!=0)
			 System.out.println("Sucessfully landed on journey page");
		
		}
	
	public boolean clickPhaseItem() throws InterruptedException{
		int i;
		List<WebElement> lst;
		Thread.sleep(1000);
		
		lst=driver.findElements(By.xpath(prop.getProperty("phase_items")));
		System.out.println("size of list "+lst.size());
		lst.get(0).click();
		
		for(i=0;i<=lst.size()-1;i++){
			Thread.sleep(1000);
			
			e=driver.findElement(By.xpath(prop.getProperty("btn_start")));
			if(e!=null){
				System.out.println("Start button found");
				e.click();
				Thread.sleep(4000);
				
				validatePhaseItem();
			}
			try{
				e=driver.findElement(By.xpath(prop.getProperty("btn_nextitem")));
				
				if(e.isDisplayed()){
					System.out.println("Next button found ");
					e.click();
					result = true;
				}
			}
			catch(Exception e){
				result=false;
			}
		}
		return result;
	
	}
	
	public void validatePhaseItem() throws InterruptedException{

		statusCode=new HttpResponse().getStatus();
		System.out.println("Status Code "+statusCode);
		
		if(statusCode==200){
			e=driver.findElement(By.xpath(prop.getProperty("btn_exit")));
			e.click();
			Thread.sleep(1000);
			e=driver.findElement(By.xpath(prop.getProperty("btn_popupexit")));
			if(e!=null)
				e.click();
			Thread.sleep(2000);
			
		}

	}
		
}

