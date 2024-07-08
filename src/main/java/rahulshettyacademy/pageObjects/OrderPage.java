package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	@FindBy(css = "tr[class = \"ng-star-inserted\"] td:nth-child(3)")
	List<WebElement> productNames; 
	
	@FindBy(css = "h1[class = \"ng-star-inserted\"]")
	WebElement verifyYourOrders;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		
		waitForWebElementToAppear(verifyYourOrders);
		Boolean match = productNames.stream().anyMatch(prodDisplay -> prodDisplay.getText().equalsIgnoreCase(productName));
		System.out.print(match);
		return match;
		
	}

}
