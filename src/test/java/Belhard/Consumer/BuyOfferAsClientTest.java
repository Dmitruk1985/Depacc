package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.BusinessMenu.DEFAULT_OFFER_NAME;
import static Belhard.ConsumerMenu.*;
import static Belhard.ConsumerMenu.MESSAGE_NEW_DEPACC;
import static com.codeborne.selenide.Selenide.$;

public class BuyOfferAsClientTest {
    @Test
    /*Покупка оффера за реальные деньги (оплата картой)*/
    public void buyOfferAsClient() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(DEFAULT_OFFER_NAME);
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
        int size = CURRENCIES.size();

        /*Блок проверок*/
        /*1. Проверка баланса*/
        //1.1 Проверка суммы баланса BYN после принятие оффера (должна увеличиться на значение стоимости оффера)
        Assertions.assertEquals((consumer.roundDouble(oldTotalBalance[0] + amount)), newTotalBalance[0]);
        //1.2 Проверка баланса других валют (должны остаться неизменными)
        for (int i = 1; i < size; i++) {
            Assertions.assertEquals(0, (oldTotalBalance[i] - newTotalBalance[i]));
        }
        //2. Проверка истории операций
        BUTTON_MENU_CONSUMER.click(); //??? Костыль, чтобы открылась история операций (по идее, эта строка не нужна, нажатие на кнопку происходит внутри метода)
        consumer.checkOperationsHistory(HISTORY_BUY_OFFER_AS_CLIENT, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //3. Проверка раздела "Сообщения"
        consumer.checkMessages(MESSAGE_NEW_DEPACC, date, amount, currency);
        //4. Проверка уведомления на почте
        consumer.checkNotification(MAIL_NEW_DEPACC, MESSAGE_NEW_DEPACC);
    }
}
