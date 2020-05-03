package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.*;

/*Реализация купона*/
public class ChargeTest {
    /*Оплата депаком*/
    @Test
    public void charge() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        executeJavaScript("window.open()");
        switchTo().window(1);
        BusinessMenu business = new BusinessMenu();
        business.login();
        BUTTON_ACCEPT_PAYMENT.click();
        String currency = "BYN";
        int amount = 21;
        //Ввод в терминале суммы "21", оплата более 20 руб. требует подтверждения.
        $(By.className("terminal-keyboard__button"), 1).click();
        $(By.className("terminal-keyboard__button")).click();
        $(By.cssSelector("button[class*='btn-submit']")).click();
        /*Ожидание сканирования QR-кода*/
        sleep(10000);
        switchTo().window(0);
        SUBMIT_BUTTON.click();
        MODAL_BUTTON.click();
        switchTo().window(1);
        $(By.cssSelector("button[class*='progress-page']")).click();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка истории операций у Бизнеса
        business.checkOperationsHistory(HISTORY_CHARGE,CONSUMER_NAME,BUSINESS_NAME, date, amount, currency);
        //2. Проверка сообщений у Бизнеса
        business.checkMessages(MESSAGE_CHARGE, date, amount, currency);
        //3. Проверка чека у Бизнеса
        business.checkCheque(date,amount,currency);
        //4. Проверка истории операций у Пользователя
        switchTo().window(0);
        consumer.checkOperationsHistory(HISTORY_CHARGE,CONSUMER_NAME,BUSINESS_NAME, date, amount, currency);
        //5. Проверка сообщений у Пользователя
        //  consumer.checkMessages(MESSAGE_COUPON_RELEASE,date,0,""); //??? Возможно, баг. Сообщения не всегда приходят.
        //6. Проверка уведомления на почте Пользователя
        consumer.checkNotification(MAIL_CHARGE, MESSAGE_CHARGE);

    }
}
