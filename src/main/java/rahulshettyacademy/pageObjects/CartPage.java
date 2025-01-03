package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	public Boolean VerifyingProductDisplay(String productName) 
	{
		boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckOut() 
	{
		checkOutEle.click();
		return new CheckoutPage(driver);
	}

}
