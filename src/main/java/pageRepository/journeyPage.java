package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
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
	assesmentPhase asp=new assesmentPhase();
	
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
		Thread.sleep(12000);
		 count=driver.findElements(objmap.getLocator("phaseitem_count")).size();
		 System.out.println("Number of phase items: "+count);
		 if(count!=0)
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
	
	public boolean navigateThroughPhases() throws Exception
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
				 navigateThroughPhaseItem(phaseType);
				 Thread.sleep(5000);
				 clickOnHomeButton(phaseType);
				 

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		//result=logout();
		return result;
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
		List<WebElement> phaseItems=returnPhaseItemsForPhaseType(phaseType);
		Thread.sleep(5000);
		phaseItems.get(0).click();
		for(i=0;i<=phaseItems.size()-1;i++){
			if(phaseType.equalsIgnoreCase("Assessment")){
				if(launchPhaseItem()==true)
					validateAndExitPhaseItem();
			}
				
				else if(phaseType.equalsIgnoreCase("NormalCourse"))
				{
					System.out.println("Inside Normal course");
					Thread.sleep(7000);
					System.out.println("After sleep");
					String path="//div[contains(@class,'sidebarHeader-module-text')]";
					String courseName=driver.findElement(By.xpath(path)).getText();
					System.out.println("CourseName from screen="+courseName);
					
					//function to traverse through the courses listed in test data
					traverseThroughCourse(courseName);
									
				}
			System.out.println("outside");
					
			if(i<phaseItems.size()-1)
				result=clickOnNextPhaseItem();
			}
		
		return result;
	
	}
	
	/*
	 * Function Name : clickOnHomeButton
	 * Function Parameters: phaseType . phaseType is passed from the navigateThroughPhases function
	 * Description : Function used to navigate through various phase items of the passed "phase"
	 * Return Value : Boolean
	 * 
	 */
	
	
	public void clickOnHomeButton(String phaseType) throws Exception{
		
		switch(phaseType)
		{
		     case "Assessment":
		    	 driver.findElement(objmap.getLocator("btn_assessmentshome")).click();
				 System.out.println("clicked on home button from Assessment phase"); 
				 Map<String,Object> DataObj=st.beforeClass("coursedata.json");
				 break;
		     case "NormalCourse":
		    	 driver.findElement(objmap.getLocator("btn_developmenthome")).click();
		    	 System.out.println("clicked on home button from Development phase");
		    	 break;
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

	
	/*
	 * Function Name : clickOnNextPhaseItem
	 * Function Parameters: None.
	 * Description : Function used to clicking on Next button to traverse through phase items
	 * Return Value : Boolean
	 * 
	 */
	
	public boolean clickOnNextPhaseItem() throws Exception
	{
			Thread.sleep(2000);
			e=driver.findElement(objmap.getLocator("btn_nextitem"));
			if(e!=null){
				System.out.println("Next button found ");
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
	
	
	public void validateAndExitPhaseItem() throws Exception{

		statusCode=new HttpResponse().getStatus();
		System.out.println("Status Code "+statusCode);
		
		if(statusCode==200){
			Thread.sleep(2000);
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

	 
	public void verifyModuleName(String moduleName,String itemName) throws Exception{
		String modulenamescreen=driver.findElement(objmap.getLocator("lbl_ModuleName")).getText();
		if(modulenamescreen.equalsIgnoreCase(moduleName)){
			System.out.println("Moduename from screen "+modulenamescreen);
			verifyItemNameAndNavigateNext(itemName);
		}
			
	}
	
	public boolean verifyModuleItemFromTestData(String ModuleName, int index) throws Exception
	{
		String ModuleTName=driver.findElement(objmap.getLocator("lbl_ModuleName")).getText();
		if(ModuleTName.equalsIgnoreCase(ModuleName))
			return true;
		else 
			return false;
	}

	/*
	 * Function Name : verifyItemNameAndNavigateNext
	 * Function Parameters: Item Name which is passed from verifyModuleName function
	 * Description : Function is used verify if the item name from screen is same as that of the test Data and navigate to next items
	 * Return Value : void
	 * 
	 */
	
	public void verifyItemNameAndNavigateNext(String itemName) throws Exception{
		
		String itemNameScreen=driver.findElement(objmap.getLocator("txt_ItemName")).getText();
		System.out.println("Item Name From Screen "+itemNameScreen);
		
		if(itemNameScreen.equalsIgnoreCase(itemName)){
			
			System.out.println(" yes matched");
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
					System.out.println("Module Item size "+moduleItem.size());
					String modulename=moduleItem.get("moduleName").toString();
					String itemName=moduleItem.get("itemName").toString();
					System.out.println("ModuleName from json = "+moduleItem.get("moduleName"));
				try {
						verifyModuleName(modulename,itemName);
						Thread.sleep(3000);
						clickOnNextPhaseItem();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
				});
			}
			
		}
		return true;
	
		
		
	}
}
