package Belhard.Consumer;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class BuyOffer_EmployeeTest {
    @Test
    /*Покупка оффера на условиях для сотрудника*/
    public void BuyOfferAsEmployee() {
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(OFFER_NAME_EMPLOYEE);
        double amount = consumer.getOfferAmmount();
        $(By.cssSelector("span[class*='condition-link']"), 1).click();
        $(By.cssSelector("div[class*='form-toggle__switch']")).click();
        $(By.cssSelector("input[class*='btn-open']")).click();
        $(By.cssSelector("input[class*='btn--open']")).click();
        MODAL_BUTTON.click();
        String currency = consumer.getDepaccCurrency();
        double[] newTotalBalance = consumer.getTotalBalance();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка баланса
        consumer.checkBalance(currency, amount, oldTotalBalance, newTotalBalance);
        //2. Проверка истории операций у Пользователя
        consumer.checkOperationsHistory(HISTORY_BUY_OFFER_AS_EMPLOYEE, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //3. Проверка раздела "Сообщения" у Пользователя
        consumer.checkMessages(MESSAGE_NEW_DEPACC, date, amount, currency);
        //4. Проверка истории операций у Бизнеса
        BusinessMenu business = new BusinessMenu();
        business.login();
        business.checkOperationsHistory(HISTORY_BUY_OFFER_AS_EMPLOYEE, BUSINESS_NAME, CONSUMER_NAME, date, amount, currency);
        //5. Проверка уведомлений на почте Пользователя и Бизнеса
        consumer.checkNotifications(MAIL_NEW_DEPACC, MAIL_NEW_DEPACC_BUSINESS, MESSAGE_NEW_DEPACC);
        sleep(1000); //Без этой задержки не удаляются письма
    }
}
