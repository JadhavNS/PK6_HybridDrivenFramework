package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void verify_Login()
	{
		
		logger.info("**** Starting TC02_LoginTest  ****");
		logger.debug("capturing application debug logs....");

		
		try
		{
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link on the home page..");
		hp.clickLogin();
		logger.info("clicked on login link under myaccount..");

		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		logger.info("Entering valid email and password..");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		logger.info("clicked on ligin button..");

		
		//My Account page
		MyAccountPage ap=new MyAccountPage(driver);
		boolean targetPage=ap.isMyAccountExists();
		
		Assert.assertEquals(targetPage, true, "Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC02_LoginTest  ****");

	}
}
