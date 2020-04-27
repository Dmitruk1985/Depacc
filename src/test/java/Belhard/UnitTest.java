package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();
        consumer.openMyDepaccs();
      consumer.searchDepacc("Депак");
      consumer.getDepaccCurrency();
    }
}
