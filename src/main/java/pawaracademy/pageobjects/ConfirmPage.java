package pawaracademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.adstractComponent.AbstractComponent;

public class ConfirmPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement messageEle;
	
	//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String getConfimationMessage()
	{
		return messageEle.getText();
		
	}

}
