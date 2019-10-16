package Test;
//import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
	 public static String chromePath = "C:\\Eclipse_Database\\codingRound\\codingRound\\libs\\chromedriver.exe";
    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }
    
    private void setDriverPath() {
    	String os = System.getProperty("os.name").toLowerCase();
    	if(isWindows(os)) {
    		 System.setProperty("webdriver.chrome.driver", chromePath);
    	}else if(isMac(os)) {
    		
    	}else {
    		
    	}}
    	
    	public static boolean isWindows(String os) {
    		return (os.indexOf("win") >= 0);
    	}
    	
    	public static boolean isMac(String os) {
    		return (os.indexOf("win") >= 0);
    	}

//    private void setDriverPath() {
//        if (PlatformUtil.isMac()) {
//            System.setProperty("webdriver.chrome.driver", "chromedriver");
//        }
//        if (PlatformUtil.isWindows()) {
//            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        }
//        if (PlatformUtil.isLinux()) {
//            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
//        }
//    }

}
