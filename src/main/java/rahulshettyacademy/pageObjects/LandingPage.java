package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	//Initializing code has to be written in Constructor
	public LandingPage(WebDriver driver) {
		
		super(driver); //this will send driver from child class to parent class
		this.driver = driver; //this.driver is current class driver and driver is from parent class
		PageFactory.initElements(driver,this); //initialisation of PageFactory elements will be triggered by this initelements nd this will
		//called as soon as the constructor is called 
	}
	
	//Pagefactory
	
	//Email
	@FindBy(id ="userEmail")
	WebElement userEmail;
	//Password
	@FindBy(id ="userPassword")
	WebElement passwordEle;
	//Login button
	@FindBy(id = "login")
	WebElement Submit;
	
	//Login button
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
		

	//Create Action mehod to perform Login functionality
	public ProductCatalogue loginApplication(String email,String password) {
		//Entering Email
		//userEmail.sendKeys(email);
		driver.findElement(By.id("userEmail")).sendKeys(email);
		passwordEle.sendKeys(password);
		Submit.click();
		//ProductsPage - Landing on Product Page
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	public void goTo() {
		//Enetring Url
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	public String getErrorMessage() {
		
		//waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}