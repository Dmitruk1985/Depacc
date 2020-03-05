package Belhard.Consumer;

import Belhard.ConsumerMenu;
import Belhard.Landing;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BuyOfferAsClientTest {
    @Test
    public void buyOfferAsClient(){
        Landing landing = new Landing();
        landing.ConsumerLoginByDefault();
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.openOfferByName("Automatic offer Type 1");
        $(By.cssSelector("input[class*='accept']")).click();
        $(By.id("request_credit_card_number_1")).setValue("4200");
        $(By.id("request_credit_card_number_2")).setValue("0000");
        $(By.id("request_credit_card_number_3")).setValue("0000");
        $(By.id("request_credit_card_number_4")).setValue("0000");
        $(By.id("request_credit_card_exp_month")).setValue("02");
        $(By.id("request_credit_card_exp_year")).setValue("20");
        $(By.id("request_credit_card_holder")).setValue("JANE SMITH");
        $(By.id("request_credit_card_verification_value")).setValue("321");
        $(By.name("commit")).click();
        $(By.cssSelector("a[class*='button']")).waitUntil(Condition.exist,10000).click();
        $(By.cssSelector("button[class*='result']")).click();
        $(By.cssSelector("button[class*='profile-consumer']")).should(Condition.exist);
    }
}
