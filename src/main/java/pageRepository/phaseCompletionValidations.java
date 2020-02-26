package pageRepository;

import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ObjectFactory;
import utils.StartUp;

public class phaseCompletionValidations extends StartUp{
	
	ObjectFactory objmap;
	public WebElement e,e1;
	String status;
	StartUp st = new StartUp();
	WebDriverWait wait = new WebDriverWait(driver,50);
		
	public phaseCompletionValidations(){
		
		System.out.println("Inside phase completed validations");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/phaseCompletion.properties");
	}


	//**************Function to verify the status of every phase item*******************//
	
	public Boolean checkPhaseItemStatus(String phaseType,String itemName) throws Exception{

		
		try{
			System.out.println("Inside check phase item status");
						
			switch(phaseType){
				case "Assessment":
					Thread.sleep(2000);
					e=driver.findElement(By.xpath("//div[contains(@class,'-menuItem-module-product') and contains(text(),'"+itemName+"')]"));
					Thread.sleep(2000);
					e=driver.findElement(objmap.getLocator("lbl_assessmentStatus"));
					break;
				
				case "Normal Course":
					Thread.sleep(2000);
					e=driver.findElement(By.xpath("//div[contains(@class,'innerListItem-module-module-item-title')]//span[contains(text(),'"+itemName+"')]"));	
					Thread.sleep(2000);	
					e=driver.findElement(objmap.getLocator("lbl_normalCourseStatus"));
					break;
				
			}
			
			
				System.out.println("Item Status captured from screen for '"+itemName+"'" +e.getText());
				status=e.getText();
				if(status.equalsIgnoreCase("Completed"))
				System.out.println("Item is completed");
				
			return true;
				
			}
			
			catch(NoSuchElementException ne){
				ne.printStackTrace();
				return false;
			}
			
		}


}
