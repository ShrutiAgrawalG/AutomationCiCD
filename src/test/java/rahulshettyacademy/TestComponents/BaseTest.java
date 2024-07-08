package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis); //it accepts inputstrema as parameter
		
		//to read system level properties i.e. from Maven
		//JavaTernry operator
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		System.out.println(browserName);
		
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			//Creating options class to handle the operations with chrome browser
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver();//added after getting error on 8th July
			//Chromedriver will be installed with your version in the sysytem
			WebDriverManager.chromedriver().setup();
			options.addArguments("headless");
			if(browserName.contains("headless")) {
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));//full screen mode
			}
			
			
		}else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("msedge")) {
			
		}
		
		//Global timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximise
		driver.manage().window().maximize();
		return driver;
		
		
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException {
		
		//Read Json to String
		//String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"),(String)null);
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		//
		
		//String to HashMap jacksonDataBind
		
		ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		 
		 return data;
		 //data will have a list<map,map>
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		//Create object of Landing Page
		landingPage = new LandingPage(driver);
		//Calling methods
		//URl
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" +testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" +testCaseName+".png";
	}
	

}
