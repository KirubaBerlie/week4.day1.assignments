package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * //Pseudo Code * 
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login" * 
 * 2. Enter UserName and Password Using Id Locator * 
 * 3. Click on Login Button using Class Locator * 
 * 4. Click on CRM/SFA Link * 
 * 5. Click on contacts Button * 	
 * 6. Click on Merge Contacts using Xpath Locator * 
 * 7. Click on Widget of From Contact * 
 * 8. Click on First Resulting Contact * 
 * 9. Click on Widget of To Contact * 
 * 10. Click on Second Resulting Contact * 
 * 11. Click on Merge button using Xpath Locator * 
 * 12. Accept the Alert * 
 * 13. Verify the title of the page
 */

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("http://leaftaps.com/opentaps/control/login");
		// Username and Password

		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		// Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// Click on CRM/SFA Link
		driver.findElement(By.partialLinkText("CRM/SFA")).click();

		// click on Contact button
		driver.findElement(By.linkText("Contacts")).click();

		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		Thread.sleep(2000);
		// Click on Widget of From Contact
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();

		
		// Move to next Window
		Set<String> window = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(window);
		driver.switchTo().window(list.get(1));
		
		//// Click on First Resulting Contact 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[contains(@class,'td-partyId x-grid3-cell-first')]/div/a")).click();

	
		driver.switchTo().window(list.get(0));
		Thread.sleep(3000);
		String title2 = driver.getTitle();
		System.out.println("Tile is " + title2);
		
		// 	// Click on Widget To contact
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		System.out.println("IMAGE2 CLICKED");

		System.out.println(list);

		Set<String> window1 = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(window1);
		
		driver.switchTo().window(list1.get(1));
		Thread.sleep(3000);
		// Click on second Resulting Contact 
		driver.findElement(By.xpath("(//td[contains(@class,'td-partyId x-grid3-cell-first')])[2]/div/a")).click();
		driver.switchTo().window(list1.get(0));
		driver.findElement(By.className("buttonDangerous")).click();
		
		//Accept the Alert 
		Alert alert = driver.switchTo().alert();
		alert.accept();

		String title = driver.getTitle();
		System.out.println(title);
		
		if (title.contains("View Contact"))
			System.out.println("Contact successfully Merged");
		else
			System.out.println("Error in contact Merge");

	}

}
