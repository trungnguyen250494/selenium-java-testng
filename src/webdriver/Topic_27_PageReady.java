package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_PageReady {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

	}

	//@Test
	public void TC_01() {
		driver.get("https://opensource-demo.orangehrmlive.com");

		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");

		driver.findElement(By.id("btnLogin")).click();

		Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[normalize-space()='(1) Pending Self Review']")).isDisplayed());

	}

	@Test
	public void TC_02() {
		driver.get("https://blog.testproject.io/");

		Assert.assertTrue(isPageLoadedSuccess());
		
		WebElement ele = driver.findElement(By.xpath("//input[@class='search-field']/following-sibling::span"));

		//Creating object of an Actions class
		Actions action = new Actions(driver);

		//Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();
		
		driver.findElement(By.xpath("//input[@class='search-field']")).sendKeys("Selenium");
		
		driver.findElement(By.xpath("//input[@class='search-field']/following-sibling::span")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List <WebElement> resultTitles = driver.findElements(By.cssSelector("h3.post-title"));
		
		for (WebElement title : resultTitles) {
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
		
		
		
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}

		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}

		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
