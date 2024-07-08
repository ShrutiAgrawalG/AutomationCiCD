package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	//Initializing code has to be written in Constructor
	public ProductCatalogue(WebDriver driver) {
		
		super(driver); //this will send driver from child class to parent class
		this.driver = driver; //this.driver is current class driver and driver is from parent class
		PageFactory.initElements(driver, this); //initialisation of PageFactory elements will be triggered by this initelements nd this will
		//called as soon as the constructor is called 
	}
	
	
	//Pagefactory
	//List of Products
	@FindBy(css = ".mb-3") ////to get all products in the list we will be usinmg div input and class = ..mb-3
	List<WebElement> products ; //it is findElements
	
	//ProductName
	@FindBy(tagName = "b")
	WebElement productList;
	
	//Animating roound
	@FindBy(css = "ng-animating")
	WebElement spinner;

	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart  = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	 
	
	//Getting the product List
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	public WebElement getproductName(String productName) {

		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName))
		.findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName) {
		
		 WebElement prod = getproductName(productName);
		 prod.findElement(addToCart).click();
		 waitForElementToAppear(toastMessage);
		 waitForElementToDisAppear(spinner);
		 
		 
	}
	
	
	
}