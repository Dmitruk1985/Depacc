package Belhard.Consumer;

import Belhard.ConsumerMenu;
import Belhard.Gmail;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.*;

public class PasswordRecoveryTest {
    @Test
    /*Восстановление пароля*/
    public void passwordRecovery() {
       // Configuration.holdBrowserOpen = true;
        //Отправка запроса на восстановление пароля
        open(URL_CONSUMER_SIGNIN);
        $(By.cssSelector("input[class*='signin__restore']")).click();
        $(By.id("email")).setValue(EMAIL_CONSUMER_TEST_2).pressEnter();
        MODAL_BUTTON.click();
        //Открытие письма со ссылкой на восстановление пароля
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject(MAIL_PASSWORD_RECOVERY);
        $(By.xpath("//a[contains(text(), '" + MAIL_CHANGE_PASSWORD + "')]")).click();
        //Удаление писем
        switchTo().window(0);
        gmail.deteleAllEmails();
        //Генерация и ввод нового пароля
        switchTo().window(1);
        String newPassword = PASSWORD + (int) (Math.random() * 1000);
        $(By.id("password")).setValue(newPassword);
        $(By.id("passwordConfirmation")).setValue(newPassword).pressEnter();
        MODAL_BUTTON.click();
        //Вход с новым паролем
        $(By.id("email")).setValue(EMAIL_CONSUMER_TEST_2);
        $(By.id("password")).setValue(newPassword).pressEnter();
        //Проверка входа в аккаунт
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.checkDrodMenu(EMAIL_CONSUMER_TEST_2);
    }
}
