package Test;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.BaseClass;
import pages.FlightBookingPage;
public class FlightBookingTest extends BaseClass  {

    @Test
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {

    	FlightBookingPage flightBookingTest=PageFactory.initElements(driver, FlightBookingPage.class);    	
    	flightBookingTest.selectFromStation("Bangalore");
    	flightBookingTest.selectTargetStation("Delhi");
    	flightBookingTest.selectDate("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[4]/td[5]/a");
    	BaseClass.clickUsingJavaScript("SearchBtn");
    	flightBookingTest.verifyFatchedResult();
    }
    
    public void datePicker() {
    	WebElement table=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody"));
    	List<WebElement> listDate=driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr"));
    	for(int i=1; i<=listDate.size();i++) {
    		for(int j=1; j<=7;j++) {
    		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr["+i+"]/td["+j+"]/a")).click();
    	}}
    } 
}
