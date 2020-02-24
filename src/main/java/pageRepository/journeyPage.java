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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
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
	WebDriverWait wait = new WebDriverWait(driver,30);
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
	phaseCompletionValidations pv=new phaseCompletionValidations();
	developmentPhase dev=new developmentPhase();
	Quiz qz=new Quiz();
	externalURL ext=new externalURL();
	fileItems fl=new fileItems();
	int i=0;
	
	public journeyPage(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/JourneyPage.properties");
		Map<String,Object> DataObj=st.beforeClass("testData.json");

	}
	
	
	/*
	 * Function Name : validateJourneyPage
	 * Function Parameters: None
	 * Description : To validate if the user has landed on journey page successfully
	 * Return Value : None
	 * 
	 */
	
	public void validateJourneyPage() throws IOException {
		
		int count=0;
		Boolean flag=false;;
		
		try 
		{
		 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("phaseitem_count")));
		 count=driver.findElements(objmap.getLocator("phaseitem_count")).size();
		 System.out.println("Number of phase items in Asessment phase: "+count);
		 flag=true;
		}
		catch(TimeoutException ne)
		{
			System.out.println("Inside catch block for Assessment phase");
			try
			{	
				wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("phaseitem_dev_count")));
				count=driver.findElements(objmap.getLocator("phaseitem_dev_count")).size();
				System.out.println("Number of phase items in Development phase: "+count);
				flag=true;
			}
			catch(Exception e1)
			{
				cm.screenShot();
				System.out.println("inside catch block for Development phase");
				flag=false;
				Assert.assertTrue("Journey Landing page not loaded properly", flag);
				e1.printStackTrace();
			}
		}
		catch (Exception e) {
			System.out.println("Inside main catch");
			flag=false;
			cm.screenShot();
			Assert.assertTrue("Journey Landing page not loaded properly", flag);
			e.printStackTrace();
		}
		
			 System.out.println("Sucessfully landed on journey page");
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
				 WebElement e1=driver.findElement(By.xpath("//div[contains(@class,'content-module-tabs-content')]//div[contains(text(),'"+phaseName+"')]//parent::div"));
				 JavascriptExecutor ex=(JavascriptExecutor)driver;
				 ex.executeScript("arguments[0].click()", e1);
				 //e1.click();
				 //User navigates through the phase items of the particular phase	 
				 
				 String phaseType=phaseMap.get("phaseType").toString();
				 System.out.println("Phase type to be clicked on next is "+phaseType);

				 navigateThroughPhaseItem(phaseType);
				 if(phaseMap.get("PhaseFeedback").toString().equalsIgnoreCase("Yes"))
				 {
					 System.out.println("Inside feedback phase submission");
					 fbp.fillFeedback(phaseName,feedbackData);
				 }
				
				 // Function call to verify Assessment report
				 	System.out.println("Before "+phaseMap.get("phaseType").toString());
					if(phaseType.equalsIgnoreCase("Assessment")){
						asp.verifyAssessmentReport();
				}
					else{
					if(driver.findElements(objmap.getLocator("lbl_Certificate")).size()==0)
					{
						System.out.println("Inside expecting to click HomeButton when certificate has not appeared");
						clickOnHomeButton(phaseType);
					}
					}
					
					 
			} 
			catch(NoSuchElementException ne)
			{
				 Map<String,Object> phaseMap1=(Map<String, Object>) (phase);
				 String phaseName1=phaseMap1.get("phaseName").toString();
				 System.out.println("phaseName1="+phaseName1);
				 Assert.assertNull("Phase Tab-"+phaseName1+" in the landing page not clickable or not found", ne);
				ne.printStackTrace();
			}
			
			catch(TimeoutException te)
			{
				 Map<String,Object> phaseMap1=(Map<String, Object>) (phase);
				 String phaseName1=phaseMap1.get("phaseName").toString();
				 System.out.println("phaseName1="+phaseName1);
				Assert.assertNull("Phase Tab-"+phaseName1+" in the landing page not clickable or not found", te);
				te.printStackTrace();
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			i++;
			
		});
		verifyCertificate();
		downloadCertificate();
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
			
		try
		{
			/*
			 * Next line to be deleted
			 * 
			 
				if(phaseType.equalsIgnoreCase("P2P"))
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'P2P- Development')]"))).click();;
				*/
			List<WebElement> phaseItems=returnPhaseItemsForPhaseType(phaseType);
			//Assert.assertNotNull("After completion of a phase,the navigation did not go automatically to the next phase as expected", phaseItems);
			wait.until(ExpectedConditions.visibilityOf(phaseItems.get(0)));
			phaseItems.get(0).click();

		//-----------launching phases--------------------
		
		if(phaseType.equalsIgnoreCase("Assessment"))
		{
			navigateThroughAssessmentPhase(phaseType);
		}
		
		if(phaseType.equalsIgnoreCase("NormalCourse")||phaseType.equalsIgnoreCase("P2P"))
		{
		for(i=0;i<=phaseItems.size()-1;i++){
			
			navigateThroughDevelopmentPhase(phaseType);
									
				if(i<phaseItems.size()-1)
					result=clickOnNextPhaseItem();
				}
		}
			System.out.println("outside");
		
		
		
		}
		catch(StaleElementReferenceException ste)
		{
			System.out.println("inside catch of stale element exception");
//			List<WebElement> phaseItems=returnPhaseItemsForPhaseType(phaseType);
//			wait.until(ExpectedConditions.visibilityOf(phaseItems.get(0)));
//			phaseItems.get(0).click();
			driver.navigate().refresh();
		}
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
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_coursenamesidebar")));
			WebElement ee=driver.findElement(objmap.getLocator("lbl_coursenamesidebar"));
			String courseName=ee.getText();
			System.out.println("CourseName from screen="+courseName);
			
			//-----function to traverse through the courses listed in test data-------
			result=traverseThroughCourse(courseName);
			}
		
		 
		 // P2P condition to be included here
		  
		   if(phaseType.equalsIgnoreCase("P2P"))
		   {
			   System.out.println("Inside p2p course");
			   e=wait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_p2pLaunchContent")));
			   e.click();
			   //traverse through the scrom course
			   dev.traverseThroughP2P_SCORM("p2P Automation");
		   	
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
	
	
	public boolean navigateThroughAssessmentPhase(String phaseType)
	{
		List AssessmentNames=datalist("Assessments");
		AssessmentNames.forEach((assessment)->{
			Map<String,Object> assessmentDetail=(Map<String, Object>) (assessment);
			String assessmentType=assessmentDetail.get("AssessmentType").toString();
			String itemName=assessmentDetail.get("AssessmentName").toString();
			System.out.println("assess Type= "+assessmentType);
			System.out.println("Assessment Name= "+itemName);
			try
			{
			if(asp.launchAssessmentItem()==true)	
				
				{
					cm.checkErrorComponents();
					asp.validateAndExitPhaseItem(assessmentType);
					
					pv.checkPhaseItemStatus(phaseType,itemName);
					
					if(assessmentDetail.get("ItemFeedbackStars")!=null)
					{
						System.out.println("Inside item feedback");
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
	
	public void verifyAssessmentCompletionMessage(String assessmentName) throws IOException
	{
		try
		{
			String messageFromScreen = driver.findElement(objmap.getLocator("lbl_assessmentCompletionMessage")).getText();
			String messageExpected="You have completed "+assessmentName+"";
			Assert.assertEquals("ThankYou message after completing assessment"+assessmentName+" not matching expected ", messageExpected, messageFromScreen);
			
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Thank you message after completing the assessment "+assessmentName+" not present", ne);
			ne.printStackTrace();
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Thank you message after completing the assessment "+assessmentName+" general ERROR", e);
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Function Name : clickOnHomeButton
	 * Function Parameters: phaseType . phaseType is passed from the navigateThroughPhases function
	 * Description : Function used to navigate through various phase items of the passed "phase"
	 * Return Value : Boolean
	 * 
	 */
	
	
	public void clickOnHomeButton(String phaseType) throws IOException {
		
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
			cm.screenShot();
			Assert.assertNull("Home button from side bar not found"+phaseType, ne);
			ne.printStackTrace();
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Exception in clickOnHomeButton",e);
			e.printStackTrace();
		}
		
	}

	/*
	 * Function Name : returnPhaseItemsForPhaseType
	 * Function Parameters: String phaseType
	 * Description : Function used to return the webelement list of phase items based on the phaseType passed
	 * Return Value : List
	 * 
	 */
	
	public List<WebElement> returnPhaseItemsForPhaseType(String phaseType) throws IOException
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
				wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("normalcourse_items")));
				phaseItems=driver.findElements(objmap.getLocator("normalcourse_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
			case "P2P":
				wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("p2p_items")));
				phaseItems=driver.findElements(objmap.getLocator("p2p_items"));
				System.out.println("size of list "+phaseItems.size());
				break;
		}
		return phaseItems;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
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
	
	public boolean launchPhaseItem() throws IOException
	{
		try
		{
			System.out.println("launch phase item inside journey page");
			e=wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_start")));
		   	if(e!=null){
				System.out.println("Start button found");
				e.click();
				return true;
			}
			else return false;
			
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Start button for assessment phase is not found", ne);
			ne.printStackTrace();
			return false;
		}
		
		catch(TimeoutException te){
			cm.screenShot();
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
	 * Function Name : clickOnNextPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to clicking on Next button to traverse through phase items
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean clickOnNextPhaseItem() throws IOException
	{

		try
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_nextitem")));
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
			cm.screenShot();
			Assert.assertNull("Next button at the phase item is not found", ne);
			ne.printStackTrace();
			return false;
		}
		catch(TimeoutException te)
		{
			cm.screenShot();
			Assert.assertNull("Next button at the phase item is not found", te);
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
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
			cm.screenShot();
			Assert.assertNull("Button to Logout from application is not found",ne );
			ne.printStackTrace();
			return false;
		}
		catch (TimeoutException te) {
			cm.screenShot();
			Assert.assertNull("Button to Logout from application is not found",te );
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
	 * Function Name : verifyModuleName
	 * Function Parameters: Module Name, Item Name is passed from traverseThroughCourse function
	 * Description : Function is used verify if the module name from screen is same as that of the test Data
	 * Return Value : void
	 * 
	 */


public boolean verifyModuleName(String moduleName,String itemName,String itemType,Map<String,Object> moduleItem) throws InterruptedException, IOException {
     
		System.out.println("Inside verify module name");
    	
		try
		{
		List<WebElement> e=driver.findElements(objmap.getLocator("coursemodules_count"));
		boolean result=false;
		for(WebElement e1:e){
			
			if(e1.getText().equalsIgnoreCase(moduleName)){
				System.out.println("Module name from screen "+e1.getText());
				System.out.println("Module name Matched with test data");
	            try {
					result=verifyItemName(itemName,itemType,moduleItem);
					break;
					
				}
	            catch (NoSuchElementException ne) {
	            	cm.screenShot();
					Assert.assertNull("The module "+moduleName+" cannot be found / is unidentifiable from the left hand tab.(For QA-Function to check :verifyModuleName)",ne);
					ne.printStackTrace();
					return false;
				}
	            catch (Exception e2) {
	            	cm.screenShot();
					Assert.assertNull("Exception while trying to identify module "+moduleName+".(For QA-Function to check :verifyModuleName) ",e2);
					e2.printStackTrace();
					return false;
				}
			}
		}
		System.out.println("just before returning result of verifyModuleName");
		return result;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Cannot find the list of modules from the screen .(For QA-Function to check :verifyModuleName)", ne);
			return false;
		}
		catch (Exception e2) {
			cm.screenShot();
			Assert.assertNull("Exception while trying to find the list of modules from the screen .(For QA-Function to check :verifyModuleName):",e2);
			e2.printStackTrace();
			return false;
		}
		
}	
		
		
	/*
	 * Function Name : verifyItemName
	 * Function Parameters: Item Name which is passed from verifyModuleName function
	 * Description : Function is used verify if the item name from screen is same as that of the test Data and navigate to next items
	 * Return Value : void
	 * 
	 */
	

public boolean verifyItemName(String itemName, String itemType,Map<String,Object> moduleItem) throws IOException{
  
	Map<String,Object> DataObj=st.beforeClass("coursedata.json");
	
	String phasetype=DataObj.get("phaseType").toString();
	System.out.println("Development phase type check: "+phasetype);
	
	try {
	cm.checkErrorComponents();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'innerListItem-module-module-item-title')]//span[contains(@class,'module-22v5yu3ffhhsgfk81kmxd65jpqpc4hrwzg5fydhjy4urrqcg2faj6em1bzckj68yxxwv96gp591877j4dy536vn4gg1dpm1nw21pwy6-innerListItem-module-title-inner') and contains(text(),'"+itemName+"')]")));
	WebElement e = driver.findElement(By.xpath("//div[contains(@class,'innerListItem-module-module-item-title')]//span[contains(@class,'module-22v5yu3ffhhsgfk81kmxd65jpqpc4hrwzg5fydhjy4urrqcg2faj6em1bzckj68yxxwv96gp591877j4dy536vn4gg1dpm1nw21pwy6-innerListItem-module-title-inner') and contains(text(),'"+itemName+"')]"));
	String itemnamefromscreen=e.getText();
	System.out.println("Item Name From Screen "+e.getText());
    
	if(e!=null){
        Thread.sleep(2000);
    }
    playItem(itemName,itemType);
   
   if(!(itemnamefromscreen.contains("Optional")))
   {
	   pv.checkPhaseItemStatus(phasetype, itemnamefromscreen);
   }
     
    if(moduleItem.get("feedback")!=null)
    {
    	System.out.println("Feedback found");
    	String feedbackItem=moduleItem.get("feedback").toString();
    	System.out.println("feedback for the item"+itemName+"="+feedbackItem);
    	fbp.likeDislikeDevItemfeedback(feedbackItem,itemName);
    }
    
    System.out.println("just before returning from verifyItemName");
    return true;
	} 
	
	catch (NoSuchElementException ne) {
		cm.screenShot();
		Assert.assertNull("The item "+itemName+"is not present or identifiable. (For QA-Function to check :verifyItemName) ",ne);
		ne.printStackTrace();
		return false;
	}
	
	catch (TimeoutException te) {
		cm.screenShot();
		Assert.assertNull("The item "+itemName+"is not present or identifiable. (For QA-Function to check :verifyItemName) ",te);

		te.printStackTrace();
		return false;
	}
	catch (Exception e1) {
		cm.screenShot();
		Assert.assertNull("Exception while trying to identify item "+itemName+". (For QA-Function to check :verifyItemName)",e1);

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

public boolean playItem(String itemName, String itemType) throws IOException
{
	System.out.println("Inside playItem");
	Map<String,Object> itemDetails;
	
	try
	{
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
        	
    		case "Video":
    			System.out.println("Item Type is Video");
    			fl.checkVideoLoad();
    			break;	
    		case "P2P":
    			System.out.println("Item Type is P2P");
    			e=wait.until(ExpectedConditions.elementToBeClickable(objmap.getLocator("btn_p2pLaunchContent")));
 			   	e.click();
    			dev.traverseThroughP2P_SCORM(itemName);
    			break;
        	
		}
    return true;  
	}
	catch(Exception e)
	{
		cm.screenShot();
		Assert.assertNull("Unable to play the item "+itemName+" in the Development Phase", e);
		e.printStackTrace();
		return false;
	}
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
    Map<String,Object> DataObj=st.beforeClass("coursedata.json");
    System.out.println("In traverse through course : "+ courseName);
    Iterator<Entry<String, Object>> it = DataObj.entrySet().iterator();
    while(it.hasNext())
    {
    	System.out.println("inside it.hasNext()");
        Map.Entry<String, Object> map = (Map.Entry<String, Object>) it.next();
        System.out.println(" outside if: "+map.getKey());
        if(map.getKey().equalsIgnoreCase(courseName))
        {
        	System.out.println("map.getkey()"+map.getKey());
        	System.out.println("coursename "+courseName);
            ArrayList<Object> ModuleList=((ArrayList)DataObj.get(courseName));
            System.out.println("Module list size "+ModuleList.size());
            
            ModuleList.forEach((module)->{
            
            	System.out.println("in module loop");
                Map<String,Object> moduleItem=(Map<String,Object>)module;
                System.out.println("the module is="+moduleItem);
                String modulename=moduleItem.get("moduleName").toString();
                String itemName=moduleItem.get("itemName").toString();
                String itemType=moduleItem.get("itemType").toString();
                System.out.println("names="+modulename+" "+itemName+" "+itemType);
            try {

            		System.out.println("just before verify module");
                    verifyModuleName(modulename,itemName,itemType,moduleItem);
                    System.out.println("executed verifyModuleName function");
                    
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
 */


public void closeApplication()
{
	System.out.println("closing browser");
	driver.quit();
}

/*
 * Purpose: To verify the end of journey certificate
 * 
 */
public boolean verifyCertificate() throws IOException
{
	Boolean flag=false;
	try
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_Certificate")));
		return true;
	}
	
	catch (NoSuchElementException ne) {
		cm.screenShot();
		Assert.assertNull("Certificate after completing the journey cant be found",ne);
		ne.printStackTrace();
		return false;
	}
	
	catch (TimeoutException te) {
		cm.screenShot();
		Assert.assertNull("Certificate after completing the journey cant be found",te);
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
 * Purpose: To download the end of jounrey certificate
 * 
 */
public void downloadCertificate() throws IOException {
	try
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_DownloadCertificate"))).click();
		
	}
	
	catch(NoSuchElementException ne)
	{
		cm.screenShot();
		Assert.assertNull("Download button for certificate download on completion of journey not found", ne);
		ne.printStackTrace();
	}
	catch(TimeoutException te)
	{
		cm.screenShot();
		Assert.assertNotNull("Download report button not found", te);
		te.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

}