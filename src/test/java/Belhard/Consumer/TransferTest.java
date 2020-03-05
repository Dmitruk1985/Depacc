package Belhard.Consumer;

import Belhard.ConsumerMenu;
import Belhard.Landing;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.Landing.EMAIL_CONSUMER_AUXILIARY;
import static com.codeborne.selenide.Selenide.$;

public class TransferTest {
    @Test
    public void transferOffer(){
        Landing landing = new Landing();
        landing.ConsumerLoginByDefault();
        ConsumerMenu consumer = new ConsumerMenu();
        double oldBalance=consumer.getBalanceBYN();
        consumer.openDepaccByName("Automatic offer Type 1");
        $(By.cssSelector("button[class*='transfer']")).click();
        $(By.id("recipientEmail")).setValue(EMAIL_CONSUMER_AUXILIARY);
        $(By.id("transferAmount")).setValue("0.01").pressEnter();
        $(By.cssSelector("input[class*='confirmation__btn--open']")).click();
        $(By.cssSelector("input[class='modal__btn']")).waitUntil(Condition.enabled,11000).click();
        double newBalance=consumer.getBalanceBYN();
        Assertions.assertEquals(oldBalance-0.01, newBalance); //Проверка баланса после трансфера

    }
}
