package MobileAutomation.MobileAutomation;

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

public class AppiumBasics {

	private AndroidDriver driver;

	By userNameField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Username\")");
	By passwordField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")");
	By loginButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"test-LOGIN\")");
	By pageHeading = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)");
	By addtocart = AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)");
	By gotocart = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)");
	By removefromcart = AppiumBy.androidUIAutomator("new UiSelector().description(\"test-REMOVE\")");
	By continueshopping = AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE SHOPPING\")");

	@BeforeTest
	public void setup() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("01surbhi");
		options.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		options.setCapability("appPackage", "com.swaglabsmobileapp");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(userNameField));
		driver.findElement(userNameField).sendKeys("standard_user");

		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		driver.findElement(passwordField).sendKeys("secret_sauce");

		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		driver.findElement(loginButton).click();
	}

	@Test(priority = 1)
	public void homePageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(pageHeading));
		boolean isHeadingDisplayed = driver.findElement(pageHeading).isDisplayed();

	}

	@Test(priority = 2)
	public void addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(addtocart));

		boolean isRemoveButtonDisplayed;
		try {
			isRemoveButtonDisplayed = driver.findElement(removebutton).isDisplayed();
		} catch (Exception e) {
			isRemoveButtonDisplayed = false; // no such element exception
		}

		
		assertFalse(isRemoveButtonDisplayed);

		
		driver.findElement(addtocart).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(removebutton)); // ab chalana
		isRemoveButtonDisplayed = driver.findElement(removebutton).isDisplayed();
		assertTrue(isRemoveButtonDisplayed);
	}

	@Test(priority = 3)
	public void goToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(gotocart));
		driver.findElement(gotocart).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yourcartheading));
		WebElement isCartPageHeading = driver.findElement(yourcartheading);
		Assert.assertEquals(true, isCartPageHeading.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "addToCart", "goToCart" })
	public void removeFromCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(removefromcart));
		driver.findElement(removefromcart).click();

		// invisibility false hone ka matlab hai its visible
		Boolean removeButton = wait.until(ExpectedConditions.invisibilityOfElementLocated(removefromcart));
		Assert.assertTrue(removeButton);
	}

	@Test(priority = 5)
	public void clickOnContinueShopping() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(continueshopping));
		driver.findElement(continueshopping).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(productpageheading));
		WebElement isHeadingProduct = driver.findElement(productpageheading);
		Assert.assertEquals(true, isHeadingProduct.isDisplayed());

	}
}
