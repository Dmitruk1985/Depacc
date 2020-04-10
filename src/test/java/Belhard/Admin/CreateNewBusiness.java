package Belhard.Admin;

import Belhard.AdminMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_LOGO;
import static Belhard.BusinessMenu.BUTTON_MENU_BUSINESS;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class CreateNewBusiness {


    @Test
    public void createNewBusiness() {
        Configuration.holdBrowserOpen = true;
        AdminMenu admin = new AdminMenu();
        admin.loginAdmin();
        $(By.cssSelector("a[href='/admin/businesses']")).click();
        $(By.cssSelector("a[href='/admin/businesses/create']")).click();
        int j = (int) (Math.random() * 10000);
        String email = "automation.testing.depacc+business" + j + "@gmail.com";
        System.out.println(email);
        $(By.cssSelector("button[class='admin__btn']")).click();
        $(By.name("newImage")).setValue(BUSINESS_LOGO);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.id("email")).setValue(email);
        $(By.id("legalName")).setValue("Automatic Business " + j);
        $(By.id("brandName")).setValue("Brand Automatic Business " + j);
        $(By.id("authority")).setValue("УНП 123456789");
        $(By.id("legalAddress")).setValue("г. Минск, ул. Мельникайте д.2, офис 1604");
        $(By.id("contactPhone")).setValue("+375290000000");
        $(By.id("contactEmail")).setValue("automation.testing.depacc+business@gmail.com");
        $(By.id("workingHours")).setValue("9:00-21:00");
        $(By.id("aboutUrl")).setValue("http://depacc.by");
        //Выбор чекбоксов с валютой
        int size = $$(By.cssSelector("input[name='checkbox-group']")).size();
        for (int i = 0; i < size; i++) {
            $(By.cssSelector("input[name='checkbox-group']"),i).click();
        }
        $(By.id("maxHoldingPeriod")).setValue("3");
        //Создание рабочей точки
        $(By.cssSelector("button[class*='shops']")).click();
        $(By.id("name")).setValue("Test Shop");
        $(By.id("address")).setValue("г. Минск, ул. Мельникайте 2, офис 1604");
        $(By.id("phone")).setValue("+375290000000");
        $(By.id("shopWorkingHours")).setValue("8:00-22:00");
        $(By.cssSelector("button[class*='resolve']")).click();
        sleep(1000);
        $(By.cssSelector("button[class*='save']")).click();
        $(By.cssSelector("input[type='submit']")).click();
        sleep(1000);
        //Заполнение данных магазина
        admin.setBusinessShopByEmail(email);
        //Подтверждение регистрации
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.confirmRegistration();
        $(By.cssSelector("span[class*='checkbox']")).click();
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("passwordConfirmation")).setValue(PASSWORD).pressEnter();
        $(By.cssSelector("input")).click();
        //Вход в созданный аккаунт
        $(By.id("email")).setValue(email);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
        BUTTON_MENU_BUSINESS.should(Condition.exist);

    }
}
