package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public static WebDriver driver = null;

	@BeforeSuite
	public void initialize() throws IOException {

//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\java\\drivers\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir")+"\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "D:\\Chrome\\chromedriver_win32\\chromedriver.exe");

		// To open facebook
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("disable-infobars");
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}

	public static void switchToIframe() {
		driver.switchTo().frame("modal_window");
	}

	public static void switchToDefaultIframe() {
		driver.switchTo().defaultContent();
	}
	
	

	public static void verifyErrorMessage(String path, String expected) {
		String actual = driver.findElement(By.xpath(path)).getText();
		System.out.println("Actual error is  : " + actual);
		Assert.assertTrue(actual.contains(expected));
	}

	public static void selectTravellers(String element, String value) {
		WebElement travellersPoint = driver.findElement(By.id(element));
		Select oSelect = new Select(travellersPoint);
		oSelect.deselectByValue(value);

	}
	
	public static void clickUsingJavaScript(String path) {

		WebElement element = driver.findElement(By.id(path));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	@AfterSuite
	public void TeardownTest() {
		BaseClass.driver.quit();
	}
}
