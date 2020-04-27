package Belhard.Business;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.BUTTON_MENU_CONSUMER;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class AddEmployee {
    @Test
    public void addEmployee() {
      BusinessMenu business = new BusinessMenu();
        business.login();
        open("/employees/create");
        $(By.id("firstName")).setValue("Automatic");
        $(By.id("lastName")).setValue("Employee");
        String email = "automation.testing.depacc+employee" + (int) (Math.random() * 10000) + "@gmail.com";
        System.out.println(email);
        $(By.id("email")).setValue(email);
        $(By.id("position")).setValue("QA Engineer");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.cssSelector("input[class*='calendar']")).setValue("01.01.1985");
        $(By.cssSelector("span[class*='toggle__inner']")).click();
        $(By.cssSelector("span[class*='toggle__inner']"),1).click();
        $(By.cssSelector("input[type='submit']")).click();
        //Подтверждение регистрации
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.confirmRegistration();
        $(By.cssSelector("span[class*='checkbox']")).click();
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("passwordConfirmation")).setValue(PASSWORD).pressEnter();
        $(By.cssSelector("input[class='modal__btn']")).click();
        $(By.id("email")).setValue(email);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
        //Проверка наличия кнопки главного меню
      BUTTON_MENU_CONSUMER.should(Condition.exist);
    }
}
