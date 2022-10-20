package webdriver;

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

public class Topic_13_Actions_PartI {
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
	public void TC_01_Tooltip() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		waitInSecond(3);
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		waitInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_Hover() {
		// Navigate to the Page URL
		driver.get("https://www.myntra.com/");

		waitInSecond(3);
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main'][normalize-space()='Kids']")))
				.perform();
		waitInSecond(3);

		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

		waitInSecond(3);

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(.,'Kids Home Bath')][@class='breadcrumbs-crumb']")).getText(),
				"Kids Home Bath");
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Kids Home Bath']")).getText(),
				"Kids Home Bath");

	}

	@Test
	public void TC_03_Hover() {
		// Navigate to the Page URL
		driver.get("https://www.fahasa.com/");

		waitInSecond(3);

		// Switch to iFrame
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'moe-onsite-campaign')]"));
		driver.switchTo().frame(iframe);

		// Click to close the popup
		driver.findElement(By.cssSelector("button#close-icon")).click();

		waitInSecond(3);
		driver.switchTo().defaultContent();
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu")))
				.perform();
		waitInSecond(3);

		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();

		waitInSecond(3);

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='mb-breadcrumbs']//strong[text()='Kỹ năng sống']")).getText(),
				"KỸ NĂNG SỐNG");

	}
	
	@Test
	public void TC_04_Click_And_Hold() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/jquery-selectable/");

		waitInSecond(3);

		List <WebElement> elements = driver.findElements(By.cssSelector("ol.ui-selectable > li"));
		
		action = new Actions(driver);
		action.clickAndHold(elements.get(0))
		.moveToElement(elements.get(3)).release().perform();
		
		List <WebElement> elementsSelected = driver.findElements(By.cssSelector("ol.ui-selectable > li.ui-selected"));
		
		//Verify all 4 items are selected
		Assert.assertEquals(elementsSelected.size(), 4);
		
		String[] listNumberSelectedExpectation = {"1","2","3","4"};
		
		ArrayList<String> listNumberSelectedActual = new ArrayList<String>();
		
		for (WebElement number: elementsSelected) {
			listNumberSelectedActual.add(number.getText());
		}
		
		Assert.assertEquals(Arrays.asList(listNumberSelectedExpectation),listNumberSelectedActual);
		
		waitInSecond(3);
	}

	@Test
	public void TC_05_Select_Random() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/jquery-selectable/");

		waitInSecond(3);

		List <WebElement> elements = driver.findElements(By.cssSelector("ol.ui-selectable > li"));
		
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).perform();
		elements.get(0).click();
		elements.get(2).click();
		elements.get(5).click();
		elements.get(10).click();
		action.keyUp(Keys.CONTROL).perform();
		
		List <WebElement> elementsSelected = driver.findElements(By.cssSelector("ol.ui-selectable > li.ui-selected"));
		
		//Verify all 4 items are selected
		Assert.assertEquals(elementsSelected.size(), 4);
		
		String[] listNumberSelectedExpectation = {"1","3","6","11"};
		
		ArrayList<String> listNumberSelectedActual = new ArrayList<String>();
		
		for (WebElement number: elementsSelected) {
			listNumberSelectedActual.add(number.getText());
		}
		
		Assert.assertEquals(Arrays.asList(listNumberSelectedExpectation),listNumberSelectedActual);
		
		waitInSecond(3);
	}
	
	@Test
	public void TC_06_Double_Click() {
		// Navigate to the Page URL
		driver.get("https://automationfc.github.io/basic-form/index.html");

		waitInSecond(3);
		scrollToElement("//button[normalize-space()='Double click me']");
		action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//button[normalize-space()='Double click me']"))).perform();
		waitInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
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
