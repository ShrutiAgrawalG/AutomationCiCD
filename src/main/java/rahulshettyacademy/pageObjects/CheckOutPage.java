package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement btncountry;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement btnPlaceOrder;
	
	By result = By.cssSelector(".ta-results");
	
	public void selectCountry(String country) {
		//Select Country Input box
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, country).build().perform();
		//wait
		waitForElementToAppear(result);
		btncountry.click(); //Selecting india
		
		
	}
	public ConfirmationPage submitOrder() {
		
		Actions action = new Actions(driver);
		action.moveToElement(btnPlaceOrder).click().build().perform();
		return new ConfirmationPage(driver);
	}
	    
	  
}
