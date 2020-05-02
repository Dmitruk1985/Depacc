package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BuyOffer_BookableTest {
    @Test
    /*Бронирование оффера*/
    public void buyOfferAsClient() {
       // Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(OFFER_NAME_BOOKABLE);
        double amount = consumer.getOfferAmmount();
        String currency = consumer.getOfferCurrency();
        $(By.cssSelector("input[class*='btn-secondary']")).click();
        MODAL_BUTTON.click();
        MODAL_BUTTON.click();
        open(URL_CONSUMER_SIGNIN); //В данный момент происходит переход на гугл форму, поэтому нужно залогиниться повторно, должен быть автологин.
        double[] newTotalBalance = consumer.getTotalBalance();
        String date = consumer.getDate();


        /*Блок проверок*/
        //1. Проверка баланса (должен остаться неизменным)
        consumer.checkBalanceConstant(oldTotalBalance, newTotalBalance);
        //2. Проверка истории операций у Пользователя
        consumer.checkOperationsHistory(HISTORY_BOOKABLE_DEPACC, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //3. Проверка раздела "Сообщения" у Пользователя
        consumer.checkMessages(MESSAGE_NEW_DEPACC, date, amount, currency);
        //4. Проверка истории операций у Бизнеса
        BusinessMenu business = new BusinessMenu();
        business.login();
        business.checkOperationsHistory(HISTORY_BOOKABLE_DEPACC, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //5. Проверка уведомлений на почте Пользователя и Бизнеса
        consumer.checkNotifications(MAIL_NEW_DEPACC, MAIL_NEW_DEPACC_BUSINESS, MESSAGE_NEW_DEPACC);
    }
}
