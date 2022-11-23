package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Manage_Priority {
	
	@Test(enabled = false, description ="Test ID")
	public void EndUser_01_Create_Product() {
		
	}
	
	@Test(description = "Test ID - View Product")
	public void EndUser_02_View_Product() {
		Assert.assertTrue(false);
		
	}
	
	@Test(description = "Test ID - Update Product")
	public void EndUser_03_Update_Product() {
		
	}
	
	@Test
	public void EndUser_04_Delete_Product() {
		
	}

}
