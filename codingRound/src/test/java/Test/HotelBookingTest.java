package Test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.HotelBookingPage;

public class HotelBookingTest extends BaseClass  {
	

    @Test
    public void shouldBeAbleToSearchForHotels() {
    	HotelBookingPage hotelBookingPage = PageFactory.initElements(driver, HotelBookingPage.class);
    	hotelBookingPage.clickOnHotelLink();
    	hotelBookingPage.clickOnLocalityTextBox("Indiranagar, Bangalore");
    	hotelBookingPage.selectDate("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[4]/td[4]/a", "//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[4]/td[6]/a");
    	BaseClass.selectTravellers("travellersOnhome", "1 room, 2 adults");
    	hotelBookingPage.searchHotelsButton();

    }
    
  
}
