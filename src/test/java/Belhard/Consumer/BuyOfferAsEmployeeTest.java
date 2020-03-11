package Belhard.Consumer;

import Belhard.ConsumerMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BuyOfferAsEmployeeTest {
    @Test
    public void BuyOfferAsEmployee() {
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        consumer.openOfferByName("Automatic offer Type 1");
        $(By.cssSelector("span[class*='condition-link']"), 1).click();
        $(By.cssSelector("div[class*='form-toggle__switch']")).click();
        $(By.cssSelector("input[class*='btn-open']")).click();
        $(By.cssSelector("input[class*='btn--open']")).click();
        $(By.cssSelector("input[class='modal__btn']")).click();

   }
}
