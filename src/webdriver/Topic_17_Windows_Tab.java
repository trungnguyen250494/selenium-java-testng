package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Windows_Tab {
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
	public void TC_01_Live_Tech_Panda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		waitInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");

		highlightElement("//a[normalize-space()='Mobile']");
		waitInSecond(3);
		clickToElementByJS("//a[normalize-space()='Mobile']");
		waitInSecond(3);

		highlightElement("//a[normalize-space()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS(
				"//a[normalize-space()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		waitInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[normalize-space()='Customer Service']");
		waitInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");

		scrollToElementOnDown("//input[@id='newsletter']");
		highlightElement("//input[@id='newsletter']");

		Random rd = new Random();
		int rdNumber = rd.nextInt(1000);
		System.out.println(rdNumber);
		sendkeyToElementByJS("//input[@id='newsletter']", "test" + rdNumber + "@yopmail.com");
		highlightElement("//button[@title='Subscribe']/span");
		waitInSecond(3);
		clickToElementByJS("//button[@title='Subscribe']/span");
		waitInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		waitInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	}

	@Test
	public void TC_02_HTML5_Validation_message() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		waitInSecond(3);

		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='fname']", "Trung");
		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='pass']", "123456");
		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "123!@!$@#%$");
		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),
				"A part following '@' should not contain the symbol '!'.");

		sendkeyToElementByJS("//input[@id='em']", "123@456");
		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");

		sendkeyToElementByJS("//input[@id='em']", "123@yopmail.com");
		clickToElementByJS("//input[@name='submit-btn']");
		waitInSecond(3);
		Assert.assertEquals(
				getElementValidationMessage(
						"//b[contains(text(),'✱ ADDRESS')]//parent::label/following-sibling::select"),
				"Please select an item in the list.");

	}
	
	@Test
	public void TC_04_Remove_attribute() {
		navigateToUrlByJS("http://demo.guru99.com/v4");
		waitInSecond(3);
		
		highlightElement("//input[@name='uid']");
		waitInSecond(3);
		sendkeyToElementByJS("//input[@name='uid']", "mngr451176");
		
		highlightElement("//input[@name='password']");
		waitInSecond(3);
		sendkeyToElementByJS("//input[@name='password']", "upEgUgU");
		
		highlightElement("//input[@name='btnLogin']");
		waitInSecond(3);
		clickToElementByJS("//input[@name='btnLogin']");
		waitInSecond(3);
		
		highlightElement("//a[normalize-space()='New Customer']");
		waitInSecond(3);
		clickToElementByJS("//a[normalize-space()='New Customer']");
		waitInSecond(3);
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		waitInSecond(3);
		
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		waitInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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

	public void switchToWindowByID(String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (id != otherID) {
				driver.switchTo().window(id);
			}
		}
	}

	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);

			// Get actual current title
			String actualPageTitle = driver.getTitle();

			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {

			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void scrollToElement(String xpathLocator) {
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
	}

}
