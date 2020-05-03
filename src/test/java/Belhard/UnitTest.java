package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        BusinessMenu business = new BusinessMenu();
        business.login();
        business.openMessages();
        ConsumerMenu consumer = new ConsumerMenu();
         String date = consumer.getDate();
        double d=21;
        business.checkCheque(date,d,"BYN");

    }
}
