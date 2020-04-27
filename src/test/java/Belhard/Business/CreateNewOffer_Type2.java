package Belhard.Business;

import Belhard.BusinessMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUTTON_MENU_BUSINESS;
import static Belhard.BusinessMenu.OFFER_IMAGE_2;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewOffer_Type2 {
    @Test
    public void createNewOffer() {
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/offer/create");
        //загрузка изображения офера
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue(OFFER_IMAGE_2);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue("Automatic offer Type 2");
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.id("description")).setValue("This offer is created by automatic software. #EUR #Available to all #Bookable");
        $(By.id("amount")).setValue("0.99");
        $(By.id("currency")).selectOptionContainingText("EUR");
        $(By.xpath("//label[@for='isPublic']/following::div[3]")).click();
        $(By.xpath("//label[@for='isBookable']/following::div[3]")).click();
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        $(By.cssSelector("input[class*='submit']")).click();
        //Проверка отображения кнопки главного меню
        BUTTON_MENU_BUSINESS.shouldBe(Condition.exist);
    }
}
