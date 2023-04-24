package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;

public class AllCurrencyTest {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();


    @Test
    public void currencyTest(){
    // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"),"uygulama yukleme basarisiz");
    // uygulamanin acildigi dogrulanir

    // cevirmek istedigimiz para birimi zloty olarak secilir

    // cevirelecek olan para birimi Tl olarak secilir

    // cevrilen tutar screenShot olarak kaydedilir

    // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir

    // bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir

    }
}
