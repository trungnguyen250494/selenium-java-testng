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

public class Topic_10_Default_Checkbox_RadioButton {
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
	public void TC_01_KendoUI() {
		//Navigate to Checkbox Page URL
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		//Wait until the checkbox is loaded
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[normalize-space()='Dual-zone air conditioning']")));
		
		//Check if the checkbox is selected or not
		if (!driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected()) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@role='link']")));
			driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).click();
		}
		waitInSecond(3);
		
		//Verify if the checkbox is selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected());
		
		//Delect the checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).click();
		waitInSecond(3);
		
		//Verify if the checkbox is de-selected
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected());
		
		//Navigate to Radio button Page URL
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Wait until the radio button is loaded
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[normalize-space()='2.0 Petrol, 147kW']//preceding-sibling::input")));
		
		//Check if the radio button is selected or not
		if (!driver.findElement(By.xpath("//label[normalize-space()='2.0 Petrol, 147kW']//preceding-sibling::input")).isSelected()) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@role='link']")));
			driver.findElement(By.xpath("//label[normalize-space()='2.0 Petrol, 147kW']//preceding-sibling::input")).click();
		}
		waitInSecond(3);
		
		//Verify if the radio button is selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='2.0 Petrol, 147kW']//preceding-sibling::input")).isSelected());
	}
	
	@Test
	public void TC_02() {
	
	}
	
	@Test
	public void TC_05_Editable() {

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
