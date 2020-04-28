package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUTTON_ACCEPT_PAYMENT;
import static com.codeborne.selenide.Selenide.*;

/*Реализация купона*/
public class CouponReleaseTest {
    @Test
    public void couponRelease() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        executeJavaScript("window.open()");
        switchTo().window(1);
        BusinessMenu business = new BusinessMenu();
        business.login();
        BUTTON_ACCEPT_PAYMENT.click();
        $(By.cssSelector("button[class*='accept-coupon']")).click();
        /*Ожидание сканирования QR-кода*/
        sleep(10000);
        switchTo().window(0);
        $(By.cssSelector("input[class*='submit']")).click();
        $(By.cssSelector("input[class='modal__btn ']")).click();

    }
}
