package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class DepaccTransferTest {
    /*Трансфер депака*/
    @Test
    public void depaccTransfer() {
        //  Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1400x1400";
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.openDepaccWithTransfer();
        double oldAmount = consumer.getDepaccAmmount();
        $(By.cssSelector("button[class*='transfer']")).click();
        $(By.id("recipientEmail")).setValue(EMAIL_CONSUMER_TEST);
        $(By.id("transferAmount")).setValue(String.valueOf(TRANSFER_AMOUNT));
        SUBMIT_BUTTON.click();
        $(By.cssSelector("input[class*='confirmation__btn--open']")).click();
        MODAL_BUTTON.click();
        double newAmount = consumer.getDepaccAmmount();
        String currency = consumer.getDepaccCurrency();
        double[] newTotalBalance = consumer.getTotalBalance();
        int size = CURRENCIES.size();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка баланса
        //1.1 Проверка баланса
        consumer.checkBalance(currency, -TRANSFER_AMOUNT, oldTotalBalance, newTotalBalance);
        // 1.2 Проверка cуммы депака после трансфера (должна уминьшиться на значение суммы трансфера)
        Assertions.assertEquals((consumer.roundDouble(oldAmount - TRANSFER_AMOUNT)), newAmount);
        //2. Проверка истории операций у отправителя
        consumer.checkOperationsHistory(HISTORY_DEPACC_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, TRANSFER_AMOUNT, currency);
        //3. Проверка истроии операций у получателя
        consumer.signOut();
        consumer.loginByData(EMAIL_CONSUMER_TEST, PASSWORD);
        consumer.checkOperationsHistory(HISTORY_DEPACC_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, TRANSFER_AMOUNT, currency);
        //4. Проверка сообщений у получателя
        consumer.checkMessages(MESSAGE_DEPACC_TRANSFER, date, TRANSFER_AMOUNT, currency);
        //5. Проверка уведомления на почте получателя
        consumer.checkNotification(MAIL_DEPACC_TRANSFER, MESSAGE_DEPACC_TRANSFER);

    }
}
