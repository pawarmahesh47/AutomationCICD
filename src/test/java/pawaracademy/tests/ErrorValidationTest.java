package pawaracademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckOutPage;
import pawaracademy.pageobjects.ConfirmPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingpage.LoginApplication("nirvi@gmail.com", "Nirvi@1243");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalague = landingpage.LoginApplication("nirvi@gmail.com", "Nirvi@123");
		
		productCatalague.addProductToCart(productName);
		CartPage cartpage = productCatalague.goToCartPage();

		boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");

		Assert.assertFalse(match);

}
}
