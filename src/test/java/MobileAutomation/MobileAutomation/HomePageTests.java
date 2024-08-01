package MobileAutomation.MobileAutomation;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class HomePageTests {

	private AndroidDriver androidDriver;
	WebDriverWait wait;

	By userNameField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Username\")");
	By passwordField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")");
	By loginButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"test-LOGIN\")");
	By pageHeading = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)");

	@BeforeTest
	public void setup() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("01surbhi");
		options.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		options.setCapability("appPackage", "com.swaglabsmobileapp");
		androidDriver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		// Login
		wait = new WebDriverWait(androidDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(userNameField));
		androidDriver.findElement(userNameField).sendKeys("standard_user");

		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		androidDriver.findElement(passwordField).sendKeys("secret_sauce");

		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		androidDriver.findElement(loginButton).click();
	}

	@Test
	public void homePageHeadingisDisplayed() {
		wait = new WebDriverWait(androidDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
		assertTrue(androidDriver.findElement(pageHeading).isDisplayed());

	}
}
