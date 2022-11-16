package webdriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_ExplicitWait_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	// Image path
	String uploadFileFolderPath = projectPath + File.separator + "picturesUpload" + File.separator;

	List<String> fileNames = getFileNameInFolder();

	@BeforeClass
	public void beforeClass() {
		runWithChrome();
		//runWithFirefox();

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

	//@Test
	public void TC_01_Telerik() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait = new WebDriverWait(driver, 15);

		// Wait until Date Time calendar is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
				"No Selected Dates to display.");

		// Wait until Day is clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='14']")));

		driver.findElement(By.xpath("//a[normalize-space()='14']")).click();

		// Wait until Ajax loading icon is not visible
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

		// Wait until selected date is chosen
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='14']")));

		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
				"Monday, November 14, 2022");

	}

	@Test
	public void TC_02_UploadFile() {
		driver.get("https://gofile.io/?t=uploadFiles");

		explicitWait = new WebDriverWait(driver, 30);

		// Load files

		String allFileNames = getAllFilesName();

		// Input multiple files
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#uploadFile-Input")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(allFileNames);

		// Wait until the loading icon disappeared
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress i.fa-spinner.fa-spin")));

		// Wait until all loading icon of images disappeared
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.progress.position-relative")));

		// Wait until the success message appears
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.text-success")));

		// Wait until 'Show Files' button is clickable
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));

		// Click 'Show Files' button
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();

		// Verify images loaded successfully
		for (String fileName : fileNames) {
			// Wait until file names and buttons 'Download'/'Play' displayed
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + fileName
					+ "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")));
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + fileName
					+ "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")));

			Assert.assertTrue(
					driver.findElement(By.xpath("//a[contains(@href,'"+fileName+"')]/span")).isDisplayed());
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public List<String> getFileNameInFolder() {
		File directoryPath = new File(uploadFileFolderPath);
		String contents[] = directoryPath.list();

		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < contents.length; i++) {
			System.out.println(contents[i]);
			fileNames.add(contents[i]);
		}
		return fileNames;
	}

	public String getAllFilesName() {
		File directoryPath = new File(uploadFileFolderPath);
		String contents[] = directoryPath.list();

		String allFileNames = "";
		for (int i = 0; i < contents.length; i++) {
			if (i == (contents.length - 1)) {
				allFileNames = allFileNames + uploadFileFolderPath + contents[i];
			} else {
				allFileNames = allFileNames + uploadFileFolderPath + contents[i] + "\n";
			}
			System.out.println(allFileNames);

		}

		return allFileNames;
	}

}
