package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class CouponTransferTest {
    @Test
    public void couponTransfer(){
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        consumer.openMyCoupons();
        consumer.setCouponsFilter(BUSINESS_NAME);
        $(By.cssSelector("span[class*='transfer']")).click();
        $(By.id("email")).waitUntil(Condition.visible,2000).setValue(EMAIL_CONSUMER_AUXILIARY).pressEnter();
        $(By.cssSelector("input[class*='open']")).click();
        $(By.cssSelector("input[value='OK']")).click();

        /*Блок проверок*/
        //1. Проверка истроии операций
        consumer.checkOperationsHistory(HISTORY_COUPON_TRANSFER,CONSUMER_NAME,CONSUMER_AUXILIARY_NAME,0,"BYN");

    }
}
