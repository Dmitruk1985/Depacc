package Belhard;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AdminMenu {

    public static final String URL_ADMIN = "https://depacc-front-dev.herokuapp.com/admin/signIn";
    public static final String EMAIL_ADMIN = "automation.testing.depacc+admin@gmail.com";
    public static final SelenideElement MAIN_MENU_BUTTON = $(By.cssSelector("button[class='nav__toggle']"));
    public static final SelenideElement BUSINESSES_BUTTON = $(By.cssSelector("a[href='/admin/businesses']"));

    /*Вход в аккаунт*/
    public void loginAdmin() {
        Configuration.baseUrl = "https://depacc-front-dev.herokuapp.com/admin";
        open(URL_ADMIN);
        $(By.id("email")).setValue(EMAIL_ADMIN);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }


    /*Поиск бизнеса по емэйлу и его редактирование */
    public void editBusinessByEmail(String s) {
        MAIN_MENU_BUTTON.click();
        $(By.cssSelector("a[href='/admin/businesses']")).click();
        for (int i = 0, j = 1; i < j; i++, j++) {
            System.out.println($(By.xpath("//tr/td[1]"), i).innerText());
            if ($(By.xpath("//tbody//td[1]"), i).innerText().contains(s)) {
                $(By.xpath("//tbody//td[6]"), i).click();
                break;
            } else {
                $(By.className("admin__button-text")).click();
            }
        }
    }

    /*Поиск бизнеса по емэйлу и редактирование его магазина */
    public void editBusinessShopByEmail(String s) {
        MAIN_MENU_BUTTON.click();
        BUSINESSES_BUTTON.click();
        for (int i = 0, j = 1; i < j; i++, j++) {
            System.out.println($(By.xpath("//tbody//td[1]"), i).innerText());
            if ($(By.xpath("//tbody//td[1]"), i).innerText().contains(s)) {
                $(By.xpath("//tbody//td[5]"), i).click();
                break;
            } else {
                $(By.className("admin__button-text")).click();
            }
        }
    }

    public void setBusinessShopByEmail(String s) {
        editBusinessShopByEmail(s);
        $(By.id("name")).setValue("Shop name");
        $(By.id("shopId")).setValue("7326");
        $(By.id("secretKey")).setValue("aac7574041620ed14fa3bc80ea5da8ccd27e0b6e6dad8e35f7e08601dae1208c").pressEnter();
    }
}



