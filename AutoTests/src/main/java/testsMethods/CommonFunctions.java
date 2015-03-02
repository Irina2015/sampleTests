package testsMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import testsData.CommonData;

public class CommonFunctions {

	public boolean BrowseralreadyLoaded = false;
	public static WebDriver driver = loadWebBrowser(CommonData.browser);
	public static WebDriver ExistingChromeBrowser;
	public static WebDriver ExistingMozillaBrowser;
	public static WebDriver ExistingIEBrowser;

	// public static String browser = CommonData.browser;

	public static WebDriver loadWebBrowser(String browser) {
		// Check If any previous WebDriver browser instance is exist.
		// If yes then run new test in that existing WebDriver browser instance.
		if (browser.equalsIgnoreCase("Mozilla") && ExistingMozillaBrowser != null) {
			driver = ExistingMozillaBrowser;
			return driver;
		} else if (browser.equalsIgnoreCase("Chrome") && ExistingChromeBrowser != null) {
			driver = ExistingChromeBrowser;
			return driver;
		} else if (browser.equalsIgnoreCase("IE") && ExistingIEBrowser != null) {
			driver = ExistingIEBrowser;
			return driver;
		}
		// If there are no any previous WebDriver browser instance:

		if (browser.equalsIgnoreCase("Mozilla")) {
			// To Load FireFox driver Instance.
			driver = new FirefoxDriver();
			ExistingMozillaBrowser = driver;
		} else if (browser.equalsIgnoreCase("Chrome")) {
			// To Load Chrome driver Instance.
			System.setProperty("webdriver.chrome.driver", CommonData.pathToChromeDriver);
			driver = new ChromeDriver();
			ExistingChromeBrowser = driver;
		} else if (browser.equalsIgnoreCase("IE")) {
			// To Load IE driver Instance.
			System.setProperty("webdriver.ie.driver", CommonData.pathToIEDriver);
			driver = new InternetExplorerDriver();
			ExistingIEBrowser = driver;
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static void closeWebBrowser() {
		// I don't want my cookies to be deleted now, so uncomment the following row if you need this function
		// driver.manage().deleteAllCookies();
		driver.close();
		// null browser Instance when close.
		ExistingChromeBrowser = null;
		ExistingMozillaBrowser = null;
		ExistingIEBrowser = null;
	}

	public static void takeScreenshot(int screenNumber) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(CommonData.pathToSaveScreenshots + screenNumber + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Screenshot is captured and stored in your D: Drive");
	}

	public static void acceptAlert() {
		// To locate alert.
		Alert A1 = driver.switchTo().alert();
		// Click Ok on alert pop-up.
		A1.accept();
		System.out.println("Alert is accepted");
	}

}