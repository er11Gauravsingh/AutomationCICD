package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> ProductNames;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	public Boolean VerifyingOrderDisplay(String productName) 
	{
		boolean match=ProductNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckOut() 
	{
		checkOutEle.click();
		return new CheckoutPage(driver);
	}

}
