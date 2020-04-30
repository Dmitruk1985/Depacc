package Belhard.Consumer;

import Belhard.ConsumerMenu;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;

public class DepaccTransferTest {
    /*Трансфер депака*/
    @Test
    public void depaccTransfer(){
        //Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.openMyDepaccs();
        $(By.cssSelector("button[class*='depacc-link']")).click();
        double oldAmount = consumer.getDepaccAmmount();
        $(By.cssSelector("button[class*='transfer']")).click();
        $(By.id("recipientEmail")).setValue(EMAIL_CONSUMER_TEST);
        $(By.id("transferAmount")).setValue(String.valueOf(TRANSFER_AMOUNT));
        SUBMIT_BUTTON.click();
        $(By.cssSelector("input[class*='confirmation__btn--open']")).click();
        MODAL_BUTTON.click();
        double newAmount = consumer.getDepaccAmmount();
        String currency=consumer.getDepaccCurrency();
        double[] newTotalBalance = consumer.getTotalBalance();
        int size = CURRENCIES.size();
        String date = consumer.getDate();

        /*Блок проверок*/
        //1. Проверка баланса
        //1.1 Проверка суммы баланса BYN после трансфера (должна уменьшиться на значение суммы трансфера)
        Assertions.assertEquals((consumer.roundDouble(oldTotalBalance[0]-TRANSFER_AMOUNT)), newTotalBalance[0]);
        //1.2 Проверка cуммы депака после трансфера (должна уминьшиться на значение суммы трансфера)
        Assertions.assertEquals((consumer.roundDouble(oldAmount-TRANSFER_AMOUNT)), newAmount);
        //1.3 Проверка баланса других валют (должны остаться неизменными)
        for (int i = 1; i < size; i++) {
            Assertions.assertEquals(0, (oldTotalBalance[i]-newTotalBalance[i]));
        }
        //2. Проверка истории операций у отправителя
        consumer.checkOperationsHistory(HISTORY_DEPACC_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, TRANSFER_AMOUNT, currency);
        //3. Проверка истроии операций у получателя
        consumer.signOut();
        consumer.loginConsumerByData(EMAIL_CONSUMER_TEST,PASSWORD);
        consumer.checkOperationsHistory(HISTORY_DEPACC_TRANSFER, CONSUMER_NAME, CONSUMER_TEST_NAME, date, TRANSFER_AMOUNT, currency);
        //4. Проверка сообщений у получателя
        consumer.checkMessages(MESSAGE_DEPACC_TRANSFER,date,TRANSFER_AMOUNT, currency);
        //5. Проверка уведомления на почте получателя
        consumer.checkNotification(MAIL_DEPACC_TRANSFER,MESSAGE_DEPACC_TRANSFER);

    }
}
