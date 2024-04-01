package pawaracademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pawaracademy.adstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//driver.findElement(By.id("userEmail"))
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
	public ProductCatalogue LoginApplication(String userid, String password)
	{		
		userEmail.sendKeys(userid);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(errormsg);
		return errormsg.getText();
		
	}
	
}
