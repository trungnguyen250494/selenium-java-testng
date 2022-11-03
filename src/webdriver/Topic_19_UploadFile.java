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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	

	// Image path
	String uploadFileFolderPath = projectPath + File.separator + "picturesUpload" + File.separator;
	
	List<String> fileNames = getFileNameInFolder();


	@BeforeClass
	public void beforeClass() {
		//runWithChrome();
		runWithFirefox();

	}

	public void runWithFirefox() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void runWithChrome() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Upload_1_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		waitInSecond(3);

		// Load files
		for (String fileName : fileNames) {
			driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(uploadFileFolderPath+ fileName);
			waitInSecond(3);
		}
		
		//Verify images loaded successfully
		for (String fileName : fileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName+"']")).isDisplayed());
		}
		
		//Upload files
		List <WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		
		for (WebElement startButton : startButtons) {
			startButton.click();
			waitInSecond(3);
		}
		
		//Verify images uploaded successfully
		for (String fileName : fileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName+"']")).isDisplayed());
		}

	}

	@Test
	public void TC_02_Upload_Multiple_Files_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		waitInSecond(3);

		// Load files
		
		
		String allFileNames= getAllFilesName();
		
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(allFileNames);
		
		//Verify images loaded successfully
		for (String fileName : fileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName+"']")).isDisplayed());
		}
		
		//Upload files
		List <WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		
		for (WebElement startButton : startButtons) {
			startButton.click();
			waitInSecond(3);
		}
		
		//Verify images uploaded successfully
		for (String fileName : fileNames) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName+"']")).isDisplayed());
		}

	}

	@Test
	public void TC_03() {

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

		String allFileNames="";
		for (int i = 0; i < contents.length; i++) {
			if(i == (contents.length-1)) {
				allFileNames = allFileNames + uploadFileFolderPath + contents[i];
			}
			else
			{
				allFileNames = allFileNames + uploadFileFolderPath + contents[i] + "\n";
			}
			System.out.println(allFileNames);
			
		}
		
		return allFileNames;
	}
}
