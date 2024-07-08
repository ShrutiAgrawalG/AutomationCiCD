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
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry1;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

		@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry1.class)
		public void LoginErrorValidation() throws IOException, InterruptedException {
			landingPage.loginApplication("shrutiagrawal1@gmail.com", "Shruti@240790");
			Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());
		} 
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException {
			
			String productName = "ZARA COAT 3";
			String country = "India";
			
			ProductCatalogue productCatalogue = landingPage.loginApplication("shrutiagrawal2@gmail.com", "Abhishek@240790");
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage(); //Add to Cart 
			Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match); //if true it will pass or else it will fail  
			
		}   
	

}
