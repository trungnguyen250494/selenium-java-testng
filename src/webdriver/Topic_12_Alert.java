package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Accept_Alert() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/basic-form/index.html");

		waitInSecond(3);

		// Scroll to the element
		scrollToElement("//legend[text()='JavaScript Alerts']");
		driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();

		waitInSecond(3);

		// Switch to alert
		alert = driver.switchTo().alert();

		// Verify text in alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		waitInSecond(3);

		// Click accept the alert
		alert.accept();

		// Verify the success message
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");

		waitInSecond(3);

	}

	@Test
	public void TC_02_Confirm_Alert() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/basic-form/index.html");

		waitInSecond(3);

		// Scroll to the element
		scrollToElement("//legend[text()='JavaScript Alerts']");
		driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();

		waitInSecond(3);

		// Switch to alert
		alert = driver.switchTo().alert();

		// Verify text in alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		waitInSecond(3);

		// Click accept the alert
		alert.dismiss();

		// Verify the success message
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");

		waitInSecond(3);

	}

	@Test
	public void TC_03_Prompt_Alert() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/basic-form/index.html");

		waitInSecond(3);

		// Scroll to the element
		scrollToElement("//legend[text()='JavaScript Alerts']");
		driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();

		waitInSecond(3);

		// Switch to alert
		alert = driver.switchTo().alert();

		// Verify text in alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		waitInSecond(3);

		String inputText = "trung";

		// Click accept the alert
		alert.sendKeys(inputText);
		waitInSecond(3);
		alert.accept();

		// Verify the success message
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + inputText);

		waitInSecond(3);

	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		// Navigate to the Page URL
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

		waitInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).getText(),"Congratulations! You must have the proper credentials.");

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

	public void scrollToElement(String xpathLocator) {
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
	}

}
