package pawaracademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import pawaracademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		
		String productName= "ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();		

		driver.findElement(By.id("userEmail")).sendKeys("nirvi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nirvi@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		Thread.sleep(9000);
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results span"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group section")));
		
		countries.stream().filter(country -> country.getText().equalsIgnoreCase("india")).findFirst().orElse(null).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
