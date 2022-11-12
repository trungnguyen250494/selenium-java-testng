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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;


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
	public void TC_01_Invisible_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_02_Invisible_Enough_Time() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_Invisible_More_Time() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Visible_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish > h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_05_Visible_Enough_Time() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish > h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_06_Visible_More_Time() {
		explicitWait = new WebDriverWait(driver, 50);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click button
		driver.findElement(By.cssSelector("div[id='start'] button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish > h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish > h4")).getText(), "Hello World!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
