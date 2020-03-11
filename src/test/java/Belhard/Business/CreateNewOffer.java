package Belhard.Business;

import Belhard.BusinessMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CreateNewOffer {
    @Test
    public void createNewOffer() {
        BusinessMenu business = new BusinessMenu();
        business.loginBusiness();
        open("https://depacc-front-dev.herokuapp.com/business/offer/create");
        //загрузка изображения офера
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue("D:\\Automationtesting\\offer.jpg");
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue("Automatic offer Type 1");
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.id("description")).setValue("This offer is created by automatic software. #BYN #Free #No automatic return #Available to all #Coupons 1(1)");
        $(By.id("amount")).setValue("99.99");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.id("designTypeMarker")).selectOptionContainingText("Бесплатно");
        $(By.xpath("//label[@for='irrevocable']/following::div[3]")).click();
        $(By.xpath("//label[@for='isPublic']/following::div[3]")).click();
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        //Добавление купона
        $(By.xpath("//label[@for='couponsData']/following::div[3]")).click();
        $(By.cssSelector("span[class*='create__coupons']")).click();
        $(By.cssSelector("div[class*='select__item']")).click();
        $(By.cssSelector("input[class*='select__quantity']")).setValue("1");
        $(By.cssSelector("input[class*='submit']")).click();
        $(By.cssSelector("input[class*='submit']")).click();
        //Проверка отображения кнопки главного меню
       $(By.cssSelector("button[class*='profile-business']")).shouldBe(Condition.exist);
    }
}
