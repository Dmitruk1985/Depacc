package Belhard.Business;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.EMPLOYEE_NAME;
import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddNewEmployeeTest {
    @Test
    public void addEmployee() {
      Configuration.holdBrowserOpen = true;
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/employees/create");
        $(By.id("firstName")).setValue("Employee");
        $(By.id("lastName")).setValue("Automatic");
        String email = "automation.testing.depacc+employee" + (int) (Math.random() * 100000) + "@gmail.com";
        System.out.println(email);
        $(By.id("email")).setValue(email);
        $(By.id("position")).setValue("QA Engineer");
        $(By.id("currency")).selectOptionContainingText("BYN");
        $(By.cssSelector("input[class*='calendar']")).setValue("01.01.1985");
        $(By.cssSelector("span[class*='toggle__inner']")).click();
        $(By.cssSelector("span[class*='toggle__inner']"), 1).click();
        $(By.cssSelector("input[type='submit']")).click();
        //Подтверждение регистрации
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.confirmRegistration();
        consumer.confirmPassword();
        consumer.loginByData(email,PASSWORD);
      double[] balance = consumer.getTotalBalance();
        BUTTON_MENU_CONSUMER.click();

      /*Блок проверок*/
      //1. Проверка выпадающего меню
      $(By.className("profile-info__name-content"),1).shouldHave(Condition.text(EMPLOYEE_NAME));
      Assertions.assertEquals(balance[0],0);
     //2. Проверка профиля
      BUTTON_MENU_CONSUMER.click();
      consumer.openMyProfile();
      $(By.className("profile__data-content")).shouldHave(Condition.text(email));
      $(By.className("profile__balance-item")).shouldHave(Condition.text("0 BYN"));
      //3. Проверка истории операций
      consumer.openOperationsHistory();
      $(By.className("transaction-history__empty-title")).shouldHave(Condition.text(NO_OPERATIONS));
      //4. Проверка депаков
      consumer.openMyDepaccs();
      $(By.className("offers-accepted__empty-title")).shouldHave(Condition.text(NO_DEPACCS));
      //5. Проверка купонов
      consumer.openMyCoupons();
      $(By.className("consumer-coupons__message")).shouldHave(Condition.text(NO_COUPONS));
      //6. Проверка сообщений
      consumer.openMessages();
      $(By.className("notifications__empty-title")).shouldHave(Condition.text(NO_MESSAGES));
    }
}
