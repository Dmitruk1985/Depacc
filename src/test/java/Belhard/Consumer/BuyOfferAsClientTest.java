package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.DEFAULT_OFFER_NAME;
import static Belhard.ConsumerMenu.BUTTON_MENU_CONSUMER;
import static com.codeborne.selenide.Selenide.$;

public class BuyOfferAsClientTest {
    @Test
    public void buyOfferAsClient(){
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        consumer.searchOfferByName(DEFAULT_OFFER_NAME);
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
        $(By.cssSelector("a[class*='button']")).waitUntil(Condition.exist,10000).click();
        $(By.cssSelector("button[class*='result']")).click();
        BUTTON_MENU_CONSUMER.should(Condition.exist);
    }
}
