package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.*;

public class TransferTest {
    @Test
    public void transferOffer(){

        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.openDepaccByName(DEFAULT_OFFER);
        double oldAmount = consumer.getDepaccAmmount();
        $(By.cssSelector("button[class*='transfer']")).click();
        $(By.id("recipientEmail")).setValue(EMAIL_CONSUMER_AUXILIARY);
        $(By.id("transferAmount")).setValue(String.valueOf(TRANSFER_AMOUNT)).pressEnter();
        $(By.cssSelector("input[class*='confirmation__btn--open']")).click();
        $(By.cssSelector("input[class='modal__btn']")).click();
        double newAmount = consumer.getDepaccAmmount();
        double[] newTotalBalance = consumer.getTotalBalance();
        int size = $$(By.cssSelector("li[class='profile-info__amount']")).size() / 2;
        /*Блок проверок*/
        Assertions.assertEquals((oldTotalBalance[0]-TRANSFER_AMOUNT), newTotalBalance[0]); //Проверка суммы баланса BYN после трансфера (должна уминьшиться на значение TRANSFER_AMOUNT)
        Assertions.assertEquals((oldAmount-TRANSFER_AMOUNT), newAmount); //Проверка cуммы депака после трансфера (должна уминьшиться на значение TRANSFER_AMOUNT)
        for (int i = 1; i < size; i++) { //Проверка баланса других валют (должны остаться неизменными)
            Assertions.assertEquals(0, (oldTotalBalance[i]-newTotalBalance[i]));
        }
        /*Проверка истории операций*/
        open("/deposit_transactions");
        $(By.cssSelector("span[class*='transaction-name']")).shouldHave(Condition.text("Трансфер"));//Должна появиться запись типа "Трансфер"
        $(By.cssSelector("span[class='transaction-item__name transaction-item__amount']")).shouldHave(Condition.text(String.valueOf(TRANSFER_AMOUNT)));//Сумма операции должна равняться TRANSFER_AMOUNT
        sleep(3000);

    }
}
