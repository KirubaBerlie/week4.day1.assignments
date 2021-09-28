package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://dev83990.service-now.com/");
		String username = "admin";		String password = "Java@123";
		
		driver.switchTo().frame(0);
		
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password,Keys.ENTER);
		
		driver.findElement(By.id("filter")).sendKeys("incident",Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();

		
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		Thread.sleep(1000);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[text()='New']")).click();
		
		String incidentNo = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		
		Set<String> window1 = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(window1);
		driver.switchTo().window(list1.get(1));
		WebElement lookup1 = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[2]/td[3]/a"));
		String text = lookup1.getText();
		System.out.println(text);
		lookup1.click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.short_description")).click();
		
		Set<String> window2 = driver.getWindowHandles();
		List<String> list2 = new ArrayList<String>(window2);
		driver.switchTo().window(list2.get(1));
		
	driver.findElement(By.xpath("//a[text()='Issue with networking']")).click();
	driver.switchTo().window(list2.get(0));
	driver.switchTo().frame("gsft_main");
	driver.findElement(By.id("sysverb_insert")).click();
	
	driver.findElement(By.xpath("//span[@id='incident_hide_search']/div/div/input")).sendKeys(incidentNo,Keys.ENTER);

	String createdIncident = driver.findElement(By.xpath("(//td[@class='vt']/a[1])[1]")).getText();
	
	if(incidentNo.equals(createdIncident))
		System.out.println("Incident Created successfully");
	else
		System.out.println("Incorrect Incident fetched");
	

	
	 File src1 = driver.getScreenshotAs(OutputType.FILE);
	 File dst1 = new File("./snaps/service.png");
	 FileUtils.copyFile(src1, dst1);
		
	
	
	
	
	
		
	}

}
