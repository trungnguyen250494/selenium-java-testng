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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_CustomDropdown {
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
	public void TC_01_JQuery() {
		//Navigate to URL
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		
		//Click the Dropdown button and wait until all items are loaded
		selectItemInCustomDropDown("span#number-button", "ul#number-menu div", "19");
			
		//Check if the item is selected
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
	}


	@Test
	public void TC_02_Honda() {
		//Navigate to URL
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".btn.btn-primary.x")));
		driver.findElement(By.cssSelector(".btn.btn-primary.x")).click();
		
		scrollToElement("div.carousel-item");
		waitInSecond(3);
		//Click the Dropdown button and wait until all items are loaded
		selectItemInCustomDropDown("button#selectize-input", "button#selectize-input + div>a", "CITY L");
		
		waitInSecond(3);
		scrollToElement("div.container");
		
		waitInSecond(3);
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		
		waitInSecond(3);
		select.selectByVisibleText("Cần Thơ");
		waitInSecond(3);
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		waitInSecond(3);
		
		
	}
	
	@Test
	public void TC_03_ReactJS() {
		//Navigate to URL
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		
		//Click the Dropdown button and wait until all items are loaded
		selectItemInCustomDropDown("div[role='listbox']", "div[role='listbox'] span", "Christian");
			
		waitInSecond(3);
	}
	
	@Test
	public void TC_04_VueJS() {
		//Navigate to URL
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		
		//Click the Dropdown button and wait until all items are loaded
		selectItemInCustomDropDown("li.dropdown-toggle", "ul.dropdown-menu li>a", "Third Option");
			
		waitInSecond(3);
	}
	
	@Test
	public void TC_05_Editable() {
		//Navigate to URL
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input.search")));
		driver.findElement(By.cssSelector("input.search")).sendKeys("a");
		waitInSecond(3);
		
		//Click the Dropdown button and wait until all items are loaded
		selectItemInCustomDropDown("input.search", "div.visible.menu.transition span", "Australia");
			
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
	
	public void selectItemInCustomDropDown(String locatorButton,String locatorItems, String inputText) {
		driver.findElement(By.cssSelector(locatorButton)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locatorItems)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(locatorItems));
		for (WebElement item : allItems) {
			String textItem = item.getText();
			if (textItem.equals(inputText)) {
				item.click();
				break;
			}
		}
	}
	
	public void scrollToElement(String cssLocator) {
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssLocator)));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}
}
