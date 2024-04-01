package pawaracademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.adstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkout;
	
	public boolean verifyProductDisplay(String productname)
	{
		boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckOutPage goToCheckout()
	{
		checkout.click();
		return new CheckOutPage(driver);
	}

}
