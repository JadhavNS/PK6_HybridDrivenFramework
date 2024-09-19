package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class _BasePage {
	
	WebDriver driver;
	
	public _BasePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
