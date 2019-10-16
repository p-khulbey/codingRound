package Test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.CleartripHomePage;

public class SignInCleartripTest extends BaseClass {

	@Test
	public void testWithoutLoginErrorMessage() throws Exception {
		CleartripHomePage cleartripHomePage = PageFactory.initElements(driver, CleartripHomePage.class);
		cleartripHomePage.clickOnYourTripLink();
		cleartripHomePage.clickOnSignInButton();
		BaseClass.switchToIframe();
		cleartripHomePage.ID_submitCredentialsCleartrip();
		BaseClass.verifyErrorMessage("//*[@id=\"errors1\"]/span", "There were errors in your submission");
	}

}