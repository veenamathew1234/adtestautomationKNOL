package pageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;

import utils.ObjectFactory;
import utils.StartUp;

public class assignment extends StartUp {
	
	ObjectFactory objmap;
	StartUp st = new StartUp();
	List assignmentList;
	WebElement e,e1;
	Map<String,Object> CourseDataObj;
	
	
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
			} catch (Exception e) {
				
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
		if(assignment.get("submissionUploadFilePath")!=null)
			filetoupload =  assignment.get("submissionUploadFilePath").toString();
		String assignmenttext=null;
		String websiteurl = null;
		
		if(assignment.get("assignmenttext")!=null){
			assignmenttext = assignment.get("assignmenttext").toString();
		}
		if(assignment.get("website_url")!=null){
			websiteurl=assignment.get("website_url").toString();
			System.out.println("url is "+websiteurl);
		}
		
		
		e=driver.findElement(objmap.getLocator("lbl_assignmentname"));
		Thread.sleep(3000);
		e1=driver.findElement(objmap.getLocator("lbl_points"));
				
		if(e.getText().equalsIgnoreCase(assignmentname) && e1.getText().equalsIgnoreCase(assignmentpoints)){
			Thread.sleep(2000);
			submitAssignment(assignmenttype,assignmenttext,filetoupload,websiteurl);
		}
		
	}
	
	public void submitAssignment(String assignmenttype,String assignmenttext,String filetoupload,String websiteurl) throws Exception{
		
		
		if(assignmenttype.equalsIgnoreCase("Text Entry")){
			
			System.out.println("Inside text assignment submission");
			driver.findElement(objmap.getLocator("txt_assignment")).sendKeys(assignmenttext);
			Thread.sleep(2000);
			driver.findElement(objmap.getLocator("btn_submit")).click();
			
		}
		else if(assignmenttype.equalsIgnoreCase("File Upload")){
			System.out.println("Inside File upload assignment submission");
			e=driver.findElement(objmap.getLocator("btn_browse"));
			e.click();
			Thread.sleep(2000);
			e.sendKeys(System.getProperty("user.dir")+filetoupload);
			Thread.sleep(1000);
			driver.findElement(objmap.getLocator("btn_submit")).click();
		}
		else if(assignmenttype.equalsIgnoreCase("Website URL")){
			System.out.println("Inside website url assignment submission");
			e=driver.findElement(objmap.getLocator("txt_assignmenturl"));
			e.sendKeys(websiteurl);
			Thread.sleep(2000);
			driver.findElement(objmap.getLocator("btn_submit")).click();
			
			
		}
	}
	
}