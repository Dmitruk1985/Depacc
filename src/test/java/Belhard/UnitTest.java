package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static Belhard.BusinessMenu.BUSINESS_NAME;
import static Belhard.ConsumerMenu.CONSUMER_NAME;
import static Belhard.ConsumerMenu.HISTORY_BUY_OFFER_AS_EMPLOYEE;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginByDefault();
        consumer.openOperationsHistory();
        String date=consumer.getDate();
        consumer.checkOperationsHistory(HISTORY_BUY_OFFER_AS_EMPLOYEE, BUSINESS_NAME, CONSUMER_NAME, date, 10.0,"BYN");
    }
}
