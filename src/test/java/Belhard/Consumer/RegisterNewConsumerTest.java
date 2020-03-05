package Belhard.Consumer;

import Belhard.Gmail;
import Belhard.Landing;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.Landing.PASSWORD;
import static Belhard.Landing.URL_CONSUMER_SIGNUP;
import static com.codeborne.selenide.Selenide.*;

public class RegisterNewConsumerTest {

    @Test
    public void regiterNewConsumer() {
        //Регисстрация нового пользователя
        open(URL_CONSUMER_SIGNUP);
        String email = "automation.testing.depacc+consumer" + (int) (Math.random() * 10000000) + "@gmail.com";
        System.out.println(email);
        $(By.cssSelector("span[class='form-group__checkbox-disagree']")).click();
        $(By.id("email")).setValue(email);
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("passwordConfirmation")).setValue(PASSWORD).pressEnter();
        //Подтверждение регистрации по ссылке на почте
        Gmail gmail = new Gmail();
        gmail.login();
        $(By.xpath("//span[@class='bog']/span[contains(text(), 'Инструкция подтверждения') and contains(@class, 'bqe')]")).shouldBe(Condition.enabled).click();
        $(By.xpath("//a[contains(text(), 'Подтвердить мой аккаунт')]")).click();
        switchTo().window(1);
        $(By.cssSelector("button[class='page-btn-primary сonfirm-signup__btn']")).click();
        $(By.cssSelector("input[class='modal__btn']")).click();
        //Удаление писем с подтверждением регистрации
        switchTo().window(0);
        gmail.deteleAllEmailsBySubject("Инструкция подтверждения");
        //Вход в приложение с созданным пользователем
        Landing landing = new Landing();
        landing.ConsumerLoginByData(email, PASSWORD);
        //Проверка наличия кнопки главного меню
        $(By.cssSelector("button[class*='profile-consumer']")).should(Condition.exist);

    }
}
