package Belhard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.EMAIL_CONSUMER;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class Gmail {

    /*Открыть Входящие*/
    public void openInbox() {
        $(By.cssSelector("div[class='aio UKr6le']")).click();
    }

    /*Вход в аккаунт*/
    public void login() {
        open("https://mail.google.com");
        SelenideElement signIn = $(By.xpath("//li[@class='h-c-header__nav-li g-mail-nav-links']/a[@class='h-c-header__nav-li-link ' and contains(text(),'Войти')]"));
       //Иногда при отрытии сайта может открываться другая страница, в этом случае нужно нажать на кнопку.
        if (signIn.isDisplayed()) {
            signIn.click();
            switchTo().window(1);
        }
        $(By.id("identifierId")).setValue(EMAIL_CONSUMER).pressEnter();
        $(By.name("password")).setValue(PASSWORD).pressEnter();
        openInbox();
    }

    /*Открыть непрочитанное письмо по теме*/
    public void openUnreadEmailBySubject(String s) {
        $(By.xpath("//span[@class='bog']/span[contains(text(), '" + s + "') and contains(@class, 'bqe')]")).waitUntil(Condition.exist, 10000).click();
    }

    /*Удалить все письма*/
    public void deteleAllEmails() {
        openInbox();
        $(By.cssSelector("span[class*='T-Jo J-J5-Ji']")).click();
        $(By.cssSelector("div[class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")).click();
    }

    /*Удалить все письма с заданной темой*/
    public void deteleAllEmailsBySubject(String s) {
        openInbox();
        String locator = "//span[contains(@class, 'bog')]/span[contains(text(), '" + s + "')]/ancestor::td/preceding-sibling::td/div[contains(@role, 'checkbox')]";
        int size = $$(By.xpath(locator)).size();
        for (int i = 0; i < size; i++) {
            $(By.xpath(locator), i).click();
        }
        $(By.cssSelector("div[class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")).click();
    }


}
