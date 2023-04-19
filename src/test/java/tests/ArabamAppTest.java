package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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
    public void arabamTest() throws InterruptedException {
        // driver.findElement(By.xpath("//*[@text='İlan ver']")).click();
        // Arabam kac para bolumune tiklayalim
        driver.findElement(By.xpath("(//*[@text='Arabam kaç para?'])[1]")).click();
        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        AndroidElement fiyatMerak =driver.findElement(By.xpath("//*[@text='Aracımın fiyatını merak ediyorum']"));
        fiyatMerak.click();
        // Wolkswagen markasini secelim
        Thread.sleep(1000);
        TouchAction action=new TouchAction<>(driver);
        action.press(PointOption.point(543,1732)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).
                moveTo(PointOption.point(537,381)).release().perform();

        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Volkswagen']").click();
     /*
         action.press(PointOption.point(537,381)).
                 waitAction(WaitOptions.waitOptions(Dration.ofMillis(500))).
                 moveTo(PointOption.point(543,1732)).release().perform();
           Eger ki bizler daha onceden kaydirma islemi gerceklestirmissek tam tersi haraketini gerceklestirmek icin yazdigimiz
           koordinat degerlerini tam tersi olacak sekilde yazmak o islemin zittini gerceklestirir.
      */

        // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2011']").click();
        // model secimi yapalim
        driver.findElementByXPath("//*[@text='Passat']").click();
        // govde tipini secelim
        driver.findElementByXPath("//*[@text='Sedan']").click();
        // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();
        // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();
        // Versiyon secimi yapalim
        Thread.sleep(1000);
        action.press(PointOption.point(453,1738)).release().perform(); //490,1747
        // aracin km bilgilerini girelim
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("190000");
        }
        else {
            driver.findElementById("com.dogan.arabam:id/et_km").sendKeys("150000");
        }
        driver.findElementById("com.dogan.arabam:id/btn_price_prediction_submit").click();
        // aracin rengini secelim
        driver.findElementByXPath("//*[@text='Gri (metalik)']").click();
        // opsiyel donanim (varsa) seecelim
        driver.findElementById("com.dogan.arabam:id/btnNext").click();
        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        AndroidElement kaput=driver.findElementById("com.dogan.arabam:id/iv_B01001");
        kaput.click();
        Thread.sleep(1000);
        driver.findElementByXPath("(//*[@text='Boyalı'])[2]").click();
        Thread.sleep(1000);
        driver.findElementById("com.dogan.arabam:id/btn_next").click();
        // tramer kaydi yok kismina tiklayalim
        driver.findElementById("com.dogan.arabam:id/rbHasNoTramerEntry").click();
        driver.findElementById("com.dogan.arabam:id/btnNext").click();
        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        String avaragePrice= driver.findElementById("com.dogan.arabam:id/tvAveragePrice").getText();
        //588.500 TL
        String lastPrice=avaragePrice.replaceAll("\\D","");
        Assert.assertTrue(Integer.parseInt(lastPrice)>500000);

        // uygulamayi kapatalim
        driver.closeApp();

    }
}