package Belhard.Consumer;

import Belhard.ConsumerMenu;
import Belhard.Gmail;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;


import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.*;

public class PasswordRecoveryTest {
    @Test
    public void passwordRecovery() {

        //Отправка запроса на восстановление пароля
        open(URL_CONSUMER_SIGNIN);
        $(By.cssSelector("input[class*='signin__restore']")).click();
        $(By.id("email")).setValue(EMAIL_CONSUMER_TEST).pressEnter();
        $(By.cssSelector("input[class='modal__btn']")).click();
        //Открытие письма со ссылкой на восстановление пароля
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject("Восстановление пароля");
        $(By.xpath("//a[contains(text(), 'Изменить пароль')]")).click();
        //Удаление письма со ссылкой на восстановление
        switchTo().window(0);
        gmail.deteleAllEmailsBySubject("Восстановление пароля");
        //Генерация и ввод нового пароля
        switchTo().window(1);
        String newPassword = ConsumerMenu.PASSWORD + (int) (Math.random() * 100);
        $(By.id("password")).setValue(newPassword);
        $(By.id("passwordConfirmation")).setValue(newPassword).pressEnter();
        $(By.cssSelector("input[class='modal__btn']")).click();
        //Вход с новым паролем
        $(By.id("email")).setValue(EMAIL_CONSUMER_TEST);
        $(By.id("password")).setValue(newPassword).pressEnter();
        //Проверка наличия кнопки главного меню Пользователя
        BUTTON_MENU_CONSUMER.should(Condition.exist);
    }
}
