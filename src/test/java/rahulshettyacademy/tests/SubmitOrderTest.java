package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	//Definig variables for products
	String productName = "ZARA COAT 3";
	//Country to be seleted
	String country = "India";
		@Test(dataProvider = "getData",groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
			
			
			//ProductsPage
			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("productName"));
			//CartPage
			CartPage cartPage = productCatalogue.goToCartPage(); //Add to Cart 
			
			Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
			Assert.assertTrue(match); //if true it will pass or else it will fail  
			//Click on Check out
			CheckOutPage checkoutPage = cartPage.goToCheckOut();
			checkoutPage.selectCountry(input.get("Country")); //Selectiing Country
			
			//Submit Order - //Confirmation Page
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
			String confirmMessage = confirmationPage.getConfirmationMessage();//Thank you for your order
			Thread.sleep(2000);
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
			   
		} 
		
		//verify if zara coat is displayed on Orders page
		@Test(dependsOnMethods = {"submitOrder"})
		public void OrderHistoryTest() {
			ProductCatalogue productCatalogue = landingPage.loginApplication("shrutiagrawal@gmail.com", "Shruti@240790");
			//Click on Orders button
			OrderPage orderPage =  productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		
		@DataProvider
		public Object getData() throws IOException {
			
			  List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)}, {data.get(1)}};//return new Object[][] {{}, {}, {}}; if it is 3 dimensional array
		}
		
		//Using dataProvider
//		@Test(dataProvider = "getData",groups = {"Purchase"})
//		public void submitOrder(String email,String password,String productName,String Country) throws IOException, InterruptedException {
//			
//			
//			//ProductsPage
//			ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
//			List<WebElement> products = productCatalogue.getProductList();
//			productCatalogue.addProductToCart(productName);
//			//CartPage
//			CartPage cartPage = productCatalogue.goToCartPage(); //Add to Cart 
//			
//			Boolean match = cartPage.verifyProductDisplay(productName);
//			Assert.assertTrue(match); //if true it will pass or else it will fail  
//			//Click on Check out
//			CheckOutPage checkoutPage = cartPage.goToCheckOut();
//			checkoutPage.selectCountry(Country); //Selectiing Country
//			
//			//Submit Order - //Confirmation Page
//			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
//			String confirmMessage = confirmationPage.getConfirmationMessage();//Thank you for your order
//			Thread.sleep(2000);
//			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
//			   
//		} 
//		 @DataProvider
//		  public Object[][] getData()
//		  {
//		    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//		    
//		  }
		
//		@DataProvider
//		public Object getData() {
//			
//			//Create HashMap
//			HashMap< String, String> map = new HashMap<String, String>();
//			map.put("email", "shrutiagrawal@gmail.com");
//			map.put("password", "Shruti@240790");
//			map.put("productName", "ZARA COAT 3");
//			map.put("Country", "India");
//			
//			//Create HashMap
//			HashMap< String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "shrutiagrawal2@gmail.com");
//			map1.put("password", "Abhishek@240790");
//			map1.put("productName", "ADIDAS ORIGINAL");
//			map1.put("Country", "India");
//			
//			
//			return new Object[][] {{map}, {map1}};//return new Object[][] {{}, {}, {}}; if it is 3 dimensional array
//		}
////	

}
