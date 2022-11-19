package webdriver;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	long durationTime = 15;
	int intervalTime = 100;

	@BeforeClass
	public void beforeClass() {
		runWithChrome();
		// runWithFirefox();

	}

	public void runWithFirefox() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();

	}

	public void runWithChrome() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();

	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElementByCSSLocator("div#javascript_countdown_time");
		
		fluentElement = new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(durationTime))
		.pollingEvery(Duration.ofMillis(intervalTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				// TODO Auto-generated method stub
				return element.getText().endsWith("00");
			}
		});

	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElementByXPathLocator("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElementByXPathLocator("//div[@id='finish']/h4").getText(), "Hello World!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement findElementByCSSLocator(String cssLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(NoSuchElementException.class);

		// Apply conditions
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector(cssLocator));
			}
		});

		return element;
	}
	
	public WebElement findElementByXPathLocator(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(NoSuchElementException.class);

		// Apply conditions
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});

		return element;
	}

}
