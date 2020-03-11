package Belhard.Consumer;

import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.URL_CONSUMER_SIGNUP;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewConsumerTest {

    @Test
    public void createNewConsumer() {
        //Регисстрация нового пользователя
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
        $(By.cssSelector("input[class='modal__btn']")).click();
        //Вход в приложение с созданным пользователем
        consumer.loginConsumerByData(email, PASSWORD);
        //Проверка входа в аккаунт
        $(By.cssSelector("button[class*='profile-consumer']")).should(Condition.exist);

    }
}
