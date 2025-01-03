package rahulshettyacademy.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException 
	{
		
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		// This above step is used to parse data from properties class and convert it into inputstreamstream and in properties class we define data in form of key and value pair in properties file
		String browserName=	System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");
		// prop.getProperty("browser");
		
		
		if(browserName.contains("chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
		if(browserName.contains("headless")) 
		{
			options.addArguments("headless");
		}
		driver= new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver= new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 driver= new EdgeDriver();
			

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String,String>> getJsonToMap(String filePath) throws IOException
	{
		
			
			//Read Json to String
			String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		   // String to HashMap for this we use JackSon Databind which is used to convert String to HashMap. // UTF 8 is the coding format
			ObjectMapper mapper= new ObjectMapper();
			List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() 
			{});
			return data;
		
	}
	 public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
     {
   	 File ts= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	 File des=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
   	 FileUtils.copyFile(ts, des);
   	 return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
     }
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver=initializeDriver();

		landingpage= new LandingPage(driver);
		landingpage.goTo();
	return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
		driver.close();
		
		
	}

}
