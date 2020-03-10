package pageRepository;

import junit.framework.Assert;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
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
		

//**************Function to verify the status of every phase item*******************//
	
	
public Boolean checkPhaseItemStatus(String phaseType,String itemName) throws Exception{

		
		try{
			System.out.println("Inside check phase item status");
						
			switch(phaseType){
				case "Assessment":
					e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'menuItem-module-text-content')]//*[(text()='"+itemName+"')]//following::span[contains(@class,'menuItem-module-completed-status')]")));
					break;
				
				case "Normal Course":
					e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'innerListItem-module-text-container')]//*[(text()='"+itemName+"')]//following::span[contains(@class,'innerListItem-module-completed-label')]")));
					break;
				
			}
			
			
				System.out.println("Item Status captured from screen for '"+itemName+"'" +e.getText());
				status=e.getText();
				if(status.equalsIgnoreCase("Completed")){
					System.out.println("Item is completed");
					status="";
				}
						
				
			return true;
				
			}
			
			catch(NoSuchElementException ne){
				Assert.assertNull("Completion state of "+itemName+" is not found", ne);
				ne.printStackTrace();
				return false;
			}
		catch(TimeoutException te)
		{
			
			Assert.assertNull("Completion state of "+itemName+" is not found", te);
			te.printStackTrace();
			return false;
		}

			
		}



}
