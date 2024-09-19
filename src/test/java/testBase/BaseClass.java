package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{

	public static WebDriver driver; //why this is static? Becaz we refer same driver instance in ExtentReportManager.java   Here we are creating object of BaseClass. It means it is a common driver for all
	public Logger logger;
	public Properties p;
	

	@BeforeClass(groups= {"Sanity","Regression","Master"}) //Master means all test cases will execute
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws Throwable 
	{
		
		//Loading properties file from src/test/resources
		FileReader file=new FileReader(".//src//test//resources//config.properties"); //FileInputStream file=new FileInputStream(".//src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		

		//Loading log4j2 file from src/test/resources
		logger = LogManager.getLogger(this.getClass()); // log4j2
		// this.getClass()=It will take the test case class.
		// We can use logger variable where we want to generate the log in test case

		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
	
			switch (br.toLowerCase()) 
			{
			case "chrome": driver = new ChromeDriver();	break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser");
			return; //This will totally exit from the execution. Becaz if browser itself invalid, then no need to continue the further stuff/test case execution. So, here we are exiting from entire execution part. return will do this. If we don't put return? Even though browser is invalid. Still it is trying to execute the test. We don't need this. 
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));

	}

	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() 
	{
		driver.close();
	}

	public String randomString() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() 
	{
		String generatedNumber = RandomStringUtils.randomAlphanumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() 
	{
		String alpha = RandomStringUtils.randomAlphabetic(5);
		String numeric = RandomStringUtils.randomAlphanumeric(2);
		return (alpha + numeric);
	}
	
	public String captureScreen(String tname) throws IOException 
	{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath; //It will return the screenshot path/location

	}

	
	
	

}
