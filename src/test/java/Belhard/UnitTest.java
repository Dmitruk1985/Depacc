package Belhard;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.sleep;


public class UnitTest {

    @Test
    public void test() {

        Landing landing = new Landing();
        landing.ConsumerLoginByDefault();
        ConsumerMenu consumer = new ConsumerMenu();
        consumer.getBalance("BYN");

        sleep(3000);


    }
}
