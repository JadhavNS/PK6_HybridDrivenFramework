package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends _BasePage {

	//1. Constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//2. Locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtPasswordConfirm;
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") WebElement confirmationMSG;
	
	public void enterFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void enterPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void enterConfirmPassword(String passcon) {
		txtPasswordConfirm.sendKeys(passcon);
	}
	
	public void setPolicy() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		//sol1
		btnContinue.click();
		
		//sol2 
		//btnContinue.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
					
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//Sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Sol6  
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}
	
	public String getConfirmationMsg() {
		try {
			return (confirmationMSG.getText());
		}
		catch(Exception e){
			return (e.getMessage());
		}
		
		
	}
	
	
	
	
}
