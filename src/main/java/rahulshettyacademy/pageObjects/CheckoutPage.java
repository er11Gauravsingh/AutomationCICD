package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
    WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submit;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	By results=By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName) throws InterruptedException 
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		Thread.sleep(3000);
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     
	     js.executeScript("window.scrollBy(0, 1500)");
	     Thread.sleep(3000);
	     
		
	}
	public ConfirmationPage submitOrder() 
	{
		
		submit.click();
		return new ConfirmationPage(driver);
	}
}
