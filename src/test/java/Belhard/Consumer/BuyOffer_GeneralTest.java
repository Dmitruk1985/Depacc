package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class BuyOffer_GeneralTest {
    @Test
    /*Покупка оффера за реальные деньги (оплата картой)*/
    public void buyOfferAsClient() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(OFFER_NAME_GENERAL);
        double amount = consumer.getOfferAmmount();
        String currency = consumer.getOfferCurrency();
        $(By.cssSelector("input[class*='accept']")).click();
        $(By.id("request_credit_card_number_1")).setValue("4200");
        $(By.id("request_credit_card_number_2")).setValue("0000");
        $(By.id("request_credit_card_number_3")).setValue("0000");
        $(By.id("request_credit_card_number_4")).setValue("0000");
        $(By.id("request_credit_card_exp_month")).setValue("02");
        $(By.id("request_credit_card_exp_year")).setValue("21");
        $(By.id("request_credit_card_holder")).setValue("JANE SMITH");
        $(By.id("request_credit_card_verification_value")).setValue("321");
        $(By.name("commit")).click();
        $(By.cssSelector("a[class*='button']")).waitUntil(Condition.exist, 10000).click();
        $(By.cssSelector("button[class*='result']")).click();
        double[] newTotalBalance = consumer.getTotalBalance();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка баланса
        consumer.checkBalance(currency, amount, oldTotalBalance, newTotalBalance);
        //2. Проверка истории операций у Пользователя
        consumer.checkOperationsHistory(HISTORY_BUY_OFFER_AS_CLIENT, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //3. Проверка раздела "Сообщения" у Пользователя
        consumer.checkMessages(MESSAGE_NEW_DEPACC, date, amount, currency);
        //4. Проверка истории операций у Бизнеса
        BusinessMenu business = new BusinessMenu();
        business.login();
        business.checkOperationsHistory(HISTORY_NEW_DEPACC_BUSINESS, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //5. Проверка уведомлений на почте Пользователя и Бизнеса
        consumer.checkNotifications(MAIL_NEW_DEPACC, MAIL_NEW_DEPACC_BUSINESS, MESSAGE_NEW_DEPACC);
    }
}
