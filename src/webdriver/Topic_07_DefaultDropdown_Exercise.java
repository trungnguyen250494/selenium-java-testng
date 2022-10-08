package webdriver;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_DefaultDropdown_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	

	
	@Test
	public void TC_03() {
		//Navigate to URL
		driver.get("https://demo.nopcommerce.com/register");
		waitInSecond(3);
		
		//Click the Register link
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		waitInSecond(5);
		
		//Select values in the Day, Month, Year dropdown list
		Select dropdownDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		dropdownDay.selectByVisibleText("1");
		Select dropdownMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		dropdownMonth.selectByVisibleText("May");
		Select dropdownYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		dropdownYear.selectByVisibleText("1980");
		
		//Input all valid values
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Trung");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Nguyen");
		Random rd = new Random();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("trungnguyen"+rd.nextInt(1000)+"@yopmail.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Tester@123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Tester@123");
		driver.findElement(By.cssSelector("input#gender-male")).click();
		
		//Click the Register button
		driver.findElement(By.cssSelector("button#register-button")).click();
		waitInSecond(5);
		
		//Verify the text indicating that register successfully
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Your registration completed"));
		
		//Click to My Account link
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		waitInSecond(5);
		
		//Verify the selected values of dropdown lists
		dropdownDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		dropdownMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		dropdownYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		
		Assert.assertEquals(dropdownDay.getFirstSelectedOption().getText(), "1");
		Assert.assertEquals(dropdownMonth.getFirstSelectedOption().getText(), "May");
		Assert.assertEquals(dropdownYear.getFirstSelectedOption().getText(), "1980");
		
	}


	@Test
	public void TC_04() {
		//Navigate to URL
		driver.get("https://www.rode.com/wheretobuy");
		waitInSecond(7);
		
		//Click Allow Cookies button
		driver.findElement(By.xpath("//button[normalize-space()='Allow All']")).click();
		
		//Verify the Country dropdown list is not multiple-selected
		Select select = new Select(driver.findElement(By.cssSelector("select#country")));
		Assert.assertFalse(select.isMultiple());
		
		//Select and verify Vietnam is selected in the dropdown list
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		//Select the Search button
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		
		waitInSecond(7);
		//Find all dealers
		List<WebElement> dealers = driver.findElements(By.cssSelector("div#map h4"));
	    for (WebElement webElement : dealers) {
			System.out.println(webElement.getText()+"\n");
		}
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void waitInSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
