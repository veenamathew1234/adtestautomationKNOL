package pageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class Quiz extends StartUp{

	StartUp st=new StartUp();
	ObjectFactory objmap;
	Map<String,Object> courseObj;
	WebElement e;
	public Quiz(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/JourneyPage.properties");
		courseObj=st.beforeClass("coursedata.json");
	}
	
	
	/*
	 * Function Name : playQuiz
	 * Function Parameters: itemName
	 * Description : To play quiz
	 * Return Value : boolean
	 * 
	 */
	public void playQuiz(String itemName) {
		ArrayList quizDetails=(ArrayList) courseObj.get("Quiz");
		quizDetails.forEach((quiz)->{
			Map<String,Object>q1=(Map<String, Object>) quiz;
			if(q1.get("quizName").toString().equalsIgnoreCase(itemName))
			{
				
			}
			
		});
		
		
	}
	
	public void answerQuestions(Map<String,Object> quiz)
	{
		
		try
		{
			List questionAnswers=(List) quiz.get("QuestionsAnswersToPlay");
			String question="",answer="";
			for(int i =0;i<questionAnswers.size();i++)
			{
				Map<String,Object> qamap=(Map<String, Object>) questionAnswers.get(i);
				question=findQuestionFromScreen(i+1);
				answer=qamap.get("question").toString();
				if(answer!=null)
				{
					clickOnAnswer(i+1, answer);
					submitQuiz();
				}
			}
		}
		catch(Exception e)
		{
			Assert.assertNotNull("main quiz questions answers having problem", e);
		}
		
	}
	
	public String findQuestionFromScreen(int questionNumber)
	{
		try
		{
			String question=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(@class,'_11yezv')]")).getText();
			return question;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNotNull(""+questionNumber+"st question cannot be found on screen", ne);
			return null;
		}
	}
	
	public boolean clickOnAnswer(int questionNumber,String answer)
	{
		try
		{
			e=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(text(),'"+answer+"')]"));
			e.click();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNotNull(""+answer+" cannot be found on screen", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNotNull("Some issue in answering question",e);
			return false;
		
		}
	}
	
	public boolean submitQuiz()
	{
		try
		{
			driver.findElement(objmap.getLocator("btn_submitQuiz")).click();
			driver.findElement(objmap.getLocator("popUp_submitQuiz")).click();
			return true;
		}
		catch(NoSuchElementException ne )
		{
			Assert.assertNotNull("Unable to submit quiz", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNotNull("Some issue in submitting quiz",e);
			return false;
		
		}
	}
	
	
	
	
	
	
	
}
