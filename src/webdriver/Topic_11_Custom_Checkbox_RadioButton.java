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

public class Topic_11_Custom_Checkbox_RadioButton {
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

		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_Angular() {
		// Navigate to Radio button Page URL
		driver.get("https://material.angular.io/components/radio/examples");

		waitInSecond(3);

		// Check if the radio button is selected or not
		if (!driver.findElement(By.xpath("//input[@value='Summer']")).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='Summer']")));

		}
		waitInSecond(3);

		// Verify if the radio button is selected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());

		// Navigate to Checkbox Page URL
		driver.get("https://material.angular.io/components/checkbox/examples");

		waitInSecond(3);

		// Check if the checkboxes are selected or not
		if (!driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")));

		}
		waitInSecond(3);

		// Verify if the checkbox 'Checked' is selected
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")).isSelected());

		if (!driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input"))
				.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input")));

		}
		waitInSecond(3);

		// Verify if the checkbox 'Indeterminate' is selected
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input"))
				.isSelected());

		// Check if the checkboxes are selected or not
		if (driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")));

		}
		waitInSecond(3);

		// Verify if the checkbox 'Checked' is not selected
		Assert.assertFalse(
				driver.findElement(By.xpath("//span[text()='Checked']//preceding-sibling::span/input")).isSelected());

		// Check if the checkboxes are selected or not
		if (driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input"))
				.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input")));

		}
		waitInSecond(3);

		// Verify if the checkbox 'Indeterminate' is not selected
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Indeterminate']//preceding-sibling::span/input"))
				.isSelected());

	}

	@Test
	public void TC_02_GoogleDoc() {
		// Navigate to Radio button Page URL
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		// Wait until the element is loaded
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div")));

		// Verify radio button is not selected
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div[@aria-checked='false']"))
				.isDisplayed());

		jsExecutor.executeScript("arguments[0].click();", driver
				.findElement(By.xpath("//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div")));

		waitInSecond(3);
		
		//Verify radio button is selected
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div[@aria-checked='true']"))
				.isDisplayed());

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
