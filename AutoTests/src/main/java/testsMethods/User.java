package testsMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import testsData.CommonData;



public class User {
	public static boolean isAlreadyLogIn = false;
	private String userEmail;
	private String userPassword;
	public static WebDriver driver = CommonFunctions.driver;

	// Constructor
	public User(String userEmail, String userPassword) {
		if (userEmail == null) {
			throw new IllegalArgumentException("userEmail = null");
		}
		this.userEmail = userEmail;
		if (userPassword == null) {
			throw new IllegalArgumentException("userPassword = null");
		}
		this.userPassword = userPassword;

	}

	// Sign In method. Can accept email and password as a string
	public static void logIn(String userEmail, String userPassword) {
		// To check if the user has already logged in. If yes then don't execute this function.
		if (!isAlreadyLogIn) {
			// If Not - then login in to your account.
			driver.get(CommonData.signInPageUrl);
			driver.findElement(By.xpath("//*[@id='signinUsername']")).sendKeys(userEmail);
			driver.findElement(By.xpath("//*[@id='signinPassword']")).sendKeys(userPassword);
			driver.findElement(By.xpath("//input[@value='Sign In']")).click();
			// Assert that signing in was successful
			Assert.assertEquals(driver.getCurrentUrl(), CommonData.afterSignInPageUrl);
			Assert.assertEquals(driver.findElement(By.xpath("//span[@class='comp']")).getText(), userEmail.toUpperCase());
			isAlreadyLogIn = true;
		}
	}

	public void logOut() {
		CommonFunctions.driver.findElement(By.xpath("//div[@id='userNavigationLabel']")).click();
		driver.findElement(By.xpath("//input[@value='Log Out']")).click();
		isAlreadyLogIn = false;
	}

	public String getEmail() {
		return userEmail;
	}

	public String getPassword() {
		return userPassword;
	}
}
