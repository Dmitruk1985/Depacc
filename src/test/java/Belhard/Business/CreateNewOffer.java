package Belhard.Business;

import Belhard.Landing;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewOffer {
    @Test
    public void createNewOffer() {
        Landing landing = new Landing();
        landing.loginBusiness();
        $(By.cssSelector("a[class='offers-business__link-add-inc']")).click();
        $(By.cssSelector("button[class='offer-create__link']")).click();
        //загрузка изображения офера
        $(By.cssSelector("input[class='logo-editor__choose-input visually-hidden']")).setValue("D:\\Automationtesting\\offer.jpg");
        $(By.cssSelector("input[class*='logo-editor__choose-btn--save']")).click();
        $(By.id("title")).setValue("Automatic offer Type 1");
        $(By.id("category")).selectOptionContainingText("Automatic");
        $(By.id("description")).setValue("This offer is created by automatic software. #BYN #Free #No automatic return #Available to all #Coupons 1(1)");
        $(By.id("amount")).setValue("99.99");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.id("designTypeMarker")).selectOptionContainingText("Free");
        $(By.xpath("//label[@for='irrevocable']/following::div[3]")).click();
        $(By.xpath("//label[@for='isPublic']/following::div[3]")).click();
        $(By.xpath("//label[@for='enabled']/following::div[3]")).click();
        //Добавление купона
        $(By.xpath("//label[@for='couponsData']/following::div[3]")).click();
        $(By.cssSelector("span[class='offer-create__coupons-link']")).click();
        $(By.cssSelector("div[class='coupon-select__item-wrapper']")).click();
        $(By.cssSelector("input[class='coupon-select__quantity-input']")).setValue("1");
        $(By.cssSelector("input[class='page-btn-primary coupon-create__btn-submit']")).click();
        $(By.cssSelector("input[class='page-btn-primary offer-create__btn-submit']")).click();
        //Проверка отображения кнопки главного меню
        $(By.cssSelector("button[class='trigger trigger--profile-business']")).shouldBe(Condition.exist);
    }
}
