package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_LoginTC_WebElement {
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
	public void TC_01_Login_With_Empty_Email_Password() {
		driver.get("http://live.techpanda.org/");
		//Click 'Account' button
		driver.findElement(By.cssSelector("a.skip-link.skip-account>span.label")).click();
		
		//Click 'My Account' link
	    driver.findElement(By.xpath("//div[@id='header-account']//li[@class='first']")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	  //Click 'Login' link
	    driver.findElement(By.xpath("//button[@title='Login']")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Verify all error messages
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		
	}

	@Test
	public void TC_03_Incorrect_Email() {
		
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		
	}

	@Test
	public void TC_05_Incorrect_Password() {
	
	}
	
	@Test
	public void TC_06_Invalid_Phone_Number() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
