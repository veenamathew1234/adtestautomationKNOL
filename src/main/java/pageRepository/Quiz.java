package pageRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.log.SysoCounter;

import junit.framework.Assert;
import utils.ObjectFactory;
import utils.StartUp;

public class Quiz extends StartUp{

	StartUp st=new StartUp();
	ObjectFactory objmap;
	Map<String,Object> courseObj;
	WebElement e;
	WebDriverWait wait = new WebDriverWait(driver,30);
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
	
	
	public boolean verifyQuizLandingPage(Map<String,Object> quizData)
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
			Assert.assertNull("Cannot find element in quiz landing page", ne);
			return false;
		}
		catch(Exception e)
		{
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
	 * 
	 */
	public void playQuiz(String itemName) {
		try
		{
			System.out.println("Inside play quiz");
			System.out.println("itemName passed="+itemName);
			ArrayList quizDetails=(ArrayList) courseObj.get("Quiz");
			System.out.println("The quiz details="+quizDetails);
			int flag=0;
		//Click on the take quiz button
			Thread.sleep(2000);
//			e=driver.findElement(objmap.getLocator("btn_TakeQuiz"));
//			e.click();
			for(int i=0;i<quizDetails.size();i++)
			{
				Map<String,Object>q1=(Map<String, Object>) quizDetails.get(i);
				System.out.println("quizName from Json="+q1.get("quizName"));
				if(q1.get("quizName").toString().equalsIgnoreCase(itemName))
				{
					flag=1;
					try {
						verifyQuizLandingPage(q1);
						e=driver.findElement(objmap.getLocator("btn_TakeQuiz"));
					}
					catch(NoSuchElementException ne1)
					{
						Assert.assertNull("Take Quiz button cannot be found for the quiz "+itemName+"", ne1);
					}
					catch (Exception e1) {
						Assert.assertNull("Take Quiz button throwing general exception", e1);
					}
					e.click();
					System.out.println("identified");
					int scoreCalc=answerQuestions(q1);
					submitQuiz();
					verifyScore(scoreCalc);
					break;
				}
			}
	
			Assert.assertEquals("Couldnot find matching quizName",1,flag);
			
//			quizDetails.forEach((quiz)->{
//			Map<String,Object>q1=(Map<String, Object>) quiz;
//			System.out.println("quizName from Json="+q1.get("quizName"));
//			if(q1.get("quizName").toString().equalsIgnoreCase(itemName))
//			{
//				//flag=1;
//				try {
//					verifyQuizLandingPage(q1);
//					e=driver.findElement(objmap.getLocator("btn_TakeQuiz"));
//				}
//				catch(NoSuchElementException ne1)
//				{
//					Assert.assertNull("Take Quiz button cannot be found for the quiz "+itemName+"", ne1);
//				}
//				catch (Exception e1) {
//					Assert.assertNull("Take Quiz button throwing general exception", e1);
//				}
//				e.click();
//				System.out.println("identified");
//				int scoreCalc=answerQuestions(q1);
//				submitQuiz();
//				verifyScore(scoreCalc);
//				//break;
//			}
//			
//		});
			
			//verifyScore(score)
			
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull("Take Quiz button cannot be found for the quiz "+itemName+"", ne);
		}
		catch(Exception e)
		{
			Assert.assertNull("Take Quiz function which loops through Quiz data having some issues", e);
			e.printStackTrace();
		}	
		
		
	}
	
	public int answerQuestions(Map<String,Object> quiz)
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
				question=findQuestionFromScreen(i+1);
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
			Assert.assertNull("main quiz questions answers having problem", e);
			return 0;
		}
		
	}
	
	public String findQuestionFromScreen(int questionNumber)
	{
		
		try
		{
			System.out.println("inside findQuestionFromScreen and question number="+questionNumber);
			Thread.sleep(3000);
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(@class,'_11yezv')]")));
			e=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(@class,'_11yezv')]"));
			String question=e.getText();
			System.out.println("question inside="+question);
			return question;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(""+questionNumber+"st question cannot be found on screen", ne);
			ne.printStackTrace();
			return null;
		}
		catch(Exception e )
		{
			Assert.assertNull("Cannot find question from screen", e);
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean clickOnAnswer(int questionNumber,String answer)
	{
		try
		{
			System.out.println("Inside click ON Answer and the question number="+questionNumber);
			//Thread.sleep(5000);
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]")));
			String question=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]")).getText();
			e=driver.findElement(By.xpath("//div[contains(@class,'questionList-module-question-data-cnt')]["+questionNumber+"]//div[contains(text(),'"+answer+"')]"));
			e.click();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(""+answer+" cannot be found on screen", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNull("Some issue in answering question",e);
			return false;
		
		}
	}
	
	public boolean submitQuiz()
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
			Assert.assertNull("Unable to submit quiz", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNull("Some issue in submitting quiz",e);
			return false;
		
		}
	}
	
	public boolean verifyScore(int score)
	{
		try
		{
			System.out.println("inside verifyScore");
			//Thread.sleep(5000);
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
			Assert.assertNull("Score cannot be found on the platform after quiz", ne);
			return false;
		}
		catch(Exception e)
		{
			Assert.assertNull("Score Calc function issue", e);
			System.out.println("Issues while matching scores");
			return false;
		}
		
	}
	
}
