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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	int index;
	CommonMethods cm=new CommonMethods();
	assesmentPhase asp=new assesmentPhase();
	feedbackPages fbp=new feedbackPages();
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
	
	public void validateJourneyPage() throws Exception{
		
		int count=0;
		Thread.sleep(4000);
		 count=driver.findElements(objmap.getLocator("phaseitem_count")).size();
		 System.out.println("Number of phase items: "+count);
		 if(count!=0)
			 System.out.println("Sucessfully landed on journey page");
		 Assert.assertNotSame("Invalid Journey Page",0, count);
		
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
		journeyInfo=datalist("journeyDetails");
		
		/*
		 * journeyInfo contains data from the testData.json
		 */
		journeyInfo.forEach((phase) -> {
			try {
				
				 Map<String,Object> phaseMap=(Map<String, Object>) (phase);
				 String phaseName=phaseMap.get("phaseName").toString();
				 System.out.println("Phase name to be clicked on next is "+phaseName);
				 driver.findElement(By.xpath("//div[contains(@class,'content-module-individual-tab')]//div[contains(text(),'"+phaseName+"')]")).click();
			
				 //User navigates through the phase items of the particular phase	 
				 String phaseType=phaseMap.get("phaseType").toString();
				 System.out.println("Phase type to be clicked on next is "+phaseType);
			//--
				 navigateThroughPhaseItem(phaseType);
				 Thread.sleep(5000);
				 if(phaseMap.get("PhaseFeedback").toString().equalsIgnoreCase("Yes"))
				 {
					 System.out.println("Inside feedback phase submission");
					 Thread.sleep(2000);
					 fbp.fillFeedback(phaseName);
				 }
				 clickOnHomeButton(phaseType);
				 

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		//result=logout();
		
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
		//retrieve appropriate phase item type for phase types and click on it 
		List<WebElement> phaseItems=returnPhaseItemsForPhaseType(phaseType);
		Thread.sleep(5000);
		phaseItems.get(0).click();
	
		//launching phases----------------------------------------------------------------
		
		if(phaseType.equalsIgnoreCase("Assessment"))
		{
			navigateThroughAssessmentPhase();
		}
		
					
		for(i=0;i<=phaseItems.size()-1;i++){
			
			navigateThroughDevelopmentPhase(phaseType);
									
				if(i<phaseItems.size()-1)
					result=clickOnNextPhaseItem();
				}
			System.out.println("outside");
					
		
		
		return result;
	
	}
	
	
	/*
	 * Function Name : navigateThroughDevelopmentPhase
	 * Function Parameters: 
	 * Description : Function used to navigate through various phase items of the development phase
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean navigateThroughDevelopmentPhase(String phaseType) throws InterruptedException
	{
		Boolean result=false;
		if(phaseType.equalsIgnoreCase("NormalCourse"))
		{
			System.out.println("Inside Normal course");
			Thread.sleep(5000);
			String path="//div[contains(@class,'sidebarHeader-module-text')]";
			String courseName=driver.findElement(By.xpath(path)).getText();
			System.out.println("CourseName from screen="+courseName);
			
			//function to traverse through the courses listed in test data
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
//		     case "NormalCourse":
//		    	 driver.findElement(objmap.getLocator("btn_developmenthome")).click();
//		    	 break;

		}
		}
		catch(Exception e)
		{
			Assert.assertNull("Exception in vclickOnHomeButton",e);
		}
		
	}

	/*
	 * Function Name : returnPhaseItemsForPhaseType
	 * Function Parameters: String phaseType
	 * Description : Function used to return the webelement list of phase items based on the phaseType passed
	 * Return Value : List
	 * 
	 */
	
	public List<WebElement> returnPhaseItemsForPhaseType(String phaseType) throws Exception
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
	
	/*
	 * Function Name : launchPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to start/launch an assessment item
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean launchPhaseItem() throws Exception
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
			return false;
		
	}

	
	/*
	 * Function Name : clickOnNextPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to clicking on Next button to traverse through phase items
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean clickOnNextPhaseItem() throws Exception
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
	
	
	
	
	/*
	 * Function Name : validate and exitPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to exit an existing assessment/simulation
	 * Return Value : void
	 * 
	 */
	
	
	public boolean validateAndExitPhaseItem(String assessmentType) throws Exception{
		
			//cm.checkErrorComponents();
			
		if(assessmentType.equalsIgnoreCase("Test Sim"))
		{
			System.out.println("Inside Test SIm ");
			try {
				asp.submitTestSim();
				} 
			catch (Exception e) {
				e.printStackTrace();
			}
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
		driver.findElement(objmap.getLocator("lbl_UserName")).click();
		driver.findElement(objmap.getLocator("btn_Logout")).click();
		driver.findElement(objmap.getLocator("pop_LOGOUT")).click();
		
		return true;
	}
		
		
	/*
	 * Function Name : verifyModuleName
	 * Function Parameters: Module Name, Item Name is passed from traverseThroughCourse function
	 * Description : Function is used verify if the module name from screen is same as that of the test Data
	 * Return Value : void
	 * 
	 */

public void verifyModuleName(String moduleName,String itemName) {
        
        WebElement e= driver.findElement(By.xpath("//div[contains(@class,'sectionHeader-module-header-name')and contains(@title,'"+moduleName+"')]"));
        System.out.println("Module name from screen "+e.getText());
        if(e!=null){
            System.out.println("Module name Matched");
            try {
				verifyItemName(itemName);
			} catch (Exception e1) {
				Assert.assertNull("Exception in verifyModuleName "+moduleName+"",e1);
				e1.printStackTrace();
			}
        }
    }
	/*
	 * Function Name : verifyItemNameAndNavigateNext
	 * Function Parameters: Item Name which is passed from verifyModuleName function
	 * Description : Function is used verify if the item name from screen is same as that of the test Data and navigate to next items
	 * Return Value : void
	 * 
	 */
	
public boolean verifyItemName(String itemName){
    
	try {
		cm.checkErrorComponents();
	Thread.sleep(2000);
    WebElement e= driver.findElement(By.xpath("//div[contains(@class,'innerListItem-module-module-item-title')and contains(@title,'"+itemName+"')]"));
    System.out.println("Item Name From Screen "+e.getText());
    if(e!=null){
        System.out.println("Item name matched");
    }        
    return true;
	} 
	catch (Exception e1) {
		// TODO Auto-generated catch block
		Assert.assertNull("Exception in verifyItemName "+itemName+"",e1);

		e1.printStackTrace();
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
    Iterator<Entry<String, Object>> it = DataObj.entrySet().iterator();
    while(it.hasNext())
    {
        Map.Entry<String, Object> map = (Map.Entry<String, Object>) it.next();
        if(map.getKey().equalsIgnoreCase(courseName))
        {
            ArrayList<Object> ModuleList=((ArrayList)DataObj.get(courseName));
            System.out.println("Module list size "+ModuleList.size());
            ModuleList.forEach((module)->{
                Map<String,Object> moduleItem=(Map<String,Object>)module;
                String modulename=moduleItem.get("moduleName").toString();
                String itemName=moduleItem.get("itemName").toString();
            try {
                    verifyModuleName(modulename,itemName);
                    Thread.sleep(3000);
                    
                    clickOnNextPhaseItem();    
                } catch (Exception e) {
                    
                	Assert.assertNull(e);
                    e.printStackTrace();
                }                    
                
            });
        }
        
    }
    
    return true;
}


}
