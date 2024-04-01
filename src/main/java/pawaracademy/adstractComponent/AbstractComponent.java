package pawaracademy.adstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pawaracademy.pageobjects.CartPage;
import pawaracademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cartPage;
	
	@FindBy (css= "[routerlink*='myorders']")
	WebElement orderpage;

	public void waitForElementToAppear(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		Thread.sleep(9000);
		cartPage.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderpage.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
		
	}
	
	
}
