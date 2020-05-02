package Belhard.Business;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.OFFER_IMAGE_2;
import static Belhard.BusinessMenu.OFFER_NAME_BOOKABLE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewOffer_BookableTest {
    @Test
    public void createNewOffer() {
        Configuration.holdBrowserOpen = true;
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/offer/create");
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue(OFFER_IMAGE_2);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue(OFFER_NAME_BOOKABLE);
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.cssSelector("div[class*='ql-editor']")).setValue("This offer is created by automatic software. #BYN #Available to all #Bookable");
        $(By.id("amount")).setValue("5");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.xpath("//label[@for='isPublic']/following::div[3]")).click();
        $(By.xpath("//label[@for='isBookable']/following::div[3]")).click();
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        $(By.cssSelector("input[class*='submit']")).click();

        /*Блок проверок*/
        //1. Проверка наличия созданного оффера в разделе "Мои предложения"
        business.searchOffer(OFFER_NAME_BOOKABLE);
        //2. Проверка того, что оффер виден пользователям
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        consumer.searchOfferByName(OFFER_NAME_BOOKABLE);

    }
}
