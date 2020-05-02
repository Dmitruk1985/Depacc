package Belhard.Business;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewOffer_EmployeeTest {
    @Test
    public void createNewOffer() {
        Configuration.holdBrowserOpen = true;
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/offer/create");
        $(By.cssSelector("button[class*='offer-create']")).click();
        $(By.name("newImage")).setValue(OFFER_IMAGE_EMPLOYEE);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("title")).setValue(OFFER_NAME_EMPLOYEE);
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.cssSelector("div[class*='ql-editor']")).setValue("This offer is created by automatic software.");
        $(By.id("amount")).setValue("15");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        $(By.cssSelector("input[class*='submit']")).click();

        /*Блок проверок*/
        //1. Проверка наличия созданного оффера в разделе "Мои предложения"
        business.searchOffer(OFFER_NAME_EMPLOYEE);
        //2. Проверка того, что оффер виден пользователям
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        consumer.searchOfferByName(OFFER_NAME_EMPLOYEE);
       
    }
}
