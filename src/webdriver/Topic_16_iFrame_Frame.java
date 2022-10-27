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

public class Topic_16_iFrame_Frame {
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
	public void TC_08_iFrame() {

		// Navigate to the Page URL
		driver.get("https://kyna.vn/");

		waitInSecond(10);

		// Verify popup displayed
		List<WebElement> popupSubscribe = driver.findElements(By.cssSelector("div.fancybox-outer"));

		if (popupSubscribe.size() > 0) {
			System.out.println("Popup subscribe is displayed.");
			// Click 'x' button
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			waitInSecond(3);
		} else {
			System.out.println("Popup Subscribe does not show. Proceed to next step.");

		}

		// Verify Facebook iframe displayed
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src,'www.facebook.com')]"));
		Assert.assertTrue(iframe.isDisplayed());
		driver.switchTo().frame(iframe);

		// Verify the subscriber numbers
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"166K likes");

		driver.switchTo().defaultContent();

		// Switch to Chatbox iframe
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.border_overlay")).click();

		// Input all mandatory fields
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Michal");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		Select select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Hello I'm here");
		waitInSecond(3);
		driver.findElement(By.cssSelector("input.submit")).click();
		waitInSecond(3);

		// Switch to Main Page and search with keyword 'Excel'
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		waitInSecond(3);

		// Verify search results
		List<WebElement> resultTitles = driver
				.findElements(By.cssSelector("div.k-box-card-wrap.clearfix > div.content > h4"));

		for (WebElement webElement : resultTitles) {
			Assert.assertTrue(webElement.getText().contains("Excel"));
		}

	}

	@Test
	public void TC_09_Frame() {
		// Navigate to the Page URL
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		waitInSecond(3);
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		waitInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		
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
