package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApkYukleme {
    AndroidDriver<AndroidElement> driver;   // AndroidElement yerine MobileElement de kullanılabilir
    AppiumDriver<MobileElement> appiumDriver;   // hem android te hem ios ta calisir.
    final String deviceName = "PIXEL";
    final String platformName = "Android";
    final String platformVersion = "10.0";
    final String automationName = "UiAutomator2";


    @Test
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Java Projects\\Appium_T_108\\Apps\\AllCurrencyConverter_2023-03-11.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
}


