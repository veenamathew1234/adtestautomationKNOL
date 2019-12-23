package pageRepository;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class assesmentPhase extends StartUp{
	
	ObjectFactory objmap;
	WebElement e;
	StartUp st=new StartUp();
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	public assesmentPhase(){
		System.out.println("Inside assessment page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/assessmentPhase.properties");
	}

	
	/*
	 * Function Name : submitTestSim
	 * Function Parameters: None.
	 * Description : Function used to start/launch an assessment item
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean submitTestSim() throws Exception
	{
			Boolean flag=false;
			System.out.println("Inside submit test sim");
			driver.switchTo().frame(1);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_TestSimSubmit"))).click();
			driver.switchTo().parentFrame();
			flag=true;
			Assert.assertTrue("Submit button inside test sim is not found",flag);
			return true;

	}
	
	
	/*
	 * Function Name : launchPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to start/launch an assessment item
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean launchAssessmentItem() throws Exception
	{
		try{
			
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_start")));
			e=driver.findElement(objmap.getLocator("btn_start"));
				if(e!=null){
					System.out.println("Start button found");
					e.click();
					Thread.sleep(4000);
					return true;
				}
				return false;
				
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Start button for assessment phase is not found", ne);
			ne.printStackTrace();
			return false;
		}
			
			catch(TimeoutException te){
				Assert.assertNull("Start button for assessment phase is not found", te);
				te.printStackTrace();
				return false;
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		
	}
	
	/*
	 * Function Name : validate and exitPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to exit an existing assessment/simulation
	 * Return Value : void
	 * 
	 */
	
	
	public boolean validateAndExitPhaseItem(String assessmentType){
		
		try {
		if(assessmentType.equalsIgnoreCase("Test Sim"))
		{
			System.out.println("Inside Test SIm ");
			
				submitTestSim();
		}
			
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_exit"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_popupexit")));
			e=driver.findElement(objmap.getLocator("btn_popupexit"));
			if(e!=null)
				e.click();
			Thread.sleep(2000);
			return true;
		} 
		catch (NoSuchElementException ne) {
			Assert.assertNull("Button to exit or exit pop up from simulation/game is not found",ne );
			ne.printStackTrace();
			return false;
		}
		
		catch (TimeoutException te) {
			Assert.assertNull("Button to exit or exit pop up from simulation/game is not found",te );
			te.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * Function Name : verifyAssessmentreport
	 * Function Parameters: NA
	 * Description : Function used to verify the assessment report
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean verifyAssessmentReport() throws Exception{
		
		Boolean flag=false;
		System.out.println("Inside assessment report");
		
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_GotoMeasures"))).click();
			Thread.sleep(2000);
			
			int AssessmentResult=driver.findElements(objmap.getLocator("lbl_Assessmentresult")).size();
			Assert.assertEquals("Assessment Result Page is not found", 1,AssessmentResult );
						
			WebElement detailedanalysis= wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lnk_detailedanalysis")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailedanalysis);
			Thread.sleep(1000); 
			detailedanalysis.click();
			
			//function to Download report
			downloadAssessmentReport();
			return true;
			
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Error while verifying Assessment report",ne);
			ne.printStackTrace();
			return false;
		}
		catch(TimeoutException te)
		{
			Assert.assertNull("Error while verifying Assessment report",te);
			te.printStackTrace();
			return false;
		}
	}
	
	public boolean downloadAssessmentReport() throws Exception{
		
		try{
			
			WebElement assessmentreport=wait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_downloadassessmentreport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assessmentreport);
			Thread.sleep(1000); 
			assessmentreport.click();
									
			WebElement backarrow=wait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("lnk_assessmentreportbackarrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", backarrow);
			Thread.sleep(1000); 
			backarrow.click();
			
			return true;
		}
		
		catch(NoSuchElementException ne){
			Assert.assertNull("Error in download assessment report", ne);
			ne.printStackTrace();
			return false;
		}
		catch(TimeoutException te){
			Assert.assertNull("Error in download assessment report", te);
			te.printStackTrace();
			return false;
		}
		
		
	}

}
