import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DenemeTesti {

    AndroidDriver<AndroidElement> driver;   // AndroidElement yerine MobileElement de kullanılabilir
    AppiumDriver<MobileElement> appiumDriver;   // hem android te hem ios ta calisir.
    final String deviceName="PIXEL";
    final String platformName="Android";
    final String platformVersion="10.0";
    final String automationName="UiAutomator2";

    /*
    appiumDriver ile AndroidDriver methodları araasında biraz farklılıklar var.
    benzer olmalarına karsın ayrı değillerdir
     */

    @BeforeTest
    public void deneme1() throws MalformedURLException {

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,automationName);
      //  capabilities.setCapability(MobileCapabilityType.APP,"C:\\Java Projects\\Appium_T-108\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");

        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }



    @Test
    public void carpma(){

        driver.findElementByAccessibilityId("8").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("2").click();
        driver.findElementByAccessibilityId("0").click();
        String result = driver.findElementById("com.google.android.calculator:id/result_preview").getText();
        System.out.println(result);
        Assert.assertEquals(Integer.parseInt(result),1600);

    }




















    // kablo ve visor uuygulaması ile kendi telefonumun bağlanması
    @Test
    public void deneme2() throws MalformedURLException {

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Redmi Not 8 Pro");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"11.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Java Projects\\Appium_T-108\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");

        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


}
