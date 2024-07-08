package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	//WebElement
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	 public Boolean verifyProductDisplay(String productName) {
		 
		//verify in cart section
		  Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		  return match;
	  }
	 public CheckOutPage goToCheckOut() {
		 checkoutEle.click();
		 CheckOutPage checkoutPage =  new CheckOutPage(driver);
		 return checkoutPage;
	 }

}
