package pageRepository;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class feedbackPages extends StartUp {
	
	ObjectFactory objmap;
	StartUp st = new StartUp();
	List feedbackData;
	Boolean result=false;
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	public feedbackPages(){
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/feedbackPages.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	
	
	/*
	 * Function Name : fillFeedback
	 * Function Parameters: phaseName
	 * Description : Function used to fill the phaseLevel feedback 
	 * Return Value : Boolean
	 * 
	 */

	public boolean fillFeedback(String phaseName,List feedbackData) throws Exception {
		System.out.println("phase name in f/b function is "+phaseName);
		System.out.println("inside fill f/b function");
		Boolean result[]= {false};
		//Thread.sleep(1000);
		 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_feedbackHeading")));
		feedbackData.forEach((feedback)->{
			 Map<String,Object> fb=(Map<String, Object>) (feedback);
			if(fb.get("phaseName").toString().equalsIgnoreCase(phaseName))
			{
				System.out.println("Phase names matched");
				try 
				{
				System.out.println("indide f/b try block");	
				result[0]=enterFeedbackDetailsForm(fb);
				verifyThankYouFeedbackPage();
			}
				catch(Exception e)
				{
					e.printStackTrace();
					//return false;
				}
				
				
			}
			});
		
		return true;
		
	}
	
	
	
	
	/*
	 * Function Name : enterFeedbackDetailsForm
	 * Function Parameters: Feedback data Map
	 * Description : Function used to enter the feedback details.
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean enterFeedbackDetailsForm( Map<String,Object> fb) throws Exception
	{
		System.out.println("phaseName found");
		//feedbackD[0]=fb;
		
		try
		{
		String overallRating=fb.get("overallRating").toString();
		String recommend=fb.get("recommendValue").toString();
		driver.findElement(By.xpath("//div[contains(@class,'module-4d12mstux7kz6kx16r2e8csqgx5wze318bdeea8bnug4byhh8nu7fzfs82ck8e7ntvgnfk478xt475dbahu496asmz7xz13s9thqknz-starRating-module-individual-star')]["+overallRating+"]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'_508t3c module-2dds1v9tuexvbh1njfs1gpvgf7rk981dc2hutw3hp84jkv6ufr5d5j5vxwfbv5czk9m4gtj1fmdmv6pmdt935rz5jz771cpkxaade7y-scaleRating-module-individual-score')]["+recommend+"]")).click();
		
		//--- Scrolling to accomodate the submit button-----
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(objmap.getLocator("btn_submitPhaseFeedback")));
		Thread.sleep(2000);
		driver.findElement(objmap.getLocator("btn_submitPhaseFeedback")).click();
		Thread.sleep(2000);
		return true;
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(""+fb.get("phaseName")+" feedback form issue while user enters data", ne);
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
		

	/*
	 * Function Name : thankYouForFeedbackPage
	 * Function Parameters: NA
	 * Description : Function used to verify the thankYou Feedback page.
	 * Return Value : Boolean
	 * 
	 */
	public boolean verifyThankYouFeedbackPage() throws Exception
	{
		
		int thankYouPage=driver.findElements(objmap.getLocator("lbl_ThankYouFeedbackPage")).size();
		Assert.assertEquals("Thank you feedback page not displaying", 1, thankYouPage);
		Thread.sleep(2000);
		return true;
	}

	
//	/*
//	 * Function Name : verifyAssessmentreport
//	 * Function Parameters: NA
//	 * Description : Function used to verify the assessment report
//	 * Return Value : Boolean
//	 * 
//	 */
//	
//	public boolean verifyAssessmentReport() throws Exception{
//		
//		driver.findElement(objmap.getLocator("btn_GotoMeasures")).click();
//		Thread.sleep(2000);
//		
//		int AssessmentResult=driver.findElements(objmap.getLocator("lbl_Assessmentresult")).size();
//		System.out.println("Size of Assessment result" +AssessmentResult);
//		Assert.assertEquals("Assessment Reult Page is not found", 1,AssessmentResult );
//		Thread.sleep(2000);
//		return true;
//		
//		
//	}
//	
	
	
	public boolean enterItemFeedbackStars(String stars) throws Exception
	{
		
		wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_itemFeedback")));
		driver.findElement(objmap.getLocator("lbl_itemFeedback")).click();
		driver.findElement(By.xpath("//div[contains(@class,'rating-module-star')]["+stars+"]")).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_closeItemFeedback")));
		driver.findElement(objmap.getLocator("lbl_closeItemFeedback")).click();
		
		return true;
	}
	
	public boolean likeDislikeDevItemfeedback(String feedback)
	{
		try
		{
			System.out.println("like Dislike Development Phase Item feedback");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_Howdidyoulikethecontent")));
			switch(feedback)
			{
			case "like":
				System.out.println("in like");
				driver.findElement(objmap.getLocator("btn_FeedbackDevPhaseItemLIKE")).click();
				break;
				
			case "dislike":
				driver.findElement(objmap.getLocator("btn_FeedbackDevPhaseItemDISLIKE")).click();
				break;
			}
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_closeFeedbackPhaseItemDev")));
			driver.findElement(objmap.getLocator("btn_closeFeedbackPhaseItemDev")).click();
			//Thread.sleep(3000);
			System.out.println("closing like dislike");
			return true;
			
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Cannot find the thumbs up /down in development phase item", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNull("General exception in the Like/Dislike Dev Phase item", e);
			return false;
		}
	}


}
