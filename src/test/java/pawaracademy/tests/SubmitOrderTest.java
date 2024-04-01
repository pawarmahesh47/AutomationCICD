package pawaracademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.TestComponents.Retry;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckOutPage;
import pawaracademy.pageobjects.ConfirmPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.OrderPage;
import pawaracademy.pageobjects.ProductCatalogue;
import scala.collection.immutable.HashMap;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"}, retryAnalyzer=Retry.class)
	public void submitOrder(java.util.HashMap<String,String> input) throws IOException, InterruptedException {

		
		ProductCatalogue productCatalague = landingpage.LoginApplication(input.get("email"), input.get("password"));
		
		productCatalague.addProductToCart(input.get("product"));
		CartPage cartpage = productCatalague.goToCartPage();

		boolean match = cartpage.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		CheckOutPage checkoutPage = cartpage.goToCheckout();

		checkoutPage.seclectCountry("India");
		ConfirmPage confirmpage = checkoutPage.sudmitOrder();

		String confirmmsg = confirmpage.getConfimationMessage();

		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalague = landingpage.LoginApplication("nirvi@gmail.com", "Nirvi@123");
		OrderPage orderpage = productCatalague.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));

	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<java.util.HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//pawaracademy//data//DataReader.json");
		///return new Object[][] {{"nirvi@gmail.com", "Nirvi@123", "ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
