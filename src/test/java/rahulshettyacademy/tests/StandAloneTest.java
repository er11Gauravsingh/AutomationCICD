package rahulshettyacademy.tests;

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

import rahulshettyacademy.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName="ADIDAS ORIGINAL";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("gauravsingh12345rocks@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(7));
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
	List<WebElement> country=driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted"));
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertEquals("THANKYOU FOR THE ORDER.", message);
	}
}