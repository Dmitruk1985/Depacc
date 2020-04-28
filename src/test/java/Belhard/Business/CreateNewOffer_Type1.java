package Belhard.Business;

import Belhard.BusinessMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateNewOffer_Type1 {
    @Test
    public void createNewOffer() {
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/offer/create");
        //загрузка изображения офера
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue(OFFER_IMAGE_1);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue(DEFAULT_OFFER_NAME);
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
        //Проверка отображения кнопки главного меню
        BUTTON_MENU_BUSINESS.shouldBe(Condition.exist);
    }
}
