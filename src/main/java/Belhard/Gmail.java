package Belhard;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.EMAIL_CONSUMER;
import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class Gmail {

    public void openInbox() {
        $(By.cssSelector("div[class='aio UKr6le']")).click();
    }

    public void login() {
        open("https://mail.google.com");
        $(By.id("identifierId")).setValue(EMAIL_CONSUMER).pressEnter();
        $(By.name("password")).setValue(PASSWORD).pressEnter();
        openInbox();
    }

    public void openUnreadEmailBySubject(String s){
        $(By.xpath("//span[@class='bog']/span[contains(text(), '"+s+"') and contains(@class, 'bqe')]")).waitUntil(Condition.exist,10000).click();
    }

    public void deteleAllEmails () {
        $(By.cssSelector("span[class*='T-Jo J-J5-Ji']")).click();
        $(By.cssSelector("div[class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")).click();
    }

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
