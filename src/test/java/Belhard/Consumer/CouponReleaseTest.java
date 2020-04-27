package Belhard.Consumer;

import Belhard.BusinessMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUTTON_ACCEPT_PAYMENT;
import static com.codeborne.selenide.Selenide.$;

/*Реализация купона*/
public class CouponReleaseTest {
    @Test
    public void couponRelease() {
        Configuration.holdBrowserOpen = true;
        BusinessMenu business = new BusinessMenu();
        business.login();
        BUTTON_ACCEPT_PAYMENT.click();
        $(By.cssSelector("button[class*='accept-coupon']")).click();
        $(By.id("body")).pressTab();


    }
}
