package Belhard.General;

import Belhard.BusinessMenu;
import Belhard.ConsumerMenu;
import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.URL_LANDING;
import static com.codeborne.selenide.Selenide.*;

/*Проверка работоспособности ссылкок в футере для всех ролей (гость/пользователь/бизнес)*/
public class FooterLinksTest {
    @Test
    public void footerLinksTest() {
        open(URL_LANDING);
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                ConsumerMenu consumer = new ConsumerMenu();
                consumer.loginConsumerByDefault();
            }
            if (i == 2) {
                BusinessMenu business = new BusinessMenu();
                business.login();
            }
            $(By.cssSelector("div[class*='offer__photo-wrapper']")).click();
            $(By.xpath("//span[contains(text(), 'О сервисе')]")).click();
            $(By.xpath("//h2[contains(text(), 'ИСПОЛЬЗОВАНИЕ СЕРВИСА')]")).should(Condition.exist);
            back();
            $(By.xpath("//span[contains(text(), 'Пользовательское соглашение')]")).click();
            $(By.xpath("//h1[contains(text(), 'ПОЛЬЗОВАТЕЛЬСКОЕ СОГЛАШЕНИЕ')]")).should(Condition.exist);
            back();
            $(By.xpath("//span[contains(text(), 'Политика конфиденциальности')]")).click();
            $(By.xpath("//h1[contains(text(), 'ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ')]")).should(Condition.exist);
            back();
            $(By.xpath("//span[contains(text(), 'Facebook')]")).click();
            switchTo().window(1);
            Assert.assertEquals("DepAcc - Главная | Facebook", title());
            closeWindow();
            switchTo().window(0);
        }
    }
}
