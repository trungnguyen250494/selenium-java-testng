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
import java.util.Set;
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

public class Topic_18_Javascript_Executor {
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
	public void TC_01_Window() {

		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/basic-form/index.html");

		waitInSecond(3);

		// Get ID of current tab
		String parentPageWindowID = driver.getWindowHandle();

		// Click on Google link
		driver.findElement(By.xpath("//a[normalize-space()='GOOGLE']")).click();
		waitInSecond(3);

		switchToWindowByID(parentPageWindowID);

		String googleTabID = driver.getWindowHandle();

		// Check title of new window
		Assert.assertEquals(driver.getTitle(), "Google");

		// Switch to parentWindow
		driver.switchTo().window(parentPageWindowID);
		// Click on Facebook link
		driver.findElement(By.xpath("//a[normalize-space()='FACEBOOK']")).click();
		waitInSecond(3);

		switchToWindowByPageTitle("Facebook");
		// Check title of new window
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		// Switch to parentWindow
		driver.switchTo().window(parentPageWindowID);
		// Click on Tiki link
		driver.findElement(By.xpath("//a[normalize-space()='TIKI']")).click();
		waitInSecond(3);

		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		// Check title of new window
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		// Switch to parentWindow
		driver.switchTo().window(parentPageWindowID);
		waitInSecond(3);
		// Close all tabs except parentID
		closeAllWindowsWithoutParent(parentPageWindowID);

		// Verify current title/url
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_02_Window() {
		// Navigate to the Page URL
		driver.get("http://live.techpanda.org/");

		waitInSecond(3);
		String parentWindowID = driver.getWindowHandle();

		// Click the Mobile tab
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		waitInSecond(3);

		// Add Sony product to compare
		driver.findElement(By.xpath(
				"//a[normalize-space()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		// Add Samsung product to compare
		driver.findElement(By.xpath(
				"//a[normalize-space()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");
		waitInSecond(3);
		
		//Click Compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		waitInSecond(3);
		
		//switch to another tab and verify title
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		//Close all tabs expect parent window
		closeAllWindowsWithoutParent(parentWindowID);
		
		//Click Clear All link
		driver.findElement(By.xpath("//a[normalize-space()='Clear All']")).click();
		waitInSecond(3);
		
		//Accept alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		waitInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The comparison list was cleared.");

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

	public void switchToWindowByID(String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (id != otherID) {
				driver.switchTo().window(id);
			}
		}
	}

	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);

			// Get actual current title
			String actualPageTitle = driver.getTitle();

			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {

			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void scrollToElement(String xpathLocator) {
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
	}

}
