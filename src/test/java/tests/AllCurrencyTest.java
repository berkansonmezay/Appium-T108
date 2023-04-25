package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;
import utils.ReusableMethods;

import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.IOException;

public class AllCurrencyTest {

    // all currency uygulamasinin yuklendigi dogulanir
// uygulamanin acildigi dogrulanir
// cevirmek istedigimiz para birimi zloty olarak secilir
// cevirelecek olan para birimi Tl olarak secilir

// cevrilen tutar screenShot olarak kaydedilir
// Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
// bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir
AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();

    @Test
    public void currencyTest() throws IOException {
        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"),"uygulama yukleme basarisiz");
        // uygulamanin acildigi dogrulanir
        AndroidElement anaSayfaApp=driver.findElementByXPath("//*[@text='CURRENCY CONVERTER']");
        String uygulamaDogrulamaActual=anaSayfaApp.getText();
        String expected="CURRENCY CONVERTER";
        Assert.assertEquals(uygulamaDogrulamaActual,expected,"uyguluma basarili bir sekilde baslatilamadi");
       AndroidElement ilkKategori=  driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/linearLayoutPopupHistory");
       ilkKategori.click();
        // cevirmek istedigimiz para birimi zloty olarak secilir
      //  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PLN\"))");
        ReusableMethods.scrollWithUiScrollable("PLN");
     // cevirelecek tutari tuslayalim
       AndroidElement kategore2= driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/SpinnerCurrencyB"));
       kategore2.click();
        ReusableMethods.scrollWithUiScrollable("Turkish Lira");
        driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/b1")).click();
        driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/b5")).click();
        driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/b0")).click();
        driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/b0")).click();
        // cevrilen tutar screenShot olarak kaydedilir

        File ekranfotografi=driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ekranfotografi,new File("ahmet.jpg"));

        ReusableMethods.getScreenshot("t108");

        AndroidElement cevirilenBirim=driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB");
        String sonucParaDegeriPLN =cevirilenBirim.getText();
// bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir
        ilkKategori.click();
        ReusableMethods.scrollWithUiScrollable("USD");
    }
}
