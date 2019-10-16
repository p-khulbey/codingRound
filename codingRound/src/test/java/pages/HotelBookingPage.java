package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Test.BaseClass;

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
		//*[@id="ui-datepicker-div"]/div[1]/table/tbody/tr[4]/td[4]/a
		//*[@id="ui-datepicker-div"]/div[1]/table/tbody/tr[4]/td[6]/a
	}

	public void searchHotelsButton() {
		searchButton.click();
	}

}
