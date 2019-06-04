package pageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
	
	//WebDriver driver= getDriver();
	WebDriverWait explicitWait = new WebDriverWait(driver,30);
	ObjectFactory objmap;
	public Properties prop;
	WebElement e;
	int statusCode;
	Boolean result;
	List journeyInfo;
	int inav;
	assesmentPhase asp=new assesmentPhase();
	
	public journeyPage(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/JourneyPage.properties");
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
		Thread.sleep(7000);
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
		boolean isNavigateThroughJourneySuccessful;
		journeyInfo=datalist("journeyDetails");
		List<WebElement> phases;
		inav=0;
		/*
		 * journeyInfo contains data from the testData.json
		 */
		journeyInfo.forEach((phase) -> {
			try {
				
				 Map<String,Object> phaseMap=(Map<String, Object>) (phase);
				 String phaseName=getPhaseName(phaseMap);
				 driver.findElement(By.xpath("//div[contains(@class,'content-module-individual-tab') and contains(text(),'"+phaseName+"')]")).click();
			//User navigates through the phase items of the particular phase	 
				 navigateThroughPhaseItem(getPhaseType(phaseMap));
				 driver.findElement(objmap.getLocator("btn_home")).click();
				 System.out.println("clicked on home button");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	
		return true;
	}
	
	/*
	 * Function Name : getPhaseName
	 * Function Parameters: Map<String,Object> phaseMap . Data object saved from testData.json which contains our test data
	 * Description : Function used to return the phaseName from the test data
	 * Return Value : String
	 * 
	 */
		
	public String getPhaseName( Map<String,Object> phaseMap)
	{
		String phaseName=phaseMap.get("phaseName").toString();
		System.out.println("Phase name to be clicked on next is"+phaseName);
		return phaseName;
	}
	
	
	/*
	 * Function Name : getPhaseType
	 * Function Parameters: Map<String,Object> phaseMap . Data object saved from testData.json which contains our test data
	 * Description : Function used to return the phaseType from the test data
	 * Return Value : String
	 * 
	 */
	public String getPhaseType( Map<String,Object> phaseMap)
	{
		String phaseType=phaseMap.get("phaseType").toString();
		System.out.println("Phase type to be clicked on next is"+phaseType);
		return phaseType;
	}
	
	
	/*
	 * Function Name : navigateThroughPhaseItem
	 * Function Parameters: String phaseType . phaseType is passed from the navigateThroughPhase function
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
			if(phaseType.equalsIgnoreCase("Assessment"))
				if(launchPhaseItem()==true)
					exitPhaseItem();
				if(i<phaseItems.size()-1)
					result=clickOnNextPhaseItem();
			
		}
		
		return result;
	
	}
	
	
	/*
	 * Function Name : returnPhaseItemsForPhaseType
	 * Function Parameters: String phaseType .
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
			case "Normal":
				phaseItems=driver.findElements(objmap.getLocator("normalcourse_items"));
				System.out.println("size of list in Normal "+phaseItems.size());
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
	
	
	/* Function Name : validatePhaseItem
	 * Parameter:Name of the simulation that is expected to be launched. It is fetched from testdata.json
	 * 
	 * 
	 * 
	 */
	public boolean validatePhaseItem()
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

		statusCode=new HttpResponse().getStatus();
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
		
}

