//import com.sun.javafx.PlatformUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	public static WebDriver driver;
//    public static String chromePath = "C:\\Eclipse_Database\\codingRound\\codingRound\\libs\\chromedriver.exe";
   // public static String chromePath = "/codingRound/libs/chromedriver.exe";
    public static String chromePath =System.getProperty("user.dir").toString() + File.separator + "libs"
    		 + File.separator;
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    private void setDriverPath() {
    	String os = System.getProperty("os.name").toLowerCase();
    	if(isWindows(os)) {
    		 System.setProperty("webdriver.chrome.driver", chromePath + "chromedriver.exe");
        	 
	 
    	}else if(isMac(os)) {
//    		chromePath = chromePath + "chromedriver";
    		  System.setProperty("webdriver.chrome.driver", chromePath + "chromedriver");
    	}else {
    		 System.setProperty("webdriver.chrome.driver", chromePath + "chromedriver_linux");
    		//chromePath = chromePath + "chromedriver";
    	}
    	ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");	
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			driver = new ChromeDriver(chromeOptions);
    	}
    	
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
