package webdriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_StaticWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");


	@BeforeClass
	public void beforeClass() {
		//runWithChrome();
		runWithFirefox();

	}

	public void runWithFirefox() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		
	}

	public void runWithChrome() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		
	}

	@Test
	public void TC_01_Not_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		waitInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_02_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		waitInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_More_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		waitInSecond(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
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
