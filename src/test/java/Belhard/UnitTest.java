package Belhard;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class UnitTest {

    @Test
    public void test() {
      /*  System.setProperty("webdriver.chrome.driver", "c://Users/User/Depacc/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://depacc-front-dev.herokuapp.com/consumer/signIn");

        ConsumerMenu consumer = new ConsumerMenu();
     consumer.loginConsumerByDefault();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.open()");*/
        Gmail gmail = new Gmail();
        gmail.login();
        //Наличие письма с темой "Трансфер"
        $(By.xpath("//span[@class='bog']/span[contains(text(), 'Трансфер') and contains(@class, 'bqe')]")).shouldBe(Condition.exist);
       // $(By.cssSelector("span[class='bx0']"),1).shouldHave(Condition.text("2"));
        gmail.openUnreadEmailBySubject("Трансфер");
        $(By.xpath("//p[contains(text(), 'Вы получили трансфер')]")).should(Condition.exist);
        $(By.xpath("//p[contains(text(), 'Трансфер депозита успешно осуществлен')]")).should(Condition.exist);

        sleep(3000);


    }
}
