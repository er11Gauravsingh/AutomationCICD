package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
	 super(driver);
	 this.driver=driver;
	 PageFactory.initElements(driver,this); // This will initialize all web element using driver
	}
	
	
	@FindBy(css="#userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement passwordEle;
	@FindBy(id="login")
	WebElement login;
	@FindBy(css="[class*='flyInOut']") // This appears when we put wrong email and password there a message is displayed and this is the locator for that
	WebElement errorMessage;
	public ProductCatalogue loginApplication(String email,String password) 
	{
		waitForWebElementToAppear(userEmail);	
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	

}
