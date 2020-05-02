package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;

        open("https://mail.google.com");
        /*Gmail gmail = new Gmail();
        gmail.login();*/
        System.out.println(title());
    }
}
