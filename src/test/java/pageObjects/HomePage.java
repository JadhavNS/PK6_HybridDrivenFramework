package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends _BasePage {

	// 1. Constructor
	public HomePage(WebDriver driver) {
		super(driver);

	}

	// 2. Locators
	@FindBy(xpath = "//span[@class='caret']") WebElement lnkMyAccount;
	@FindBy(xpath = "//a[text()='Register']") WebElement lnkRegister;
	@FindBy(xpath = "//a[text()='Login']") WebElement lnkLogin;

	// 3. Actions methods
	public void clickMyAccount() {
		lnkMyAccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}

	public void clickLogin()
	{
		lnkLogin.click();
	}
	
}
