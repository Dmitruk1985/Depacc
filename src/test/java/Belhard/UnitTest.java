package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static Belhard.ConsumerMenu.BUTTON_MENU_CONSUMER;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByGoogle();
        BUTTON_MENU_CONSUMER.click();

    }
}
