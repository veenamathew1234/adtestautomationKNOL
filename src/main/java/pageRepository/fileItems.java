package pageRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.StartUp;

public class fileItems  extends StartUp {
	
	
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
	
	public boolean checkVideoLoad() throws InterruptedException
	{
		WebElement frame=driver.findElement(By.xpath("//iframe[contains(@id,'video-player-container_ifp')]"));
		driver.switchTo().frame(frame);
		Thread.sleep(1000);
		//WebElement tobeDragged=driver.findElement(By.xpath("//div[contains(@class,'mwPlayerContainer kdark ua-mouse ua-chrome size-large pause-state')]//span[contains(@class,'ui-slider-handle ui-corner-all ui-state-default')]"));
		//new Actions(driver).dragAndDropBy(tobeDragged, 200, 0).build() .perform();
		//driver.findElement(By.cssSelector("div[class='sliderPreview'][style*='background-position: -9100px 0px']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'videoHolder hover')]//a[contains(@class,'icon-play  comp largePlayBtn  largePlayBtnBorder')]")).click();
		Thread.sleep(12000);
		driver.switchTo().defaultContent();
		return true;
	}

}
