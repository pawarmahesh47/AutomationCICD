package pawaracademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pawaracademy.adstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".form-group input")
	WebElement selectCountryele;
	
	@FindBy(css=".ta-results span")
	List<WebElement> listedCountries;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	public void seclectCountry(String country)
	{
		selectCountryele.sendKeys(country);
		waitForElementToAppear(By.cssSelector(".form-group section"));
		listedCountries.stream().filter(country1 -> country1.getText().equalsIgnoreCase("india")).findFirst().orElse(null).click();
		
	}
	
	public ConfirmPage sudmitOrder()
	{
		submit.click();
		return new ConfirmPage(driver);
	}
}
