package pageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class assignment extends StartUp {
	
	ObjectFactory objmap;
	StartUp st = new StartUp();
	List assignmentList;
	WebElement e,e1;
	Map<String,Object> CourseDataObj;
	WebDriverWait wait = new WebDriverWait(driver,30);
	CommonMethods cm=new CommonMethods();
	
	
	public assignment(){
		System.out.println("Inside assignment page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/assignment.properties");
		CourseDataObj=st.beforeClass("coursedata.json");
		
	}
	
	public void verifyAndSubmitAssignment(){
			
		assignmentList = (List) CourseDataObj.get("AssignmentData");
		assignmentList.forEach((assignmentDetails) -> {
			Map<String,Object> assignment = (Map<String, Object>) assignmentDetails;
			try {
				verifyAssignmentName(assignment);
				System.out.println("leaving verifyAssignmentName");
			
			} 
			catch (Exception e) {
				Assert.assertNull("Error while playing the assignment in the development phase",e);
				e.printStackTrace();
			}
		
	});
}	
	
	public void verifyAssignmentName(Map<String,Object>assignment) throws Exception{
			
		String assignmentname = assignment.get("assignmentName").toString();
		String assignmenttype = assignment.get("submissionType").toString();
		String filetoupload="";
		System.out.println("assignmenttype="+assignmenttype);
		String assignmentpoints = assignment.get("assignmentPoints").toString();
		String assignmenttext="";
		String websiteurl = "";
				
		if(assignment.get("assignmenttext")!=null){
			assignmenttext = assignment.get("assignmenttext").toString();
		}
		if(assignment.get("website_url")!=null){
			websiteurl=assignment.get("website_url").toString();
		}
		if(assignment.get("submissionUploadFilePath")!=null){
			filetoupload=assignment.get("submissionUploadFilePath").toString();
		}
		

		try
		{
			e=driver.findElement(objmap.getLocator("lbl_assignmentname"));
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_points")));
			e1=driver.findElement(objmap.getLocator("lbl_points"));
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Assignment Name is not displayed on screen for the assignment : "+assignmentname+". For QA- check function verifyAssignmentName ", ne);
			ne.printStackTrace();
		}
		catch(TimeoutException te)
		{
			cm.screenShot();
			Assert.assertNull("Points assigned for the assignment is not displayed on screen for the assignment : "+assignmentname+". For QA- check function verifyAssignmentName ", te);
			te.printStackTrace();
		}
		if(e.getText().equalsIgnoreCase(assignmentname) && e1.getText().equalsIgnoreCase(assignmentpoints)){
			Thread.sleep(2000);
			submitAssignment(assignmenttype,assignmenttext,filetoupload,websiteurl);
		}
		
	}
	
	public void submitAssignment(String assignmenttype,String assignmenttext,String filetoupload,String websiteurl) throws Exception{
		
		try
		{
		
		if(assignmenttype.equalsIgnoreCase("Text Entry")){
			
			System.out.println("Inside text assignment submission");
			driver.findElement(objmap.getLocator("txt_assignment")).sendKeys(assignmenttext);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_submit")));
			driver.findElement(objmap.getLocator("btn_submit")).click();
			
		}
		else if(assignmenttype.equalsIgnoreCase("File Upload")){
			System.out.println("Inside File upload assignment submission");
			e=driver.findElement(objmap.getLocator("btn_browse"));
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", e);
			
			//e.click();
			Thread.sleep(2000);
			e.sendKeys(System.getProperty("user.dir")+filetoupload);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_submit")));
			driver.findElement(objmap.getLocator("btn_submit")).click();
		}
		else if(assignmenttype.equalsIgnoreCase("Website URL")){
			System.out.println("Inside website url assignment submission");
			e=driver.findElement(objmap.getLocator("txt_assignmenturl"));
			e.sendKeys(websiteurl);
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_submit")));
			driver.findElement(objmap.getLocator("btn_submit")).click();
			
		}
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Error while submitting the assignment : "+assignmenttype+".For QA-Check the function submitAssignment", ne);
			ne.printStackTrace();
		}
		catch(TimeoutException te)
		{
			cm.screenShot();
			Assert.assertNull("Error while submitting the assignment : "+assignmenttype+".For QA-Check the function submitAssignment", te);
			te.printStackTrace();
		
		}
	}
	
}