import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ArabamAppTest {

    AndroidDriver<AndroidElement> driver;   // AndroidElement yerine MobileElement de kullanılabilir
    AppiumDriver<MobileElement> appiumDriver;   // hem android te hem ios ta calisir.
    final String deviceName = "PIXEL";
    final String platformName = "Android";
    final String platformVersion = "10.0";
    final String automationName = "UiAutomator2";

    /*
    appiumDriver ile AndroidDriver methodları araasında biraz farklılıklar var.
    benzer olmalarına karsın ayrı değillerdir
     */

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);

        capabilities.setCapability("appPackage","com.dogan.arabam");
        // hangi uygulama uzerinde calışmak istediğimizi

        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        //  uygulamada hangi sayfayı baslatmak istediğimizi gosterir. Main.Activity ya da Homepage.activity

        capabilities.setCapability(MobileCapabilityType.NO_RESET,"false");
        // false kullanırsak uygulama calıştıkan sonra işlem bittikten sonra sıfırlar yani ilk baştaki gibi sıfırlanır
        // true olursa uygulama çalıştırıldıktan sonra işlem bittikten sonra sıfırlamaz


        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void arabamTest(){

       // driver.findElement(By.xpath("//*[@text=\"İlan ver\"]")).click();

        driver.findElement(By.xpath("//*[@text=\"Ana Sayfa\"]")).click();

       // driver.findElement(By.xpath("//*[@text=\"Arabam kaç para?\"]")).click();


    }
}