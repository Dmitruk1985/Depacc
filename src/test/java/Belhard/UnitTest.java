package Belhard;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.sleep;


public class UnitTest {

    @Test
    public void test() {

        ConsumerMenu consumer = new ConsumerMenu();
        consumer.loginConsumerByDefault();

        sleep(3000);


    }
}
