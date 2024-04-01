package pawaracademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.adstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkout;
	
	public boolean verifyOrderDisplay(String productname)
	{
		boolean match= orderProducts.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productname));
		return match;
	}

}
