package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Browser_Exercise {
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
	public void TC_01_Verify_URL() {
		//Navigate to URL
		driver.get("http://live.techpanda.org/");
		
		//Click 'My Account' link in the Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify URL of Login page
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click 'Create Account' button
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		//Verify URL of Register page
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {
		//Navigate to URL
		driver.get("http://live.techpanda.org/");
				
		//Click 'My Account' link in the Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				
		//Verify Title of Login page
		Assert.assertEquals(driver.getTitle(), "Customer Login");
				
		//Click 'Create Account' button
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
				
		//Verify Title of Register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_Function() {
		//Navigate to URL
		driver.get("http://live.techpanda.org/");
				
		//Click 'My Account' link in the Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				
		//Click 'Create Account' button
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
				
		//Verify URL of Register page
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
		//Navigate back to the Login Page
		driver.navigate().back();
		
		//Verify URL of Login page
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		//Forward to the Register Page
		driver.navigate().forward();
		
		//Verify Title of Register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_Get_Page_SourceCode() {
		//Navigate to URL
		driver.get("http://live.techpanda.org/");
				
		//Click 'My Account' link in the Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				
		//Verify Login page contains text 'Login or Create an Account'
		String pageSource = driver.getPageSource();
		Assert.assertEquals(pageSource.contains("Login or Create an Account"),true);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
