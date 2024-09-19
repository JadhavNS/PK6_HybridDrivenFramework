package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_LoginDDT(String email, String password, String exp)
	{

		logger.info("**** Starting TC03_LoginDDT *****");
		
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
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		logger.info("clicked on ligin button..");

		
		//My Account page
		MyAccountPage ap=new MyAccountPage(driver);
		boolean targetPage=ap.isMyAccountExists();
		
		/*
		 Data is valid   : login success - test pass - logout
		 				   login failed - test fail
		   
		 Data is invalid :  login success - test fail- logout
		 					login failed - test pass
		  */
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true) 
			{
				ap.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				ap.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
		}
		catch(Exception e)
		{
			Assert.fail("An Exception occured : "+e.getMessage());
		}
		
		logger.info("**** Finished TC03_LoginDDT *****");

		
		
	}
	
	
	
	
	
	
}
