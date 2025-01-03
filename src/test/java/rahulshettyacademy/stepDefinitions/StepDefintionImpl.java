package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class StepDefintionImpl extends BaseTest
{
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue ;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		landingpage =launchApplication(); // This method is returning landing page Objects.
	}
	
	 @Given ("^Logged in with username(.+)and password(.+)$")  // (.+)  we use it that it will match with any type of string and make it generic and since we are using regular expression we have to start with ^ and end with $
	 public void logged_in_with_username_and_password(String username, String password) 
	 {

		 productCatalogue = landingpage.loginApplication(username,password);
	 }
	@When("^I add(.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@And("^CheckOut(.+) and submit the order$")
	public void checkout_and_submit_order(String productName) throws InterruptedException 
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyingProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");
		 confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} is displayed on Confirmation Page")
	public void  message_displayed_on_Confirmation_Page(String string) 
	{
		String confirmaMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmaMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String arg1)throws Throwable 
	{
		Assert.assertEquals( arg1,landingpage.getErrorMessage());
		driver.close();
	}
	
}
