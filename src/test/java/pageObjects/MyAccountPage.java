package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends _BasePage {
	
	//1. Constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//2. Locators
	@FindBy(xpath="//h2[text()='My Account']") WebElement msgHeading; // MyAccount Page heading
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement lnkLogout;
	
	
	//3. Action methods
	
	public boolean isMyAccountExists() // MyAccount Page heading display status
	{
		try
		{
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return(false); 
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	

}
