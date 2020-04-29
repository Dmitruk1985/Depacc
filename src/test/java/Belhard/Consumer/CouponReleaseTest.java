package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.BusinessMenu.BUTTON_ACCEPT_PAYMENT;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.*;

/*Реализация купона*/
public class CouponReleaseTest {
    @Test
    public void couponRelease() {
       // Configuration.holdBrowserOpen = true;
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
        switchTo().window(1);
        $(By.cssSelector("button[class*='progress-page']")).click();
        String date=consumer.getDate();

        /*Блок проверок*/
        //1. Проверка истории операций у Бизнеса
        business.checkOperationsHistory(HISTORY_COUPON_RELEASE, BUSINESS_NAME, CONSUMER_NAME, date, 0,"BYN");
        //2. Проверка истории операций у Пользователя
        switchTo().window(0);
        consumer.checkOperationsHistory(HISTORY_COUPON_RELEASE, BUSINESS_NAME, CONSUMER_NAME, date, 0, "BYN");
        //3. Проверка сообщений у Пользователя
        consumer.checkMessages(MESSAGE_COUPON_RELEASE,date,0,"");
        //4. Проверка уведомления на почте Пользователя
        consumer.checkNotification(MAIL_COUPON_RELEASE,MESSAGE_COUPON_RELEASE);

    }
}
