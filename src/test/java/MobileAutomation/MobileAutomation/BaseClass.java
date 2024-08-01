package MobileAutomation.MobileAutomation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass {

	AndroidDriver driver;

	public void configureAppium() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("01surbhi");
		options.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		options.setCapability("appPackage", "com.swaglabsmobileapp");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

	}

	public void tearDown() {
		driver.quit();
	}
}
