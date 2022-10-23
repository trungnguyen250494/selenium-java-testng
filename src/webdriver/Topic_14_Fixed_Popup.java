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

public class Topic_14_Fixed_Popup {
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
	public void TC_01_NgoaiNgu() {
		// Navigate to the Page URL
		driver.get("https://ngoaingu24h.vn/");

		waitInSecond(3);

		WebElement loginPopup = driver.findElement(By.xpath("(//div[@id='modal-login-v1'])[1]"));
		// Check 'Đăng nhập' popup does not display
		Assert.assertFalse(loginPopup.isDisplayed());

		// Click Login button
		driver.findElement(By.cssSelector(".login_.icon-before")).click();
		waitInSecond(3);

		// Check if 'Đăng nhập' popup displays
		Assert.assertTrue(loginPopup.isDisplayed());

		// Input username and password
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");

		// Click 'Đăng nhập button'
		driver.findElement(By.xpath("(//button[@data-text='Đăng nhập'])[1]")).click();
		waitInSecond(3);

		// Verify error message displayed
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Tài khoản không tồn tại!')]")).getText(),
				"Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Kyna() {
		// Navigate to the Page URL
		driver.get("https://kyna.vn/");

		waitInSecond(3);

		// Click to close the popup
		driver.findElement(By.xpath("//a[@title='Close']")).click();

		waitInSecond(3);

		// Click login button
		driver.findElement(By.cssSelector("a.login-btn")).click();
		waitInSecond(3);

		WebElement loginPopup = driver.findElement(By.cssSelector("div.k-popup-account-mb-content"));
		// Check if Login popup is displayed
		Assert.assertTrue(loginPopup.isDisplayed());

		// Input username and password
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");

		// Click login button
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		waitInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close > span")).click();
		waitInSecond(3);

		Assert.assertFalse(loginPopup.isDisplayed());

	}

	@Test
	public void TC_03_Tiki() {
		// Navigate to the Page URL
		driver.get("https://tiki.vn/");

		waitInSecond(3);

		// Click to 'Đăng nhập/đăng ký' link
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();

		waitInSecond(3);

		// Check if Login popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn-close']/parent::div")).isDisplayed());

		// Click the link 'Login with email'
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		waitInSecond(3);

		// Click 'Đăng nhập' button without inputting any fields
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		waitInSecond(3);

		// Verify error message displayed
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(),
				"Email không được để trống");

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(),
				"Mật khẩu không được để trống");

		// Click the Close button
		driver.findElement(By.cssSelector("button.btn-close")).click();
		waitInSecond(3);

		// Verify Login popup does not show
		Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn-close']/parent::div")).size(), 0);

	}

	@Test
	public void TC_04_Facebook() {
		// Navigate to the Page URL
		driver.get("https://www.facebook.com/");

		waitInSecond(3);

		// Click to 'Tạo tài khoản mới' link
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		waitInSecond(3);

		// Check if Sign Up popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());


		// Click the Close button
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		waitInSecond(3);

		// Verify Sign Up popup does not show
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
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
