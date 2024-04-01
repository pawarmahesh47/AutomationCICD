package pawaracademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pawaracademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pawaracademy\\resources\\GlobalData.properties");
		pro.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser"); 
		//String browserName = pro.getProperty("browser");
		if (browserName.contains("Chrome"))
		{
			ChromeOptions option = new ChromeOptions();   //Test in headless mode
			
			if(browserName.contains("headless"))
			{
				option.addArguments("headless");
			}	
			
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1400, 900));       //run in maximize mode
			
		}
		
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			driver = new EdgeDriver();
		}
		
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		//driver = initializeDriver();
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports" + testCaseName + ".png";
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		
		return data;
		
		}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
