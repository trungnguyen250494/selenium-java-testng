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

public class Topic_09_Button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().setSize(new Dimension(1366, 768));

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Fahasa() {
		// Navigate to URL
		driver.get("https://www.fahasa.com/customer/account/create");
		waitInSecond(3);

		// Switch to iFrame
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'moe-onsite-campaign')]"));
		driver.switchTo().frame(iframe);

		// Click to close the popup
		driver.findElement(By.cssSelector("button#close-icon")).click();

		waitInSecond(3);
		driver.switchTo().defaultContent();
		// Click Đăng nhập link
		driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();

		waitInSecond(3);
		// Verify the 'Đăng nhập' button is disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		waitInSecond(3);
		// Input valid values to Email/Password fields
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("test@yopmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("Test@123");

		// Get button color
		String rgbaColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		// Convert to hexa color
		String hexColor = Color.fromString(rgbaColor).asHex().toUpperCase();

		// Verify background color
		Assert.assertEquals(hexColor, "#C92127");
		waitInSecond(3);
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
