package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class CouponTransferTest {
    @Test
    /*Трансфер купона*/
    public void couponTransfer() {
        Configuration.browserSize="1400x1400"; //Этот параметр нужен, чтобы были видны кнопки у купона
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        consumer.openMyCoupons();
        consumer.setCouponsFilter(BUSINESS_NAME);
        $(By.cssSelector("button[class*='transfer']")).click();
        $(By.id("email")).setValue(EMAIL_CONSUMER_TEST).pressEnter();
        $(By.cssSelector("input[class*='open']")).click();
        $(By.cssSelector("input[value='OK']")).click();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка истроии операций у отправителя
        consumer.checkOperationsHistory(HISTORY_COUPON_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, 0, "BYN");
        //2. Проверка истроии операций у получателя
        consumer.signOut();
        consumer.loginConsumerByData(EMAIL_CONSUMER_TEST,PASSWORD);
        consumer.checkOperationsHistory(HISTORY_COUPON_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, 0, "BYN");
        //3. Проверка сообщений у получателя
        consumer.checkMessages(MESSAGE_COUPON_TRANSFER,date,0,"");
        //4. Проверка уведомления на почте получателя
        consumer.checkNotification(HISTORY_COUPON_TRANSFER,MESSAGE_COUPON_TRANSFER);
    }
}
