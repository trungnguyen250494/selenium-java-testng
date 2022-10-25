package webdriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Topic_15_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

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
	public void TC_05_Java_Code_Geek() {
		// Navigate to the Page URL
		driver.get("https://www.javacodegeeks.com/");

		waitInSecond(10);

		WebElement popupSubscribe = driver
				.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])"));

		if (popupSubscribe.isDisplayed()) {
			System.out.println("Popup subscribe is displayed.");
			// Click 'x' button
			driver.findElement(By.xpath("//a[normalize-space()='×']")).click();
			waitInSecond(3);
		} else {
			System.out.println("Popup Subscribe does not show. Proceed to next step.");

		}

		// Input search value and click Search icon
		driver.findElement(By.xpath("//input[@id='s']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		waitInSecond(10);

		// Verify search result
		Assert.assertEquals(driver.findElement(By.xpath("//article[@class='item-list item_1']/h2/a")).getText(),
				"How To Perform Modern Web Testing With TestCafe Using JavaScript And Selenium");

	}

	@Test
	public void TC_06_VNK() {

		// Navigate to the Page URL
		driver.get("https://vnk.edu.vn/");

		waitInSecond(10);
		
		WebElement popupSubscribe = driver
				.findElement(By.xpath("//div[@class='tve-leads-conversion-object']"));

		if (popupSubscribe.isDisplayed()) {
			System.out.println("Popup subscribe is displayed.");
			// Click 'x' button
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			waitInSecond(3);
		} else {
			System.out.println("Popup Subscribe does not show. Proceed to next step.");

		}
	}
	
	@Test
	public void TC_07_DeHieu() {

		// Navigate to the Page URL
		driver.get("https://dehieu.vn/");

		waitInSecond(10);
		
		List<WebElement> popupSubscribe = driver
				.findElements(By.xpath("//div[@class='popup-content']"));

		if (popupSubscribe.size()>0) {
			System.out.println("Popup subscribe is displayed.");
			// Click 'x' button
			driver.findElement(By.xpath("//button[@id='close-popup']")).click();
			waitInSecond(3);
		} else {
			System.out.println("Popup Subscribe does not show. Proceed to next step.");

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

	public void scrollToElement(String xpathLocator) {
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
	}

}
