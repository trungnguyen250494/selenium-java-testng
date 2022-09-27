package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Elements_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	

	@Test
	public void TC_01_CheckElementsDisplayed() {
		//Navigate to URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Verify Email textbox displayed on the page
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		}
		else {
			System.out.println("Email textbox is not displayed");
		}
		
		//Verify Radio Button 'Under 18' displayed on the page
		WebElement rdUnder18 = driver.findElement(By.id("under_18"));
	    if (rdUnder18.isDisplayed()) {
			rdUnder18.click();
			System.out.println("Age Under 18 radio is displayed");
		} else {
			System.out.println("Age Under 18 radio is not displayed");
		}
	    
	    //Verify Education textbox displayed on the page
	    WebElement eduTextarea = driver.findElement(By.id("edu"));
	    if (eduTextarea.isDisplayed()) {
	    	eduTextarea.sendKeys("Automation Testing");
			System.out.println("Education textarea is displayed");
		}
		else {
			System.out.println("Education textarea is not displayed");
		}
	    
	    //Verify Element User5 not displayed on the page at the first time
	    
	    WebElement User5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
	    if (User5.isDisplayed()) {
	    	System.out.println("User5 is displayed");
	    	driver.findElement(By.id("under_18")).click();
	    }
	    else {
	    	System.out.println("User5 is not displayed");
	    }
	    

	   
	}

	@Test
	public void TC_02_Check_Enabled_Disabled() {
		//Navigate to URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
				
		//Verify Email textbox enabled on the page
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isEnabled()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is enabled");
		}
		else {
			System.out.println("Email textbox is disabled");
		}
		
		//Verify Radio Button 'Under 18' enabled on the page
		WebElement rdUnder18 = driver.findElement(By.id("under_18"));
		if (rdUnder18.isEnabled()) {
			rdUnder18.click();
			System.out.println("Age Under 18 radio is enabled");
		} else {
			System.out.println("Age Under 18 radio is disabled");
		}
	    
	    //Verify Education textbox enabled on the page
		 WebElement eduTextarea = driver.findElement(By.id("edu"));
		    if (eduTextarea.isEnabled()) {
		    	eduTextarea.sendKeys("Automation Testing");
				System.out.println("Education textarea is enabled");
			}
			else {
				System.out.println("Education textarea is disabled");
			}
	    
	    //Verify Job Role 1 enabled on the page
		WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
		if (jobRole1.isEnabled()) {
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disabled");
		}
	    
	    //Verify Job Role 2 enabled on the page
		WebElement jobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
		if (jobRole2.isEnabled()) {
			System.out.println("Job Role 2 is enabled");
		} else {
			System.out.println("Job Role 2 is disabled");
		}
	    
	    //Verify Interests (Development) checkbox enabled on the page
		WebElement InterestDevCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		if (InterestDevCheckbox.isEnabled()) {
			System.out.println("Interests (Development) checkbox is enabled");
		} else {
			System.out.println("Interests (Development) checkbox is disabled");
		}
	    
	    //Verify Slider 01 enabled on the page
		WebElement slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if (slider01.isEnabled()) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disabled");
		}
	    
	    //Verify Password disabled on the page
		WebElement Password = driver.findElement(By.xpath("//input[@id='disable_password']"));
		if (Password.isEnabled()) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password is disabled");
		}
	    
	    //Verify Age (Radio button) disabled on the page
		WebElement AgeRd = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		if (Password.isEnabled()) {
			System.out.println("Age (Radio button) is enabled");
		} else {
			System.out.println("Age (Radio button) is disabled");
		}
	    
	    //Verify Biography disabled on the page
		WebElement Biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
		if (Biography.isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disabled");
		}
	    
	    //Verify Job Role 3 disabled on the page
		WebElement jobRole3 = driver.findElement(By.xpath("//select[@id='job3']"));
		if (jobRole3.isEnabled()) {
			System.out.println("Job Role 3 is enabled");
		} else {
			System.out.println("Job Role 3 is disabled");
		}
	    
	    //Verify Interests checkbox disabled on the page
		WebElement InterestCheckbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		if (InterestCheckbox.isEnabled()) {
			System.out.println("Interests checkbox is enabled");
		} else {
			System.out.println("Interests checkbox is disabled");
		}
	     
	   //Verify Slider 02 disabled on the page
		WebElement slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		if (slider02.isEnabled()) {
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02 is disabled");
		}
	   
	}

	@Test
	public void TC_03_Check_Selected() {
		//Navigate to URL
	    driver.get("https://automationfc.github.io/basic-form/index.html");
	    
	    //Click Age Under 18 Radio button and verify
	    WebElement rdUnder18 = driver.findElement(By.id("under_18"));
	    rdUnder18.click();
	    if (rdUnder18.isSelected()) {
			System.out.println("Age Under 18 Radio button is selected");
		} else {
			System.out.println("Age Under 18 Radio button is not selected");
		}
	    
	    //Click "Languages: Java" checkbox
	    WebElement languageJavaCheckbox = driver.findElement(By.id("java"));
	    languageJavaCheckbox.click();
	    if (languageJavaCheckbox.isSelected()) {
			System.out.println("\"Languages: Java\" checkbox is selected");
		} else {
			System.out.println("\"Languages: Java\" checkbox is not selected");
		}
	    
	    //Click to de-select "Languages: Java" checkbox
	    languageJavaCheckbox.click();
	    if (languageJavaCheckbox.isSelected()) {
			System.out.println("\"Languages: Java\" checkbox is selected");
		} else {
			System.out.println("\"Languages: Java\" checkbox is not selected");
		}
	}
	
	@Test
	public void TC_04_Register_Func_MailChimp() throws InterruptedException {
		//Navigate to URL
	    driver.get("https://login.mailchimp.com/signup/");
	    
	    //Input valid email
	    driver.findElement(By.id("email")).sendKeys("abc94TrungNguyen@gmail.com");
	    
	    
	    //Input password
	    WebElement password = driver.findElement(By.id("new_password"));
	    
	    //Verify inputting only numbers
	    password.sendKeys("123");
	    //Click button 'Show Password'
	    driver.findElement(By.xpath("//label[@title='Show Password']")).click();
	    
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    
	    
	    Thread.sleep(3000);
	    
	    //Verify inputting only lower case
	    password.clear();
	    password.sendKeys("abc");
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    
	    Thread.sleep(3000);
	    
	    //Verify inputting only upper case
	    password.clear();
	    password.sendKeys("ABC");
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    
	    Thread.sleep(3000);
	    
	    //Verify inputting only special character
	    password.clear();
	    password.sendKeys("@#$");
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    
	    Thread.sleep(3000);
	    
	    //Verify inputting all conditions
	    password.sendKeys("123abcA@#$");
	    Assert.assertTrue(driver.findElement(By.xpath("//input[@class='av-password success-check']")).isDisplayed());
	    
	    Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
