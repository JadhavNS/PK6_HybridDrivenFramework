package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistration extends BaseClass {

	@Test(groups={"Regression", "Master"})
	public void Account_Registration() 
	{

		logger.info("TC01 Account Registration is started");
		logger.debug("This is debug log message");

		try 
		{
			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			logger.info("Click on My Account link");

			hp.clickRegister();
			logger.info("Click on Register link");

			AccountRegistrationPage arp = new AccountRegistrationPage(driver);
			
			logger.info("Providing customer information");
			arp.enterFirstName(randomString());
			arp.enterLastName(randomString());
			arp.enterEmail(randomAlphaNumeric() + "@gmail.com");
			arp.enterTelephone(randomNumber());

			String pass = randomAlphaNumeric();
			arp.enterPassword(pass);
			arp.enterConfirmPassword(pass);
			arp.setPolicy();
			arp.clickContinue();
			
			logger.info("Validating expected message");
			
			String confirmMessage = arp.getConfirmationMsg();
			Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
			
			logger.info("Test passed");
		}

		catch (Exception e) 
		{

			logger.error("Test failed : "+e.getMessage());
			Assert.fail("Test failed : "+ e.getMessage());
			
		} 
		finally {
			
			logger.info("TC01 Account Registration is finished");
		}
	}

}
