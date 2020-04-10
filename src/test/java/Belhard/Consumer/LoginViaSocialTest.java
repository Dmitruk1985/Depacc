package Belhard.Consumer;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginViaSocialTest {
    @Test
    public void loginViaSocialTest() {
        /*Логин через Google*/
        open(URL_CONSUMER_SIGNIN);
        $(By.cssSelector("img[alt='google']")).click();
        $(By.id("identifierId")).setValue(EMAIL_CONSUMER).pressEnter();
        $(By.name("password")).setValue(PASSWORD).pressEnter();
        BUTTON_MENU_CONSUMER.click();
        Assert.assertEquals(EMAIL_CONSUMER, $(By.className("profile-info__name-content")).innerText());
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[contains(@value, 'Выход')]")).shouldBe(Condition.visible).click();
        $(By.cssSelector("button[class*='primary']")).click();

        /*Логин через vk.com*/
        $(By.cssSelector("img[alt='vk']")).click();
        $(By.name("email")).setValue(EMAIL_CONSUMER);
        $(By.name("pass")).setValue("QWerty1234567").pressEnter();
        BUTTON_MENU_CONSUMER.click();
        Assert.assertEquals(EMAIL_CONSUMER, $(By.className("profile-info__name-content")).innerText());
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[contains(@value, 'Выход')]")).shouldBe(Condition.visible).click();
        $(By.cssSelector("button[class*='primary']")).click();

    }
}
