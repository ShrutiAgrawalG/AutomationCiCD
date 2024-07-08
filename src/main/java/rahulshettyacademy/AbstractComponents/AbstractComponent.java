package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;


public class AbstractComponent {
	
	
	//Add to Cart buton
	@FindBy(css = "[routerlink*= 'cart']")
	WebElement btnAddtoCart;
	
	//Orders button
	@FindBy(css = "[routerlink*= 'myorders']")
	WebElement btnOrders;
	//.btn.btn-custom[routerlink='/dashboard/myorders']
	
	
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) { //Constructor
		
		this.driver = driver;
		PageFactory.initElements(driver, this.driver);
	}

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToClick(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	public void waitForElementToDisAppear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCartPage() {
		//Add to cart button
		 btnAddtoCart.click(); //this will land on Cart page
		 CartPage cartPage =  new CartPage(driver);
		 return cartPage;
	}
	public OrderPage goToOrdersPage() {
		//Add to Orders button
		 btnOrders.click(); //this will land on Cart page
		 System.out.print("Hi created");
		 OrderPage orderPage =  new OrderPage(driver);
		 return orderPage;
	}
}
