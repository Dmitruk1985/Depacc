package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.DEFAULT_OFFER_NAME;
import static Belhard.ConsumerMenu.BUY_OFFER_AS_EMPLOYEE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyOfferAsEmployeeTest {
    @Test
    public void BuyOfferAsEmployee() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        double[] oldTotalBalance = consumer.getTotalBalance();
        consumer.searchOfferByName(DEFAULT_OFFER_NAME);
        double amount = consumer.getOfferAmmount();
        $(By.cssSelector("span[class*='condition-link']"), 1).click(); //Переделать локатор без индекса
        $(By.cssSelector("div[class*='form-toggle__switch']")).click();
        $(By.cssSelector("input[class*='btn-open']")).click();
        $(By.cssSelector("input[class*='btn--open']")).click();
        $(By.cssSelector("input[class='modal__btn ']")).click(); //Здесь лишний пробел, изменить после исправления
        double[] newTotalBalance = consumer.getTotalBalance();
        int size = $$(By.cssSelector("li[class='profile-info__amount']")).size() / 2;

        /*Блок проверок*/
        //Проверка суммы баланса BYN после принятие оффера (должна увеличиться на значение стоимости оффера)
        Assertions.assertEquals((oldTotalBalance[0] + amount), newTotalBalance[0]);
        //Проверка баланса других валют (должны остаться неизменными)
        for (int i = 1; i < size; i++) {
            Assertions.assertEquals(0, (oldTotalBalance[i] - newTotalBalance[i]));
        }
        //Проверка истории операций
        consumer.checkOperationsHistory(BUY_OFFER_AS_EMPLOYEE,amount);
    }
}
