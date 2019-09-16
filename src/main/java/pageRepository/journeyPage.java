package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.log.SysoCounter;

import io.restassured.RestAssured;
import utils.CommonMethods;
import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class journeyPage extends StartUp {

	WebDriverWait explicitWait = new WebDriverWait(driver,30);
	ObjectFactory objmap;
	StartUp st = new StartUp();
	public Properties prop;
	WebElement e;
	int statusCode;
	Boolean result;
	List journeyInfo;
	List feedbackData;
	List assignmentData;
	int index;
	boolean webElementPresent;
	CommonMethods cm=new CommonMethods();
	assesmentPhase asp=new assesmentPhase();
	feedbackPages fbp=new feedbackPages();
	assignment assgn = new assignment();
	Quiz qz=new Quiz();
	externalURL ext=new externalURL();
	
	public journeyPage(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/JourneyPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");
//		Map<String,Object>CourseDataObj=st.beforeClass("coursedata.json");
	}
	
	
	/*
	 * Function Name : validateJourneyPage
	 * Function Parameters: None
	 * Description : To validate if the user has landed on journey page successfully
	 * Return Value : None
	 * 
	 */
	
	public void validateJourneyPage() throws Exception{
		
		int count=0;
		Thread.sleep(4000);
		 count=driver.findElements(objmap.getLocator("phaseitem_count")).size();
		 System.out.println("Number of phase items: "+count);
		 if(count!=0)
			 System.out.println("Sucessfully landed on journey page");
		 Assert.assertNotSame("Journey Landing page not loaded properly",0, count);
		
		}
	
	
	/*
	 * Function Name : navigateThroughPhases
	 * Function Parameters: None
	 * Description : To validate if the user is able to navigate through various phases in the journey.
	 * 				Data is taken from testData.json. After navigating through phase items in each phase ,
	 * 				user goes to back to the dashboard and clicks on the next phase tab
	 * Return Value : Boolean
	 * 
	 */
	
	public void navigateThroughPhases() throws Exception
	{
		
		try
		{
			
			
		journeyInfo=datalist("journeyDetails");
		if(DataObj.get("PhaseFeedbackDetails")!=null)
		{
			feedbackData=datalist("PhaseFeedbackDetails");
		}
	
		/*
		 * journeyInfo contains data from the testData.json
		 */
		
		journeyInfo.forEach((phase) -> {
			try {
				
				 Map<String,Object> phaseMap=(Map<String, Object>) (phase);
				 String phaseName=phaseMap.get("phaseName").toString();
				 System.out.println("Phase name to be clicked on next is "+phaseName);
				// cm.verifyElementPresent("//div[contains(@class,'content-module-individual-tab')]//div[contains(text(),'"+phaseName+"')]", false,"next "+phaseName+" tab to be clicked not found");
				 WebElement e1=driver.findElement(By.xpath("//div[contains(@class,'content-module-individual-tab')]//div[contains(text(),'"+phaseName+"')]"));
				 e1.click();
				 //User navigates through the phase items of the particular phase	 
				 String phaseType=phaseMap.get("phaseType").toString();
				 System.out.println("Phase type to be clicked on next is "+phaseType);

				 navigateThroughPhaseItem(phaseType);
				 Thread.sleep(7000);
				 if(phaseMap.get("PhaseFeedback").toString().equalsIgnoreCase("Yes"))
				 {
					 System.out.println("Inside feedback phase submission");
					 Thread.sleep(2000);
					 fbp.fillFeedback(phaseName,feedbackData);
				 }
			 clickOnHomeButton(phaseType);
			 
			} 
			catch(NoSuchElementException ne)
			{
				 Map<String,Object> phaseMap1=(Map<String, Object>) (phase);
				 String phaseName1=phaseMap1.get("phaseName").toString();
				 System.out.println("phaseName1="+phaseName1);
				Assert.assertNull("Phase Tab-"+phaseName1+" in the landing page not clickable or not found", ne);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

	/*
	 * Function Name : navigateThroughPhaseItem
	 * Function Parameters: phaseType which is passed from the navigateThroughPhase function
	 * Description : Function used to navigate through various phase items of the passed "phase"
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean navigateThroughPhaseItem(String phaseType) throws Exception{
		int i;
		//------retrieve appropriate phase item type for phase types and click on it-------- 
		List<WebElement> phaseItems=returnPhaseItemsForPhaseType(phaseType);
		Thread.sleep(5000);
		phaseItems.get(0).click();
	
		//-----------launching phases--------------------
		
		if(phaseType.equalsIgnoreCase("Assessment"))
		{
			navigateThroughAssessmentPhase();
		}
		
		if(phaseType.equalsIgnoreCase("NormalCourse")||phaseType.equalsIgnoreCase("R2S"))
		{
		for(i=0;i<=phaseItems.size()-1;i++){
			
			navigateThroughDevelopmentPhase(phaseType);
									
				if(i<phaseItems.size()-1)
					result=clickOnNextPhaseItem();
				}
		}
			System.out.println("outside");
					
		
		
		return true;
	
	}
	
	
	/*
	 * Function Name : navigateThroughDevelopmentPhase
	 * Function Parameters: 
	 * Description : Function used to navigate through various phase items of the development phase
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean navigateThroughDevelopmentPhase(String phaseType) throws Exception
	{
		Boolean result=false;
		if(phaseType.equalsIgnoreCase("NormalCourse"))
		{
			System.out.println("Inside Normal course");
			Thread.sleep(5000);
			WebElement ee=driver.findElement(objmap.getLocator("lbl_coursenamesidebar"));
			
			
			String courseName=ee.getText();
			System.out.println("CourseName from screen="+courseName);
			
			//-----function to traverse through the courses listed in test data-------
			result=traverseThroughCourse(courseName);
						
			}
		return result;
	}
	
	
	/*
	 * Function Name : navigateThroughAssessmentPhase
	 * Function Parameters: 
	 * Description : Function used to navigate through various phase items of the assessment phase
	 * Return Value : Boolean
	 * 
	 */
	
	
	public boolean navigateThroughAssessmentPhase()
	{
		List AssessmentNames=datalist("Assessments");
		AssessmentNames.forEach((assessment)->{
			Map<String,Object> assessmentDetail=(Map<String, Object>) (assessment);
			String assessmentType=assessmentDetail.get("AssessmentType").toString();
			System.out.println("assess Type= "+assessmentType);
			try
			{
			//Thread.sleep(2000);	
			if(launchPhaseItem()==true)
				{
					System.out.println("inside start button found");
					cm.checkErrorComponents();
					validateAndExitPhaseItem(assessmentType);
					if(assessmentDetail.get("ItemFeedbackStars")!=null)
					{
						System.out.println("inside item feedback");
						Thread.sleep(2000);
						String stars=assessmentDetail.get("ItemFeedbackStars").toString();
						fbp.enterItemFeedbackStars(stars);
					}
				}
				clickOnNextPhaseItem();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		});
		return true;
	}
	
	/*
	 * Function Name : verifyAssessmentCompletionMessage
	 * Function Parameters: Current assessment name
	 * Description : Function is used verify if assessment completion name is displayed correctly
	 * Return Value : Boolean
	 * 
	 */
	
	public void verifyAssessmentCompletionMessage(String assessmentName)
	{
		try
		{
			String messageFromScreen = driver.findElement(objmap.getLocator("lbl_assessmentCompletionMessage")).getText();
			String messageExpected="You have completed "+assessmentName+"";
			Assert.assertEquals("ThankYou message after completing assessment"+assessmentName+" not matching expected ", messageExpected, messageFromScreen);
			
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Thank you message after completing the assessment "+assessmentName+" not present", ne);
			
		}
		catch(Exception e)
		{
			Assert.assertNull("Thank you message after completing the assessment "+assessmentName+" general ERROR", e);
			
		}
	}
	
	
	/*
	 * Function Name : clickOnHomeButton
	 * Function Parameters: phaseType . phaseType is passed from the navigateThroughPhases function
	 * Description : Function used to navigate through various phase items of the passed "phase"
	 * Return Value : Boolean
	 * 
	 */
	
	
	public void clickOnHomeButton(String phaseType) {
		
		try
		{
		switch(phaseType)
		{
		     case "Assessment":
		    	 driver.findElement(objmap.getLocator("btn_assessmentshome")).click(); 
		    	 Map<String,Object> DataObj=st.beforeClass("coursedata.json");
				 break;
		     case "NormalCourse":
		    	 driver.findElement(objmap.getLocator("btn_developmenthome")).click();
		    	 break;
		}
		}
		catch(NoSuchElementException ne)
		{	
			//String s = ExceptionUtils.getStackTrace((Throwable) e);
			Assert.assertNull("Home button from side bar not found"+phaseType, ne);
		}
		catch(Exception e)
		{
			Assert.assertNull("Exception in clickOnHomeButton",e);
		}
		
	}

	/*
	 * Function Name : returnPhaseItemsForPhaseType
	 * Function Parameters: String phaseType
	 * Description : Function used to return the webelement list of phase items based on the phaseType passed
	 * Return Value : List
	 * 
	 */
	
	public List<WebElement> returnPhaseItemsForPhaseType(String phaseType)
	{
		try
		{
		List<WebElement> phaseItems=null;
		switch(phaseType)
		{
			case "Assessment":
				phaseItems=driver.findElements(objmap.getLocator("assessment_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
			case "R2S":
				phaseItems=driver.findElements(objmap.getLocator("r2s_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
			case "Self Paced":
				phaseItems=driver.findElements(objmap.getLocator("self_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
			case "NormalCourse":
				Thread.sleep(4000);
				phaseItems=driver.findElements(objmap.getLocator("normalcourse_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
		}
		return phaseItems;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull( ""+phaseType+" items not loading in journey page ", ne);
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*
	 * Function Name : launchPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to start/launch an assessment item
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean launchPhaseItem()
	{
		
		try
		{
		//explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(objmap.getLocator("btn_start"))));
		Thread.sleep(4000);
		   e=driver.findElement(objmap.getLocator("btn_start"));
			if(e!=null){
				System.out.println("Start button found");
				e.click();
				Thread.sleep(4000);
				return true;
			}
			else return false;
			
		}
		catch(NoSuchElementException ne)
		{
			//Dont know which assessment
			Assert.assertNotNull("Start button for assessment phase is not found", ne);
			return false;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
			
		
	}

	
	/*
	 * Function Name : clickOnNextPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to clicking on Next button to traverse through phase items
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean clickOnNextPhaseItem()
	{

		try
		{
			Thread.sleep(5000);
			e=driver.findElement(objmap.getLocator("btn_nextitem"));
			if(e!=null){
				System.out.println("Next button found ");
				Thread.sleep(1000);
				e.click();
				return true;
			}
			return false;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Next button at the phase item is not found", ne);
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
			
				asp.submitTestSim();
		}
			
			Thread.sleep(2000);
			e=driver.findElement(objmap.getLocator("btn_exit"));
			e.click();
			Thread.sleep(1000);
			e=driver.findElement(objmap.getLocator("btn_popupexit"));
			if(e!=null)
				e.click();
			Thread.sleep(2000);
			return true;
		} 
		catch (NoSuchElementException ne) {
			Assert.assertNull("Button to exit or exit pop up from simulation/game is not found",ne );
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * Function Name : runAssessment
	 * Function Parameters: None.
	 * Description : Function used to exit an existing assessment/simulation
	 * Return Value : boolean
	 * 
	 */
	
public boolean runAssessment(String assessmentName)
{
	
	return true;
}
	/*
		 * Function Name : Logout
		 * Function Parameters: pNA.
		 * Description : Function is used to logout of the platform 
		 * Return Value : boolean
		 * 
		 */
		
	public boolean logout() throws Exception
	{
		try
		{
			driver.findElement(objmap.getLocator("lbl_UserName")).click();
			driver.findElement(objmap.getLocator("btn_Logout")).click();
			driver.findElement(objmap.getLocator("pop_LOGOUT")).click();
			return true;
		}
		catch (NoSuchElementException ne) {
			Assert.assertNull("Button to Logout from application is not found",ne );
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
		
		
	/*
	 * Function Name : verifyModuleName
	 * Function Parameters: Module Name, Item Name is passed from traverseThroughCourse function
	 * Description : Function is used verify if the module name from screen is same as that of the test Data
	 * Return Value : void
	 * 
	 */


public boolean verifyModuleName(String moduleName,String itemName,String itemType,Map<String,Object> moduleItem) throws InterruptedException {
     
		System.out.println("Inside verify module name");
		Thread.sleep(4000);
    	List<WebElement> e=driver.findElements(By.xpath("//div[contains(@class,'module-2jm6jgwkd4mep6p6g9p5rj7s886661tqjt4tmat6w1xjsweeu2z43gzyk628tx66haga3dbb4435mwkk6cvdba6ysyb5vysmn2ru4-moduleItemScreen-module-sidebar-open module-36ypdeweh3nma3gv8ygsmvuuz5y6v96ntwxw69wy8167wqc5ze79x4mvj63hhhsh61ku9pggep2y9zh1d8f91qsu2q5gddzy7cxatzc-moduleItemScreen-module-module-item-outer-cnt')]//div[contains(@class,'_6flor0 module-49qcfnxjwygbdeq6agmkwaksp2wekc55jyg1pf9y7851cfanspg95dq93t6gsy47wmn2ukvwnwhaqk8rzaykjh4xnm4y64w96mdssx4-moduleItemScreen-module-menu-container')]//span//div//div//div[contains(@class,'tobesco')]//div[contains(@class,'_1feb3ip module-3apypwr1fyrjh2vrn8ma4dc9uxybzg21wmc7repy765y6ymt37y9fhyh3yb1gmebcz3ehqdmehedhugb9n573mua5rnnednx87w96rp-sectionHeader-module-header-name')]"));
		boolean result=false;
		for(WebElement e1:e){
			
			if(e1.getText().equalsIgnoreCase(moduleName)){
				System.out.println("Module name from screen "+e1.getText());
				System.out.println("Module name Matched with test data");
	            try {
					result=verifyItemName(itemName,itemType,moduleItem);
					break;
				}
	            catch (NoSuchElementException e2) {
					Assert.assertNull("Exception in verifyModuleName: cant find the list of module names "+moduleName+"",e2);
					e2.printStackTrace();
					return false;
				}
	            catch (Exception e2) {
					Assert.assertNull("Exception in verifyModuleName "+moduleName+"",e2);
					e2.printStackTrace();
					return false;
				}
			}
		}
		return result;
}	
		
		
	/*
	 * Function Name : verifyItemName
	 * Function Parameters: Item Name which is passed from verifyModuleName function
	 * Description : Function is used verify if the item name from screen is same as that of the test Data and navigate to next items
	 * Return Value : void
	 * 
	 */
	

public boolean verifyItemName(String itemName, String itemType,Map<String,Object> moduleItem){
  
	try {
		
	cm.checkErrorComponents();
	Thread.sleep(2000);
	WebElement e = driver.findElement(By.xpath("//div[contains(@class,'innerListItem-module-module-item-title')]//span[contains(@class,'module-22v5yu3ffhhsgfk81kmxd65jpqpc4hrwzg5fydhjy4urrqcg2faj6em1bzckj68yxxwv96gp591877j4dy536vn4gg1dpm1nw21pwy6-innerListItem-module-title-inner') and contains(text(),'"+itemName+"')]"));
	System.out.println("Item Name From Screen "+e.getText());
    
	if(e!=null){
        Thread.sleep(2000);
    }
    playItem(itemName,itemType);
    if(moduleItem.get("feedback")!=null)
    {
    	System.out.println("Feedback found");
    	String feedbackItem=moduleItem.get("feedback").toString();
    	System.out.println("feedback for the item"+itemName+"="+feedbackItem);
    	fbp.likeDislikeDevItemfeedback(feedbackItem);
    }
    return true;
	} 
	
	catch (NoSuchElementException e1) {
		Assert.assertNull("Exception in verifyItemName : The item "+itemName+"is not present",e1);

		e1.printStackTrace();
		return false;
	}
	catch (Exception e1) {
		Assert.assertNull("Exception in verifyItemName "+itemName+"",e1);

		e1.printStackTrace();
		return false;
	}
}


/*
 * Function Name : playitem
 * Function Parameters: itemName
 * Description : To play or execute the current item 
 * Return Value : boolean
 * 
 */

public boolean playItem(String itemName, String itemType)
{
	System.out.println("Inside playItem");
	Map<String,Object> itemDetails;

    switch(itemType)
    {
    	case "Quiz" :
    		qz.playQuiz(itemName);
    		break;

        case "Assignment":
        	System.out.println("Item Type is Assignment");
        	assgn.verifyAndSubmitAssignment();
            break;
       
        
        	
        case "URL":
        	System.out.println("Item Type is External URL");
        	ext.openExternalURL(itemName);
        	break;
    }
    return true;

}

	/*
	 * Function Name : traverseThroughCourse
	 * Function Parameters: course name which is passed from navigateThroughPhaseItem function
	 * Description : Function is used traverse through course and its modules
	 * Return Value : Boolean
	 * 
	 */
		
	
	
public boolean traverseThroughCourse(String courseName)
{
    index=1;
    System.out.println("In traverse through course");
    Iterator<Entry<String, Object>> it = DataObj.entrySet().iterator();
    while(it.hasNext())
    {
        Map.Entry<String, Object> map = (Map.Entry<String, Object>) it.next();
        if(map.getKey().equalsIgnoreCase(courseName))
        {
            ArrayList<Object> ModuleList=((ArrayList)DataObj.get(courseName));
            System.out.println("Module list size "+ModuleList.size());
            ModuleList.forEach((module)->{
            	System.out.println("in module loop");
                Map<String,Object> moduleItem=(Map<String,Object>)module;
                System.out.println("the module is="+moduleItem);
                String modulename=moduleItem.get("moduleName").toString();
                String itemName=moduleItem.get("itemName").toString();
                String itemType=moduleItem.get("itemType").toString();
            try {

            		System.out.println("just before verify module");
                    verifyModuleName(modulename,itemName,itemType,moduleItem);
                    System.out.println("executed verifyModuleName function");
                    Thread.sleep(4000);
                    
                    clickOnNextPhaseItem();  
                } catch (Exception e) {
                    
                	Assert.assertNull("Traversing thorugh course not successful",e);
                    e.printStackTrace();
                }                    
                
            });
        }
        
    }
    
    return true;
}






/*
 * Function : To close browser
 * 
 * 
 * 
 */


public void closeApplication()
{
	System.out.println("closing browser");
	driver.quit();
}

}
