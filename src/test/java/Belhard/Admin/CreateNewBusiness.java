package Belhard.Admin;

import Belhard.AdminMenu;
import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_LOGO;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class CreateNewBusiness {


    @Test
    public void createNewBusiness() {

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
        $(By.id("legalName")).setValue("Automatic Business "+j);
        $(By.id("authority")).setValue("Some requisites");
        $(By.id("legalAddress")).setValue("Some Address");
        $(By.id("contactPhone")).setValue("+375290000000");
        $(By.id("contactEmail")).setValue("automation.testing.depacc+business@gmail.com");
        $(By.id("workingHours")).setValue("9:00-21:00");
        $(By.id("aboutUrl")).setValue("http://depacc.by");
        //Выбор чекбоксов с валютой
        int size=$$(By.cssSelector("input[name='checkbox-group']")).size();
        for (int i = 0; i < size; i++) {
            $(By.id(String.valueOf(i))).click();
        }
        $(By.id("maxHoldingPeriod")).setValue("3");
        //Создание рабочей точки
        $(By.cssSelector("button[class*='shops']")).click();
        $(By.id("name")).setValue("Test Shop");
        $(By.id("address")).setValue("г. Минск, ул. Мельникайте 2");
        $(By.id("phone")).setValue("+375290000000");
        $(By.cssSelector("input[class*='form-group']"),16).setValue("8:00-22:00");
        $(By.cssSelector("button[class*='resolve']")).click();
        sleep(1000);
        $(By.cssSelector("button[class*='save']")).click();
        $(By.cssSelector("input[type='submit']")).click();
        //Подтверждение регистрации
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.confirmRegistration();
        $(By.cssSelector("span[class*='checkbox']")).click();
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("passwordConfirmation")).setValue(PASSWORD).pressEnter();
        //input[class='modal__btn']
        //Вход в созданный аккаунт
        BusinessMenu business = new BusinessMenu();
        business.loginBusinessByData(email,PASSWORD);
        $(By.cssSelector("button[class*='profile-business']")).should(Condition.exist);

        sleep(10000);
    }
}
