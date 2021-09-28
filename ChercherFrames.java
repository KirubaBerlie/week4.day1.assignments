package week4.day1.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChercherFrames {

	public static void main(String[] args) {
	
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Navigate to the URL
		driver.get(" https://chercher.tech/practice/frames-example-selenium-webdriver");
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("HandlingFrames");
	
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='frame3']"));
		driver.switchTo().frame(frame2);
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(1);
		WebElement animalDropdown = driver.findElement(By.id("animals"));
		
		Select dropdown = new Select(animalDropdown);
		dropdown.selectByVisibleText("Baby Cat");
		
		

	}

}
