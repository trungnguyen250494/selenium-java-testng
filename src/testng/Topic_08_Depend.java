package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Depend {

	@Test
	public void Cart_01_Create_Product() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "Cart_01_Create_Product")
	public void Cart_02_View_Product() {
		
	}
	
	@Test(dependsOnMethods = "Cart_02_View_Product")
	public void Cart_03_Update_Product() {
		
	}
	
	@Test(dependsOnMethods = "Cart_03_Update_Product")
	public void Cart_04_Delete_Product() {
		
	}

}
