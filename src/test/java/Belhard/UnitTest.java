package Belhard;

import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.Main.URL_LANDING;
import static com.codeborne.selenide.Selenide.*;


public class UnitTest {

    @Test
    public void test() {


        open(URL_LANDING);
        $(By.xpath("//span[contains(text(), 'Facebook')]")).click();
        switchTo().window(1);
        System.out.println(title());

        sleep(3000);


    }
}
