package pageRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpResponse;

import utils.ObjectFactory;
import utils.StartUp;

public class assesmentPhase extends StartUp{
	
	ObjectFactory objmap;
	WebElement e;
	public assesmentPhase(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/assessmentPhase.properties");
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
		
		public boolean assessmentPhaseNavigation()
		{
			return true;
		}
	
}
