package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_TextArea_TextBox_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String customerName = "Trung";
	String dateofBirth = "1998-06-09";
	String address = "111 Quang Trung";
	String city = "Ho Chi Minh";
	String state = "Go Vap";
	String pin = "012345";
	String mobileNumber = "012345678";
	Random random = new Random();
	int rd = random.nextInt(1000);
	String email = "trungnguyen"+rd+"@yopmail.com";
	String password = "Tester@123";

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
	public void TC_01() {
		//Navigate to URL
		driver.get("http://demo.guru99.com/v4");
		waitInSecond(3);
		
		//Input values for username, password
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr444384");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("EqUmAmy");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		
		waitInSecond(3);
		
		//Click on New Customer link
		driver.findElement(By.xpath("//a[normalize-space()='New Customer']")).click();
		
		waitInSecond(5);
		
		
		//Input all mandatory fields
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dateofBirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		//Click Submit button
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		waitInSecond(3);
		
		//Get customerID and verify all input values
		String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateofBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		//Click Edit Customer link
		driver.findElement(By.xpath("//a[normalize-space()='Edit Customer']")).click();
		
		waitInSecond(3);
		
		//Input and submit customerID
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		waitInSecond(3);
		//Verify customerName and address value
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);
		
		//Edit and update values
		address = "Update address";
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		
		city = "Update city";
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		
		state = "Update state";
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		
		pin = "098765";
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		
		mobileNumber = "0987654321";
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		
		rd = random.nextInt(1000);
		email = "trungnguyen"+rd+"@yopmail.com";
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		
		//Click Submit
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		waitInSecond(5);
		
		if(driver.switchTo().alert() != null)
		{
		    Alert alert = driver.switchTo().alert();
		    alert.dismiss(); 
		}
		
		//Redirect to Edit Customer page
		driver.get("https://demo.guru99.com/v4/manager/EditCustomer.php");
		
		//Input and submit customerID
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		waitInSecond(3);
		
		//Verify all updated values
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='state']")).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='pinno']")).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value"), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='emailid']")).getAttribute("value"), email);
		
	   
	}

	@Test
	public void TC_02() {
		//Navigate to URL
		driver.get("https://opensource-demo.orangehrmlive.com/");
		waitInSecond(3);
		
		//Input values for username, password
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		waitInSecond(3);
		
		//Navigate to the AddEmployee page
		driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
		waitInSecond(8);
		
		String firstName = "Trung";
		String lastName = "Nguyen";
		//Input First Name, Last Name and get employeeID
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
		
		waitInSecond(3);
		String employeeID = driver.findElement(By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div")).getAttribute("value");
		
		//Click Submit button
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		waitInSecond(8);
		
		//Verify all input data in the Personal Details page
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div")).getAttribute("value"),employeeID);
	   
		
		
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
