package pageRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.CommonMethods;
import utils.StartUp;

public class fileItems1  extends StartUp {
	
	CommonMethods cm=new CommonMethods();
	WebDriverWait wait = new WebDriverWait(driver,30);
	WebElement e;
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
//		try
//		{
//			System.out.println("video 1");
//			WebElement frame=driver.findElement(By.xpath("//iframe[contains(@id,'video-player-container_ifp')]"));
//			driver.switchTo().frame(frame);
//			System.out.println("video 2");
//			Thread.sleep(2000);
//			e=driver.findElement(By.xpath("//div[contains(@class,'videoHolder hover')]//a[contains(@class,'icon-play  comp largePlayBtn  largePlayBtnBorder')]"));
//			//e=driver.findElement(By.xpath("//div[contains(@class,'videoHolder hover')]//div[contains(@class,'videoDisplay')]//video[contains(@class,'persistentNativePlayer nativeEmbedPlayerPid')]"));
//			//e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'videoHolder hover')]//div[contains(@class,'videoDisplay')]//video[contains(@class,'persistentNativePlayer nativeEmbedPlayerPid')]")));
//			//e=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'mwPlayerContainer kdark ua-mouse ua-chrome start-state size-medium')]//div[contains(@id,'video-player-container')]")));
//			//e=driver.findElement((By.xpath("//div[contains(@class,'mwPlayerContainer kdark ua-mouse ua-chrome start-state size-medium')]//div[contains(@id,'video-player-container')]")));
//			System.out.println("video 3");
//			//e.click();
//
//			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("arguments[0].click();", e);
//			
//			Thread.sleep(12000);
//			driver.switchTo().defaultContent();
//			return true;
//		}
		try
		{
			WebElement frame=driver.findElement(By.xpath("//iframe[contains(@id,'video-player-container_ifp')]"));
			driver.switchTo().frame(frame);
			e=driver.findElement(By.xpath("//div[contains(@class,'videoHolder hover')]//a[contains(@class,'icon-play  comp largePlayBtn  largePlayBtnBorder')]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("let clickevent=new Event('click')");
			executor.executeScript("arguments[0].click();", e);
			
			
//			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
//			System.out.println("switched to video iframe");
//			WebElement e= driver.findElement(By.xpath("//*[contains(@id, 'pid_video-player-container')]"));
//			e.click();
			Thread.sleep(12000);
			driver.switchTo().defaultContent();
			
			//JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("document.getElementsByClassName('persistentNativePlayer nativeEmbedPlayerPid')[0].play()");
//			
			//executor.executeScript("kalturaplayer().play();");
			
			
			
			
//			Thread.sleep(2000);
//			WebElement video = driver.findElement(By.xpath("//video[contains(@class,'persistentNativePlayer nativeEmbedPlayerPid')]"));
//			video.click();
			
			//video[contains(@class,'persistentNativePlayer nativeEmbedPlayerPid')]

//			Thread.sleep(14000);
//			driver.switchTo().defaultContent();
			
			
			
			
//			WebElement frame1=driver.findElement(By.xpath("//iframe[contains(@id,'video-player-container_ifp')]"));
//			driver.switchTo().frame(frame1);
//			
//			System.out.println("video1");
//		Thread.sleep(2000);
		
		
		
			//JavascriptExecutor executor = (JavascriptExecutor)driver;
			//executor.executeScript("document.getElementsByTagName(\"video\")[0].play()");
//			executor.executeScript("let elem = document.getElementsByClassName(\"persistentNativePlayer nativeEmbedPlayerPid\")[0]");
//			executor.executeScript("let a=new Event('play')");
//			executor.executeScript("elem.dispatchEvent(a)");

//			
//			executor.executeScript("let clickevent=new Event('click')");
//			executor.executeScript("let elem=document.getElementById('video-player-container')");
//			executor.executeScript("elem.dispatchEvent(clickevent)");
			
//			Thread.sleep(14000);
//			driver.switchTo().defaultContent();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			cm.screenShot();
			ne.printStackTrace();
			Assert.assertNull(ne, "Unable to play video in the development phase. For QA-check the function checkVideoLoad");
			ne.printStackTrace();
			return false;
		}
	}

}
