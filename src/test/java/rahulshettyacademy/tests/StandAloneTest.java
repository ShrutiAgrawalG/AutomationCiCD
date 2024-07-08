package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Chromedriver will be installed with your version in the sysytem
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//Definig variables for products
		String productName = "ZARA COAT 3";
		
		//Global timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		//Invoking the URl
		driver.get("https://rahulshettyacademy.com/client");
		
		//maximise
		driver.manage().window().maximize();
		
		//Create object of Landing Page
		LandingPage landingPage = new LandingPage(driver);
		
		//Adding the details - Email
		driver.findElement(By.id("userEmail")).sendKeys("shrutiagrawal@gmail.com");
		//Password
		driver.findElement(By.id("userPassword")).sendKeys("Shruti@240790");
		//click on Login button
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//to get all products in the list we will be usinmg div input and class = ..mb-3
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		 WebElement prod = products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName))
		.findFirst().orElse(null);
		 
		 prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		 //Explicit wait
		 //id = toast-container
		  
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
		 //ng-animating
		 
		 
		 //Add to cart button
		 driver.findElement(By.cssSelector("[routerlink*= 'cart']")).click();
		 
		 //verify in cart section
		 //.cartSection h3
		  List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		  Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		  Assert.assertTrue(match); //if true it will pass or else it will fail
		  
		  //click on chekout button
		  driver.findElement(By.cssSelector(".totalRow button")).click();  //xpath - //button[text()='Checkout']
		  
		  //Confirmation Page
		  ////input[@placeholder='Select Country']
		  //Css - [class*=group-item] i  for menu items of auto suggested drop down
		  
		  //Select Country Input box
		  WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		  
		 
		  
		   Actions action = new Actions(driver);
		   action.sendKeys(selectCountry, "India").build().perform();
		   
		   //wait
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		   
//		   //Selecting menu item India
//		   List<WebElement> menuItemList= driver.findElements(By.cssSelector(".ta-results span"));
//		   System.out.println(menuItemList.size());
//		   for(WebElement e : menuItemList) {
//			   System.out.println(e.getText());
//			   if(e.getText().equals("India")) {
//				   System.out.println(e.getText());
//				   e.click();
//			   }
//		   }
		   
//		   //.ta-item:nth-of-type(2)  and xpath = (//button[contains(@class,'ta-item')])[2] - selecting India from dd
		   driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		   Thread.sleep(2000);
		  // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		   
		   //Place order
		   //driver.findElement(By.cssSelector(".btnn.action__submit ")).click();
		    WebElement btnPlaceOrder = driver.findElement(By.xpath("//a[text()='Place Order ']"));
		    
		   action.moveToElement(btnPlaceOrder).click().build().perform();
		   
		    //Thread.sleep(2000);
		   //Thank you for your order
		   String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		   System.out.println(confirmMsg);
		   Thread.sleep(2000);
		   Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		   driver.close();
		   
		   
		   
	}

}
