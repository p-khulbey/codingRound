package common;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClassHandler {
	public static WebDriver driver;
	public static String propertyPath ="C:\\Eclipse_Database\\codingRound\\codingRound\\dataElement.properties";
//	public static String propertyPath = System.getProperty("user.dir").toString() + File.separator
		//	+ "dataElement.properties";
	public static Properties CONFIG = null;
	public static String defectsScreenShots = "C:\\Eclipse_Database\\MobileWebMomsPressoQA\\DefectsScreenShots";
	public static Wait wait = null;
	public static WebDriverWait webdriverwait = null;
	public final static int TimeOut = 10;
	public final static int Page_Load_Timeout = 30;
	FileInputStream FIS = null;
	 public static String chromePath =System.getProperty("user.dir").toString() + File.separator + "libs"
	 + File.separator;
	static BaseClassHandler P;
	Action action;
	

	public static BaseClassHandler getInstance() {
		if (P == null)
			P = new BaseClassHandler();
		return P;
	}

	public BaseClassHandler() {
		String os = System.getProperty("os.name").toLowerCase();
		if (isWindows(os)) {
			chromePath = chromePath + "chromedriver.exe";
		} else {
			chromePath = chromePath + "chromedriver";
		}

		File file = new File(propertyPath);
		if (CONFIG == null) {
			try {
				// initialize CONFIG to corresponding env
				CONFIG = new Properties();

				FIS = new FileInputStream(file);
				CONFIG.load(FIS);
				// System.out.println("Config file Loaded successfully.........." + CONFIG);
			} catch (Exception e) {
				System.out.println("Error on intializing properties files");
			}
		}
	}

	public static boolean isWindows(String os) {
		return (os.indexOf("win") >= 0);
	}
	
	public void init() {
		setDriverPath();
	}

	private void setDriverPath() {
    	String os = System.getProperty("os.name").toLowerCase();
    	if(isWindows(os)) {
    		 System.setProperty("webdriver.chrome.driver", chromePath);
    	}else if(isMac(os)) {
//    		chromePath = chromePath + "chromedriver";
    		  System.setProperty("webdriver.chrome.driver", chromePath + "chromedriver");
    	}else {
    		 System.setProperty("webdriver.chrome.driver", chromePath + "chromedriver_linux");
    	}
    	ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");	
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://www.cleartrip.com/");
    	}
    	
    	public static boolean isMac(String os) {
    		return (os.indexOf("win") >= 0);
    	}
    	
    	public void assertTestCase(String path, String expected) {
    		 String actual = driver.findElement(By.id(path)).getText();
    		 //getElementByXpath("").
    	        Assert.assertTrue(actual.contains(expected));
    	}
	

	@SuppressWarnings("unchecked")
	public void genricURLLauncher(String runMode, String browser) {
		String URL = CONFIG.getProperty("AppUrl");
		if (runMode.equalsIgnoreCase("desktop")) {
			if (browser.equalsIgnoreCase("headless")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				System.setProperty("webdriver.chrome.driver", chromePath);
				Map<String, String> mobileEmulation = new HashMap<String, String>();
				// mobileEmulation.put("deviceName", "Nexus 5");
				mobileEmulation.put("deviceName", "Pixel 2");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("headless");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				// chromeOptions.addArguments("window-size=1200x600");
				// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

				driver = new ChromeDriver(chromeOptions);
				driver.get(URL);
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				System.setProperty("webdriver.chrome.driver", chromePath);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
				Map<String, Object> prefs = new HashMap<String, Object>();
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("disable-infobars");
				prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
				chromeOptions.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(chromeOptions);
				driver.get(URL);
				driver.navigate().refresh();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				//System.setProperty("webdriver.ie.driver", iePath);
				driver = new InternetExplorerDriver();
				driver.get(URL);
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				//System.setProperty("webdriver.gecko.driver", FFPath);
				driver = new FirefoxDriver();
				driver.get(URL);
			}

			driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Page_Load_Timeout, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else if (runMode.equalsIgnoreCase("mobileWeb")) {
			if (browser.equalsIgnoreCase("headless")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				System.setProperty("webdriver.chrome.driver", chromePath);
				Map<String, String> mobileEmulation = new HashMap<String, String>();

				// mobileEmulation.put("deviceName", "Nexus 5");
				mobileEmulation.put("deviceName", "Pixel 2");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("headless");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				// chromeOptions.addArguments("window-size=1200x600");
				// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

				driver = new ChromeDriver(chromeOptions);
				driver.get(URL);
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("Launching " + URL + " in " + browser + " on " + runMode);
				System.setProperty("webdriver.chrome.driver", chromePath);
				Map<String, String> mobileEmulation = new HashMap<String, String>();
				// mobileEmulation.put("deviceName", "Nexus 5");
				mobileEmulation.put("deviceName", "Pixel 2");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("disable-infobars");
				// chromeOptions.addArguments("window-size=1200x600");
				// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

				driver = new ChromeDriver(chromeOptions);
				driver.get(URL);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void desktopURLLauncher() {
		String browser = CONFIG.getProperty("DesktopBrowser");
		String URL = CONFIG.getProperty("AppUrl");
		if (browser.equalsIgnoreCase("headless")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on desktop web");
			System.setProperty("webdriver.chrome.driver", chromePath);
			// Map<String, String> mobileEmulation = new HashMap<String, String>();
			// mobileEmulation.put("deviceName", "Nexus 5");
			// mobileEmulation.put("deviceName", "Pixel 2");
			ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--no-sandbox");
			// chromeOptions.addArguments("window-size=1200x600");
			// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

			driver = new ChromeDriver(chromeOptions);
			driver.get(URL);
			driver.manage().deleteAllCookies();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on desktop web");
			System.setProperty("webdriver.chrome.driver", chromePath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
			Map<String, Object> prefs = new HashMap<String, Object>();
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(chromeOptions);
			driver.get(URL);
			driver.navigate().refresh();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on desktop web");
		//	System.setProperty("webdriver.ie.driver", iePath);
			driver = new InternetExplorerDriver();
			driver.get(URL);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on desktop web");
		//	System.setProperty("webdriver.gecko.driver", FFPath);
			driver = new FirefoxDriver();
			driver.get(URL);
		}

		driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		webdriverwait = new WebDriverWait(driver, 50);
		webdriverwait.until(ExpectedConditions.elementToBeClickable(getElement("XPath_PregnancyLink")));
	}

	@SuppressWarnings("unchecked")
	public void mobileWebURLLauncher() {
		String browser = CONFIG.getProperty("MobileWebBrowser");
		String URL = CONFIG.getProperty("AppUrl");

		if (browser.equalsIgnoreCase("headless")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on mobile Web Browser");
			System.setProperty("webdriver.chrome.driver", chromePath);
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			// mobileEmulation.put("deviceName", "Nexus 5");
			mobileEmulation.put("deviceName", "Pixel 2");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--no-sandbox");
			// chromeOptions.addArguments("window-size=1200x600");
			// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

			driver = new ChromeDriver(chromeOptions);
			driver.get(URL);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on mobile Web Browser");
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium Drivers\\Firefox\\geckodriver.exe");
			FirefoxOptions ffoption = new FirefoxOptions();
			ffoption.addArguments("--disable-features=EnableEphemeralFlashPermission");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
//				mobileEmulation.put("deviceName", "Nexus 5");
			mobileEmulation.put("deviceName", "Pixel 2");

			// ffoption.setsetExperimentalOption("mobileEmulation", mobileEmulation);
			ffoption.addArguments("--disable-notifications");
			ffoption.addArguments("disable-infobars");

			// chromeOptions.addArguments("window-size=1200x600");
			// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

			// driver = new Firefo
			driver.get(URL);
			driver.navigate().refresh();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Going to launch " + URL + " in " + browser + " on mobile Web Browser");
			System.setProperty("webdriver.chrome.driver", chromePath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
//				mobileEmulation.put("deviceName", "Nexus 5");
			mobileEmulation.put("deviceName", "Pixel 2");

			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("disable-infobars");

			// chromeOptions.addArguments("window-size=1200x600");
			// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

			driver = new ChromeDriver(chromeOptions);
			driver.get(URL);
			driver.navigate().refresh();
		}

		driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Page_Load_Timeout, TimeUnit.SECONDS);
	}

	@SuppressWarnings("unchecked")
	public void mobileBrowserLaunch() {
		String URL = CONFIG.getProperty("AppUrl");
		System.out.println("Launching " + URL + " Browser--------->");
		System.setProperty("webdriver.chrome.driver", chromePath);
		Map<String, String> mobileEmulation = new HashMap<String, String>();

//	mobileEmulation.put("deviceName", "Nexus 5");
		mobileEmulation.put("deviceName", "Pixel 2");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("headless");
		// chromeOptions.addArguments("window-size=1200x600");
		// chromeOptions.addArguments("--disable-features=EnableEphemeralFlashPermission");

		driver = new ChromeDriver(chromeOptions);
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	public void moveToDefaultIframe() {
		driver.switchTo().defaultContent();
	}

	public void MouseHoverOnElement(String Element) {
		Actions action = new Actions(driver);
		WebElement element = getElement(Element);
		action.moveToElement(element).perform();
	}

	public static WebElement getElement(String path) {
		String value = CONFIG.getProperty(path);
		webdriverwait = new WebDriverWait(driver, 50);
		String[] obj = path.split("_");
		String locatorType = obj[0];

		if (locatorType.equalsIgnoreCase("xpath")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
		} else if (locatorType.equalsIgnoreCase("class")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
		} else if (locatorType.equalsIgnoreCase("name")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
		} else if (locatorType.equalsIgnoreCase("css")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(value)));
		} else if (locatorType.equalsIgnoreCase("link")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(value)));
		} else if (locatorType.equalsIgnoreCase("plink")) {
			return webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(value)));
		} else if (locatorType.equalsIgnoreCase("accessibilityID")) {
		}
		return null;
	}

	public void verifyTitleTextMobileWeb() throws Throwable {
		String FirstScreentitle = getElement("Xpath_TodaysBestBeforeClickFirstBlog").getText();
		ClickElement("Xpath_TodaysBestBeforeClickFirstBlog");
		Thread.sleep(5000);
		String SecondScreentitle = getElement("Xpath_TodaysBestAfterClickFirstBlog").getText();
		System.out.println("First title is : " + FirstScreentitle);
		System.out.println("Second title is : " + SecondScreentitle);
		assertTrue(FirstScreentitle.equalsIgnoreCase(SecondScreentitle));
	}

	public void SwitchToWindow(String window) throws InterruptedException {
		Thread.sleep(5000);
		if (window.equalsIgnoreCase("window1")) {
			Set<String> AllWindowHandles = driver.getWindowHandles();
			String window1 = (String) AllWindowHandles.toArray()[0];
			driver.switchTo().window(window1);
		} else if (window.equalsIgnoreCase("window2")) {
			Set<String> AllWindowHandles = driver.getWindowHandles();
			// System.out.print("window1 handle code = "+AllWindowHandles.toArray()[0]);
			String window2 = (String) AllWindowHandles.toArray()[1];
			System.out.print("\nwindow2 handle code = " + AllWindowHandles.toArray()[1]);
			driver.switchTo().window(window2);
		}
	}

	public void getscreenshot(String fileName) throws Exception {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Eclipse_Database\\MobileWebMomsPressoQA\\DefectsScreenShots\\55.png");
		//FileUtils.copyFile(scr, dest);
		Thread.sleep(3000);
	}

	public void assertTitleText(String beforeClickPath, String afterClickPath)
			throws IOException, InterruptedException {
		try {
			Thread.sleep(4000);
			String expectedText = getElement(beforeClickPath).getText();
			System.out.println("Expected text is : " + expectedText);
			ClickElement(beforeClickPath);
			Thread.sleep(4000);
			String actualText = getElement(afterClickPath).getText();
			System.out.println("Actual text is : " + actualText);
			assertEquals(actualText, expectedText);
		} catch (AssertionError e) {
			// screenShotCatchBlock();
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formatDateTime = now.format(formatter);
			String str = formatDateTime.toString();
			System.out.println("Our date is  : " + str);

			File file = new File("C:/Eclipse_Database/MobileWebMomsPressoQA/DefectsScreenShots/" + str);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
			//FileUtils.copyFile(SrcFile, new File(file + "/" + dateFormat.format(date) + ".png"));
			assertFalse(true);
		}
	}

	public void screenShotCatchBlock() throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatDateTime = now.format(formatter);
		String str = formatDateTime.toString();
		System.out.println("Our date is  : " + str);

		File file = new File("C:/Eclipse_Database/MobileWebMomsPressoQA/DefectsScreenShots/" + str);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
		//FileUtils.copyFile(SrcFile, new File(file + "/" + dateFormat.format(date) + ".png"));
		assertFalse(true);
	}

	public void verifyTitleText(String expectedTitle) throws Throwable {
		// assertText();

		// String exp_title = "Parenting Tips, Videos, Pregnancy Advice, Events for Kids
		// and Moms in India | Momspresso";
		String actual_title = driver.getTitle();
		// assertEquals(expectedTitle, actual_title, "Homepage title does not match");
		Assert.assertEquals(expectedTitle, actual_title);

	}

	public void clickUsingJavaScript(String path) {

		WebElement element = getElement(path);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void assertText(String path, String Expected) {
		webdriverwait = new WebDriverWait(driver, 70);
		webdriverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		String buttonText = getElement(path).getText();
		System.out.println("Actual text is: " + buttonText);
		Assert.assertEquals(Expected, buttonText);
	}

	public void ClickElement(String path) {
		@SuppressWarnings("unused")
		String Value = CONFIG.getProperty(path);
		String[] obj = path.split("_");
		String locatorType = obj[0];

		if (locatorType.equalsIgnoreCase("xpath")) {
			// driver.findElement(By.xpath(Value)).click();
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("class")) {
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("name")) {
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("css")) {
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("link")) {
			getElement(path).click();
		} else if (locatorType.equalsIgnoreCase("plink")) {
			getElement(path).click();
		}
	}

	public void inputData(String data, String path) {
		String[] obj = path.split("_");
		String locatorType = obj[0];
		// System.out.println("Data is :" +data);
		// System.out.println("Path is : "+path);
		if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(CONFIG.getProperty(path))).clear();
			driver.findElement(By.xpath(CONFIG.getProperty(path))).sendKeys(data);
		} else if (locatorType.equalsIgnoreCase("class")) {
			driver.findElement(By.className(CONFIG.getProperty(path))).clear();
			driver.findElement(By.className(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		} else if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(CONFIG.getProperty(path))).clear();
			driver.findElement(By.id(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		} else if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(CONFIG.getProperty(path))).clear();
			driver.findElement(By.name(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		} else if (locatorType.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(CONFIG.getProperty(path))).clear();
			driver.findElement(By.cssSelector(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		} else if (locatorType.equalsIgnoreCase("link")) {
			driver.findElement(By.linkText(CONFIG.getProperty(path))).clear();
			driver.findElement(By.linkText(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		} else if (locatorType.equalsIgnoreCase("plink")) {
			driver.findElement(By.partialLinkText(CONFIG.getProperty(path))).clear();
			driver.findElement(By.partialLinkText(CONFIG.getProperty(path))).sendKeys(CONFIG.getProperty(data));
		}
	}

	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}
	}

	public void ScrollVerticallyDown(double scrollPoint) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(0, " + scrollPoint + ")", "");
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
	}

	public void ScrollVerticallyDown250() throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(0, 500)", "");
//		Thread.sleep(1000);
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
	}

	public void ScrollHorizontllyRight() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('swiper-container videos-swiper swiper-init').scrollLeft += 250", "");
	}

	

}
