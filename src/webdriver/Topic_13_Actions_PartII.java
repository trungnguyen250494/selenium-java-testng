package webdriver;

import java.io.BufferedReader;
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

public class Topic_13_Actions_PartII {
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
	public void TC_07_Right_Click() {
		// Navigate to the Page URL
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		waitInSecond(3);
		action = new Actions(driver);
		action.contextClick(driver.findElement(By.xpath("//span[normalize-space()='right click me']"))).perform();
		waitInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//li[normalize-space()='Quit']"))).perform();
		waitInSecond(3);

		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());
	}

	@Test
	public void TC_08_Drag_And_Drop_HTML4() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		waitInSecond(3);
		action = new Actions(driver);
		action.dragAndDrop(driver.findElement(By.cssSelector("#draggable")),
				driver.findElement(By.cssSelector("#droptarget"))).perform();
		waitInSecond(3);

		// Get button color
		String rgbaColor = driver.findElement(By.cssSelector("#droptarget")).getCssValue("background-color");
		// Convert to hexa color
		String hexColor = Color.fromString(rgbaColor).asHex();

		// Verify background color
		Assert.assertEquals(hexColor, "#03a9f4");
		waitInSecond(3);

	}

	@Test
	public void TC_09_Drag_And_Drop_HTML5() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		waitInSecond(3);
		
		WebElement aSquare = driver.findElement(By.cssSelector("div#column-a"));
		WebElement bSquare = driver.findElement(By.cssSelector("div#column-b"));
		
		try {
			String jsContentFile = getContentFile(projectPath + "/dragAndDrop/drag_and_drop_helper.js");
			
			jsExecutor.executeScript(jsContentFile + "$('div#column-a').simulateDragDrop({dropTarget : 'div#column-b'});");
			waitInSecond(5);
			
			Assert.assertEquals(aSquare.getText(), "B");
			
			jsExecutor.executeScript(jsContentFile + "$('div#column-b').simulateDragDrop({dropTarget : 'div#column-a'});");
			Assert.assertEquals(aSquare.getText(), "A");
			waitInSecond(5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	

}
