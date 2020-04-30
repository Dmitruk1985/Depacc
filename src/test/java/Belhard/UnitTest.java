package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static Belhard.BusinessMenu.MAIL_NEW_DEPACC_BUSINESS;
import static Belhard.ConsumerMenu.*;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.checkNotifications(MAIL_NEW_DEPACC,MAIL_NEW_DEPACC_BUSINESS,MESSAGE_NEW_DEPACC);


    }
}
