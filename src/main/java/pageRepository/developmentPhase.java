package pageRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class developmentPhase extends StartUp {
	
	ObjectFactory objmap;
	StartUp st = new StartUp();
	List scormData;
	Boolean result=false;
	WebDriverWait scormwait = new WebDriverWait(driver,90);
	WebElement e;
	
	public developmentPhase(){
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/developmentPhase.properties");
		//Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	public boolean traverseThroughP2P_SCORM(String courseName) throws Exception
	{
		try
		
		{
			System.out.println("Inside traversing through P2P course");
		//switch frame to main frame
		e=scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("frame_mainSCORMFrame")));
		driver.switchTo().frame(e);
		
		System.out.println("Switch to main frame successful");
			
		//switch to second frame
		e=scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("frame_scormCourseSecondFrame")));
		driver.switchTo().frame(e);
		System.out.println("Switch to second frame successful");
		
		//switch to content frame
		
		e=scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("frame_scormContent")));
		driver.switchTo().frame(e);
		
		System.out.println("switched to content frame successfully");
		//click on StartCourse
		
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_StartScormCourse"))).click();
		
		System.out.println("started course");
		
		//Click on Wellness Quiz

		
		//scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_WellnessQuiz"))).click();
		WebElement e=scormwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'transition-group')]//div[contains(@class,'lesson__sidebar')]//div[contains(@class,'overview-sidebar')]//div[contains(text(),'Wellness Quiz')]")));
		if(e.isDisplayed())
		{
			System.out.println("Element displayed");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", e);
		}
		System.out.println("Wellness Quiz Clicked");
		
		//Click on Start Quiz
	
		
		Thread.sleep(2000);
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_StartQuiz"))).click();
		System.out.println("Successfully Started Quiz");
		
		//Answer the first question 
		
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_firstAnswer"))).click();
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_submit"))).click();
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_NextButton"))).click();
		System.out.println("Answered first question");
		
		//Answer the second question
		
		Thread.sleep(2000);
		
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_secondAnswer"))).click();
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_submit2"))).click();
		scormwait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_NextButton2"))).click();
		System.out.println("Answered second question");
		
		if(scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_completionStatus"))).getText().equalsIgnoreCase("100%"))
		{
			System.out.println("quiz successfully completed");
		}
		
		//switch back to platform Frame
		driver.switchTo().defaultContent();
		
		//click on Exit
		
		scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_ScormExit"))).click();
		e=scormwait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_ScormYesExit")));
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click()", e);
		System.out.println("Exited SCORM successfully");
		
		//Check Score Value
		
		
		return true;
		}
		catch(org.openqa.selenium.TimeoutException te)
		{
			Assert.assertNull("Could not traverse through SCORM course successfully", te);
			te.printStackTrace();
			return false;
		}
	}

}
