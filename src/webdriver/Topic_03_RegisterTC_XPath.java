package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_RegisterTC_XPath {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify all error messages
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Input invalid email value
		driver.findElement(By.id("txtFirstname")).sendKeys("Test");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@678");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@678");
		driver.findElement(By.id("txtPassword")).sendKeys("123@456@678");
		driver.findElement(By.id("txtCPassword")).sendKeys("123@456@678");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
	}

	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Input incorrect email (valid but wrong confirmation email) value
		driver.findElement(By.id("txtFirstname")).sendKeys("Test");
		driver.findElement(By.id("txtEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tester@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("123@456@678");
		driver.findElement(By.id("txtCPassword")).sendKeys("123@456@678");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Input invalid password value
		driver.findElement(By.id("txtFirstname")).sendKeys("Test");
		driver.findElement(By.id("txtEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
				
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Incorrect_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Input incorrect password (valid but wrong confirmation password) value
		driver.findElement(By.id("txtFirstname")).sendKeys("Test");
		driver.findElement(By.id("txtEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
				
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Case 1: Input invalid phone number - total numbers is 9
		driver.findElement(By.id("txtFirstname")).sendKeys("Test");
		driver.findElement(By.id("txtEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tester@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("098765432");
				
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		// Case 2: Input invalid phone number - total numbers is 12
		driver.findElement(By.id("txtPhone")).sendKeys("098765432112");
						
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
						
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số."); 
		
		// Case 3: Input invalid first number
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("98765432112");
								
		//Click 'Đăng Ký' button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
								
		//Validate the error message
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08"); 
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
