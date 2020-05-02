package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewConsumerTest {

    @Test
    /*Регисстрация нового пользователя*/
    public void createNewConsumer() {
      //  Configuration.holdBrowserOpen = true;
        open(URL_CONSUMER_SIGNUP);
        String email = "automation.testing.depacc+consumer" + (int) (Math.random() * 10000000) + "@gmail.com";
        System.out.println(email);
        $(By.cssSelector("span[class*='checkbox']")).click();
        $(By.id("email")).setValue(email);
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("passwordConfirmation")).setValue(PASSWORD).pressEnter();
        //Подтверждение регистрации по ссылке на почте
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.confirmRegistration();
        $(By.cssSelector("button[class*='сonfirm-signup']")).click();
        MODAL_BUTTON.click();
        //Вход в приложение с созданным пользователем
        consumer.loginByData(email, PASSWORD);
        //Вход в аккаунт
        BUTTON_MENU_CONSUMER.click();

        /*Блок проверок*/
        //1. Проверка выпадающего меню
        $(By.className("profile-info__name-content"),1).shouldHave(Condition.text(email));
        $(By.className("profile-info__deposit"),1).shouldHave(Condition.text(NO_OPEN_DEPOSITE));
        //2. Проверка профиля
        BUTTON_MENU_CONSUMER.click();
        consumer.openMyProfile();
        $(By.className("profile__data-content")).shouldHave(Condition.text(email));
        $(By.className("profile__deposit-amount")).shouldHave(Condition.text(NO_OPEN_DEPOSITE));
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
