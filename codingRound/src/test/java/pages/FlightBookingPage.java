package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Test.BaseClass;

public class FlightBookingPage extends BaseClass {

	WebDriver driver;

	public FlightBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "FromTag") 
	private WebElement sourceStation;
	
	@FindBy(how = How.ID, using = "ToTag") 
	private WebElement destinationStation;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder=\"Pick a date\"]")
	WebElement pickADate;
	
	@FindBy(how = How.XPATH, using = "//input[@value=\"Search flights\"]")
	WebElement searchFlightButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='GlobalNav']/div/div[2]/div/strong")
	WebElement verifyResult;
	
	
	

	public void selectFromStation(String fromStation) {
		sourceStation.clear();
		sourceStation.sendKeys(fromStation);
	}
	
	public void selectTargetStation(String toStation) {
		destinationStation.clear();
		destinationStation.sendKeys(toStation);
	}

	public void selectDate(String date) {
		pickADate.click();
		driver.findElement(By.xpath(date)).click();
	}

	public void searchFlightButton() {
		searchFlightButton.click();
	}

	public void verifyFatchedResult() {
		assertTrue(verifyResult.isDisplayed(), "We are not on searched flight result page");
	}
	
}
