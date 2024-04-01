package pawaracademy.stepDefinations;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pawaracademy.TestComponents.BaseTest;
import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.CheckOutPage;
import pawaracademy.pageobjects.ConfirmPage;
import pawaracademy.pageobjects.LandingPage;
import pawaracademy.pageobjects.ProductCatalogue;

public class StepDefinationImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productCatalague;
	public ConfirmPage confirmpage;

	@Given("I landed on home page of E-commerce webpage")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalague = landingpage.LoginApplication(username, password);
	}

	@When("^add product (.+) to cart$")
	public void add_product_to_cart(String product) throws InterruptedException {
		productCatalague.addProductToCart(product);
	}

	@When("^checkout product (.+) and submit order$")
	public void checkout_product_and_submit_order(String product) throws InterruptedException {
		CartPage cartpage = productCatalague.goToCartPage();

		boolean match = cartpage.verifyProductDisplay(product);

		Assert.assertTrue(match);

		CheckOutPage checkoutPage = cartpage.goToCheckout();

		checkoutPage.seclectCountry("India");
		confirmpage = checkoutPage.sudmitOrder();
	}

	@Then("I verify the confirmation message {string}")
	public void i_verify_confirmation_message(String string) {
		String confirmmsg = confirmpage.getConfimationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
}
