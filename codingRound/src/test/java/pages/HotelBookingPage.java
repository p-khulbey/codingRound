package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.BaseClass;

public class HotelBookingPage extends BaseClass  {
	
	
	WebDriver driver;

	public HotelBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton") 
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(id = "CheckInDate")
    private WebElement checkInDate;
   

	public void clickOnHotelLink() {
		hotelLink.click();
	}

	public void clickOnLocalityTextBox(String hotalName) {
		localityTextBox.clear();
		localityTextBox.sendKeys(hotalName);
	}
	
	public void selectDate(String startDate, String endDate) {
		checkInDate.click();
		driver.findElement(By.xpath(startDate)).click();
		driver.findElement(By.xpath(endDate)).click();
	}

	public void searchHotelsButton() {
		searchButton.click();
	}

}
