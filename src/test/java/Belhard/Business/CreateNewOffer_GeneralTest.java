package Belhard.Business;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.OFFER_IMAGE_1;
import static Belhard.BusinessMenu.OFFER_NAME_GENERAL;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewOffer_GeneralTest {
    @Test
    public void createNewOffer() {
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/offer/create");
        //загрузка изображения офера
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue(OFFER_IMAGE_1);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue(OFFER_NAME_GENERAL);
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.cssSelector("div[class*='ql-editor']")).setValue("This offer is created by automatic software. #BYN #No automatic return #Available to all");
        $(By.id("amount")).setValue("10");
        $(By.id("currency")).selectOptionContainingText("BYN");
       // $(By.id("designTypeMarker")).selectOptionContainingText("Бесплатно");
        $(By.xpath("//label[@for='irrevocable']/following::div[3]")).click();
        $(By.xpath("//label[@for='isPublic']/following::div[3]")).click();
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        //Добавление купона
       /* $(By.xpath("//label[@for='couponsData']/following::div[3]")).click();
        $(By.cssSelector("span[class*='create__coupons']")).click();
        $(By.cssSelector("div[class*='select__item']")).click();
        $(By.cssSelector("input[class*='select__quantity']")).setValue("1");
        $(By.cssSelector("input[class*='submit']")).click();*/
        $(By.cssSelector("input[class*='submit']")).click();

        /*Блок проверок*/
        //1. Проверка наличия созданного оффера в разделе "Мои предложения"
        business.searchOffer(OFFER_NAME_GENERAL);
        //2. Проверка того, что оффер виден пользователям
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        consumer.searchOfferByName(OFFER_NAME_GENERAL);
    }
}
