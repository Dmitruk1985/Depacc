package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.BusinessMenu.DEFAULT_OFFER_NAME;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class BuyOfferAsEmployeeTest {
    @Test
    /*Покупка оффера на условиях для сотрудника*/
    public void BuyOfferAsEmployee() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(DEFAULT_OFFER_NAME);
        double amount = consumer.getOfferAmmount();
        $(By.cssSelector("span[class*='condition-link']"),1).click();
        $(By.cssSelector("div[class*='form-toggle__switch']")).click();
        $(By.cssSelector("input[class*='btn-open']")).click();
        $(By.cssSelector("input[class*='btn--open']")).click();
        $(By.cssSelector("input[class='modal__btn ']")).click(); //Здесь лишний пробел, изменить после исправления
        String currency=consumer.getDepaccCurrency();
        double[] newTotalBalance = consumer.getTotalBalance();
        String date=consumer.getDate();
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
        BUTTON_MENU_CONSUMER.click(); //??? Эта строка нужна, чтобы открылась история операций
        consumer.checkOperationsHistory(HISTORY_BUY_OFFER_AS_EMPLOYEE, BUSINESS_NAME, CONSUMER_NAME, date, amount,currency);
        //3. Проверка раздела "Сообщения"
        consumer.checkMessages(MESSAGE_NEW_DEPACC,date,amount,currency);
        //4. Проверка уведомления на почте
        consumer.checkNotification(MAIL_NEW_DEPACC,MESSAGE_NEW_DEPACC);

    }
}
