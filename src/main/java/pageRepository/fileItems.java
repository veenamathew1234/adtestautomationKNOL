package pageRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import utils.CommonMethods;
import utils.StartUp;

public class fileItems  extends StartUp {
	
	CommonMethods cm=new CommonMethods();
	/*
	 * To be added at later phases in case of multiple pages
	 * 
	 */
	public boolean checkFileLoad()
	{
		return true;

	}
	
	public boolean checkPPTLoad()
	{
		return true;
		
	}
	
	public boolean checkVideoLoad() throws InterruptedException, IOException
	
	{
		try
		{
			WebElement frame=driver.findElement(By.xpath("//iframe[contains(@id,'video-player-container_ifp')]"));
			driver.switchTo().frame(frame);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[contains(@class,'videoHolder hover')]//a[contains(@class,'icon-play  comp largePlayBtn  largePlayBtnBorder')]")).click();
			Thread.sleep(12000);
			driver.switchTo().defaultContent();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			Assert.assertNull(ne, "Unable to play video in the development phase. For QA-check the function checkVideoLoad");
			ne.printStackTrace();
			return false;
		}
	}

}
