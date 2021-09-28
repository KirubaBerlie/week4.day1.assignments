package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotAndFramesCount4 {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		
		driver.get("http://leafground.com/pages/frame.html");
		

		Thread.sleep(2000);
		WebElement frame1 = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[1]"));
		driver.switchTo().frame(frame1);
		WebElement clickMeButton= driver.findElement(By.id("Click"));
		
		 File src1 = clickMeButton.getScreenshotAs(OutputType.FILE);
		 File dst1 = new File("./snaps/button.png");
		 FileUtils.copyFile(src1, dst1);
		 
		 driver.switchTo().defaultContent();
		 List<WebElement> frames =driver.findElements(By.tagName("iframe"));
		 System.out.println("Number of Frames: "+ frames.size());;
	}

}
