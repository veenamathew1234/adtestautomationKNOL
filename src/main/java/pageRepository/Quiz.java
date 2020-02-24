package pageRepository;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.log.SysoCounter;

import junit.framework.Assert;
import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class Quiz extends StartUp{

	StartUp st=new StartUp();
	ObjectFactory objmap;
	Map<String,Object> courseObj;
	WebElement e;
	WebDriverWait wait = new WebDriverWait(driver,30);
	CommonMethods cm=new CommonMethods();
	
	public Quiz(){
		System.out.println("Inside Quiz page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/Quiz.properties");
		courseObj=st.beforeClass("coursedata.json");
	}
	
	
	
	/*
	 * Function Name : verifyQuizLandingPage
	 * Function Parameters: itemName
	 * Description : To verify the fields and labels in quiz landing page like Quiz Name, Points, Number of Attempts
	 * Return Value : boolean
	 * 
	 */
	
	
	public boolean verifyQuizLandingPage(Map<String,Object> quizData) throws IOException
	{

		System.out.println("Inside verifyQuizLandingPage");
		try
		{
			//Verify the quizName
			String quizName=quizData.get("quizName").toString();
			String quizNameFromScreen=driver.findElement(objmap.getLocator("lbl_QuizNameFromQuizLandingPage")).getText();
			System.out.println("quizName from screen="+quizNameFromScreen);
			Assert.assertEquals("Quiz Name From Screen "+quizNameFromScreen+" not matching the expected "+quizName+"", quizName, quizNameFromScreen);
			
			//Verify the max points
			int pointsPerQuestion=(int) quizData.get("pointsPerQuestion");
			int numberOfquestions=(int) quizData.get("noOfQuestions");
			int totalPoints=pointsPerQuestion*numberOfquestions;
			String pointsfromScreenJargon=driver.findElement(objmap.getLocator("lbl_totalQuizPoints")).getText();
			String s=pointsfromScreenJargon.substring(0, pointsfromScreenJargon.indexOf('P')).trim();
			int totalPointsFromScreen=Integer.parseInt(s);
			
			Assert.assertEquals("Total Quiz Points not matching as expected", totalPoints, totalPointsFromScreen);
			return true;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Cannot find element in quiz landing page", ne);
			ne.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			cm.screenShot();
			e.printStackTrace();
			Assert.assertNull("General exception in verify quiz landing page", e);
			return false;
		}
		
	}
	
	
	/*
	 * Function Name : playQuiz
	 * Function Parameters: itemName
	 * Description : To play quiz
	 * Return Value : boolean
	 */
	public void playQuiz(String itemName) throws IOException {
		try
		{
			System.out.println("Inside play quiz");
			System.out.println("itemName passed="+itemName);
			ArrayList quizDetails=(ArrayList) courseObj.get("Quiz");
			System.out.println("The quiz details="+quizDetails);
			boolean flag=false;
		//Click on the take quiz button
			Thread.sleep(2000);
			for(int i=0;i<quizDetails.size();i++)
			{
				Map<String,Object>q1=(Map<String, Object>) quizDetails.get(i);
				System.out.println("quizName from Json="+q1.get("quizName"));
				if(q1.get("quizName").toString().equalsIgnoreCase(itemName))
				{
					System.out.println("Inside identifying");
					flag=true;
					try {
						verifyQuizLandingPage(q1);
						e=driver.findElement(objmap.getLocator("btn_TakeQuiz"));
					}
					catch(NoSuchElementException ne1)
					{
						cm.screenShot();
						Assert.assertNull("Take Quiz button cannot be found for the quiz "+itemName+".For QA-Function to check :playQuiz ", ne1);
						ne1.printStackTrace();
					}
					catch (Exception e1) {
						cm.screenShot();
						Assert.assertNull("Error while clicking on Take Quiz button. For QA-Function to check :playQuiz", e1);
						e1.printStackTrace();
					}
					e.click();
					Thread.sleep(2000);
					System.out.println("identified");
					int scoreCalc=answerQuestions(q1);
					submitQuiz();
					verifyScore(scoreCalc);
					break;
				}
			}
	
			Assert.assertTrue("Could not find the quiz:"+itemName+".(For QA-Function to check :playQuiz)",flag);
			
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Take Quiz button cannot be found for the quiz "+itemName+".(For QA-Function to check :playQuiz)", ne);
			ne.printStackTrace();
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Error while trying to play quiz.(For QA-Function to check :playQuiz)", e);
			e.printStackTrace();
		}	
		
		
	}
	
	public int answerQuestions(Map<String,Object> quiz) throws IOException
	{
		
		try
		{
			System.out.println("Inside answerQuestions function");
			List questionAnswers=(List) quiz.get("QuestionsAnswersToPlay");
			ArrayList<String> answers;
			String question="";
			int scoreCalc=0;
			for(int i =0;i<questionAnswers.size();i++)
			{
				System.out.println("Inside questions loop");
				Map<String,Object> qamap=(Map<String, Object>) questionAnswers.get(i);
				System.out.println("Qamap="+qamap);
				
				//question=findQuestionFromScreen(i+1);
				question=qamap.get("question").toString();
				System.out.println("question got="+question);
				scoreCalc+=(Integer)qamap.get("scoreReceived");
				answers=(ArrayList)qamap.get(question);
				for(String answer:answers)
		 		{
					clickOnAnswer(i+1, answer);
		 		}				
			}
			
			return scoreCalc;
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Error while trying to enter answers for the quiz.(For QA-Function to check :answerQuestions)", e);
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public String findQuestionFromScreen(int questionNumber) throws IOException
	{

        try

        {

            System.out.println("inside findQuestionFromScreen and question number="+questionNumber);
            JavascriptExecutor js = (JavascriptExecutor) driver;
           String question="";
            System.out.println("question inside="+question);
            return question.toString();

        }

		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull(""+questionNumber+"st question cannot be found on screen", ne);
			ne.printStackTrace();
			return null;
		}
		catch(Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean clickOnAnswer(int questionNumber,String answer) throws IOException
	{
		try
		{
			System.out.println("Inside click ON Answer and the question number="+questionNumber);
			Thread.sleep(3000);
			e=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(@class,'mcq-module-mcq-cnt')]//div[contains(@class,'mcq-module-answer-options-cnt')]//div[contains(@class,'mcq-module-option-text') and contains(text(),'"+answer+"')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
			Thread.sleep(500);
			e.click();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull(""+answer+" for the quiz cannot be found on screen.(For QA-Function to check :clickOnAnswer", ne);
			ne.printStackTrace();
			
			return false;
		}
		catch(Exception e)
		{
			cm.screenShot();
			e.printStackTrace();
			Assert.assertNull("Issue while clicking on an answer in quiz.(For QA-Function to check :clickOnAnswer)",e);
			e.printStackTrace();
			return false;
		
		}
	}
	
	public boolean submitQuiz() throws IOException
	{
		System.out.println("inside submitQuiz");
		try
		{
			driver.findElement(objmap.getLocator("btn_submitQuiz")).click();
			Thread.sleep(2000);
			driver.findElement(objmap.getLocator("popUp_submitQuiz")).click();
			return true;
		}
		catch(NoSuchElementException ne )
		{
			
			cm.screenShot();
			Assert.assertNull("Unable to submit quiz. (For QA-Function to check :submitQuiz)", ne);
			ne.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Issue while submitting quiz. (For QA-Function to check :submitQuiz)",e);
			e.printStackTrace();
			return false;
		
		}
	}
	
	public boolean verifyScore(int score) throws IOException
	{
		try
		{
			System.out.println("inside verifyScore and the calculated score is: "+score+"");
			wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_TotalScoreReceived")));
			e=driver.findElement(objmap.getLocator("lbl_TotalScoreReceived"));
			System.out.println("The string before int parse="+e.getText());
			float scoreFromScreentemp=Float.parseFloat(e.getText());
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			float scoreFromScreen=Float.valueOf(decimalFormat.format(scoreFromScreentemp));
			System.out.println("Score from screen ="+scoreFromScreen);
			if(scoreFromScreen==score)
			{	
				System.out.println("score matched!!!");
				return true;
			}
			else
			{
				System.out.println("Score didnt match!!!");
				return false;
			}
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull("Score not displayed on the platform after quiz. (For QA-Function to check :verifyScore)", ne);
			ne.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			cm.screenShot();
			Assert.assertNull("Score Calc issue after quiz. (For QA-Function to check :verifyScore)", e);
			System.out.println("Issues while matching scores");
			e.printStackTrace();
			return false;
		}
		
	}
	
}
