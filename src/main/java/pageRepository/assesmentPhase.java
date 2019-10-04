package pageRepository;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class assesmentPhase extends StartUp{
	
	ObjectFactory objmap;
	WebElement e;
	StartUp st=new StartUp();
	
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
		System.out.println("inside submit test sim");
		Thread.sleep(2000);
		driver.switchTo().frame(1);
		driver.findElement(objmap.getLocator("btn_TestSimSubmit")).click();
		driver.switchTo().parentFrame();
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
		
		//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(objmap.getLocator("btn_start"))));
		Thread.sleep(2000);
		e=driver.findElement(objmap.getLocator("btn_start"));
			if(e!=null){
				System.out.println("Start button found");
				e.click();
				Thread.sleep(4000);
				return true;
			}
			return false;
		
	}
	
	
	/* Function Name : validatePhaseItem
	 * Parameter:Name of the simulation that is expected to be launched. It is fetched from testdata.json
	 * 
	 * 
	 * 
	 */
	public boolean validateAssessmentItem()
	{
		return true;
	}
		
	
	
	/*
	 * Function Name : exitPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to exit an existing assessment/simulation
	 * Return Value : Boolean
	 * 
	 */
	
	
	public void exitPhaseItem() throws Exception{

		int statusCode=new HttpResponse().getStatus();
		System.out.println("Status Code "+statusCode);
		
		if(statusCode==200){
			System.out.println("hello 200");
			//explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(objmap.getLocator("btn_exit"))));
			e=driver.findElement(objmap.getLocator("btn_exit"));
			e.click();
			Thread.sleep(1000);
			//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(objmap.getLocator("btn_popupexit"))));
			e=driver.findElement(objmap.getLocator("btn_popupexit"));
			if(e!=null)
				e.click();
			Thread.sleep(2000);
			
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
		
		System.out.println("Inside assessment report");
		
		try{
			driver.findElement(objmap.getLocator("btn_GotoMeasures")).click();
			Thread.sleep(2000);
			
			int AssessmentResult=driver.findElements(objmap.getLocator("lbl_Assessmentresult")).size();
			Assert.assertEquals("Assessment Reult Page is not found", 1,AssessmentResult );
			Thread.sleep(2000);
			
			WebElement detailedanalysis= driver.findElement(objmap.getLocator("lnk_detailedanalysis"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailedanalysis);
			Thread.sleep(1000); 
			detailedanalysis.click();
			
			//function to Download report
			downloadAssessmentReport();
			return true;
			
		}
		catch(NoSuchElementException e)
		{
			Assert.assertNull("Exception in verify Assessment report",e);
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean downloadAssessmentReport() throws Exception{
		
		try{
			
			WebElement assessmentreport=driver.findElement(objmap.getLocator("btn_downloadassessmentreport"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assessmentreport);
			Thread.sleep(1000); 
			assessmentreport.click();
			Thread.sleep(3000);
			
			WebElement backarrow=driver.findElement(objmap.getLocator("lnk_assessmentreportbackarrow"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", backarrow);
			Thread.sleep(1000); 
			backarrow.click();
			
			return true;
		}
		
		catch(NoSuchElementException e){
			Assert.assertNull("Exception in download assessment report", e);
			return false;
		}
		
		
		
	}
	
		
		public boolean assessmentPhaseNavigation()
		{
			return true;
		}
	
}
