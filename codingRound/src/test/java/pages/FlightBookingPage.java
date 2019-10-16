package pages;

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
		pickADate.sendKeys();
	}

	
}
