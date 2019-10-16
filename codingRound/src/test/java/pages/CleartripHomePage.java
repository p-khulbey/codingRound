package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import common.BaseClass;

public class CleartripHomePage extends BaseClass {

	WebDriver driver;

	public CleartripHomePage(WebDriver driver) {
		this.driver = driver;
	}

	
		@FindBy(how = How.XPATH, using = "//input[@id=\"SignIn\"]")
	WebElement signInBtnCleartrip;
	@FindBy(how = How.XPATH, using = "//span[text()=\"Your trips\"]")
	WebElement YourTripLink;
	@FindBy(how = How.XPATH, using = "//button[text()=\"Sign in\"]")
	WebElement ID_submitCredentialsCleartrip;
	@FindBy(how = How.XPATH, using = "//div[@id=\"errors1\"]")
	WebElement WithoutLoginSignInError;
	

	public void clickOnYourTripLink() {
		YourTripLink.click();
	}

	public void clickOnSignInButton() {
		signInBtnCleartrip.click();
	}

	public void ID_submitCredentialsCleartrip() {
		ID_submitCredentialsCleartrip.click();
	}

	
	
	
}