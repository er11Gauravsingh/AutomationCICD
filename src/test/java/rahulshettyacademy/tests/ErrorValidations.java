package rahulshettyacademy.tests;

import java.io.IOException;
import java.net.Authenticator;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.Testcomponents.RetryAnalyser;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	

     @Test(groups={"ErrorHandling"},retryAnalyzer=RetryAnalyser.class)
      public void LoginErrorValidation() throws IOException, InterruptedException 
      {
		String productName="ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingpage.loginApplication("gauravsingh123456rocks@gmail.com", "123456");
		
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
		System.out.println(landingpage.getErrorMessage());
		
	
	}
      

      @Test
      public void ProductErrorValidation() throws IOException, InterruptedException 
      {
		String productName="ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingpage.loginApplication("gauravsingh12345rocks@gmail.com", "123456");
		//List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyingProductDisplay("QWERTY");
		Assert.assertFalse(match);
		
		
	}
}
