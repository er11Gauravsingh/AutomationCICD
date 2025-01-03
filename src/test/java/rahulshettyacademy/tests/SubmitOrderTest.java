package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	public String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "Purchase Order")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyingProductDisplay(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmaMessage = confirmationPage.getConfirmationMessage();

		Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmaMessage);

	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingpage.loginApplication("gauravsingh12345rocks@gmail.com", "123456");

		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyingOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		// List<HashMap<String,String>> data=
		// getJsonToMap(System.getProperty("user.dir")+"src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		List<HashMap<String, String>> data = getJsonToMap(
				"D:\\Workspace\\eclipse\\Eclipse_Workspace\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	/*
	 * HashMap<String,String> hs= new HashMap<String,String>(); hs.put("email",
	 * "gauravsingh12345rocks@gmail.com"); hs.put("password", "123456");
	 * hs.put("productName","ADIDAS ORIGINAL" );
	 * 
	 * HashMap<String,String> hs1= new HashMap<String,String>(); hs1.put("email",
	 * "gauravsingh123rocks@gmail.com"); hs1.put("password", "gAURAV@123");
	 * hs1.put("productName","ADIDAS ORIGINAL" );
	 */
}