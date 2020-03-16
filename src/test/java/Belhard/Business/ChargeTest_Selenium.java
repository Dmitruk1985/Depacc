package Belhard.Business;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Belhard.BusinessMenu.EMAIL_BUSINESS;
import static Belhard.BusinessMenu.URL_BUSINESS_SIGNIN;
import static Belhard.ConsumerMenu.*;


public class ChargeTest_Selenium {
    @Test
    public void charge() {

        System.setProperty("webdriver.chrome.driver", "c://Users/User/Depacc/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1600, 1024));
        /*Вход за пользователя*/
        driver.get(URL_CONSUMER_SIGNIN);
        driver.findElement(By.id("email")).sendKeys(EMAIL_CONSUMER);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        /*Вход за бизнес*/
        driver.get(URL_BUSINESS_SIGNIN);
        driver.findElement(By.id("email")).sendKeys(EMAIL_BUSINESS);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        //Нажатие кнопки "Принять платеж"
        driver.findElement(By.cssSelector("button[class*='page-btn-primary']")).click();

        /*Здесь нужно отсканировать QR-код*/

        driver.findElement(By.cssSelector("input[class='modal__btn']")).click();
        //Поиск депака DEFAULT_OFFER
        WebElement offer = driver.findElement(By.xpath("//h2[contains(text(), '" + DEFAULT_OFFER + "')]/parent::div/preceding-sibling::div"));
        List<WebElement> offers = driver.findElements(By.cssSelector("div[class*='photo-wrapper']"));
        for (int i = 0, j = 1; i < j; i++, j++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                    , offers.get(i));
            if (offer.isDisplayed()) {
                offer.click();
                break;
            }
        }
        //Ввод суммы CHARGE_AMOUNT
        driver.findElement(By.id("amount")).sendKeys(String.valueOf(CHARGE_AMOUNT));
        driver.findElement(By.cssSelector("input[class*='submit']")).click();
        //Подтверждение операции у пользователя
        driver.switchTo().window(tabs.get(0));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Ошибка");
        }
        driver.findElement(By.cssSelector("input[class*='submit']")).click();
        driver.findElement(By.className("modal__btn")).click();
        /*Проверка истории операций пользователя*/
        driver.findElement(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-consumer')]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Ошибка");
        }
        driver.findElement(By.xpath("//section[contains(@class, 'header__desktop')]//input[contains(@value, 'История операций')]")).click();
        //Проверка наличия операции "Оплата"
        Assert.assertEquals("Оплата", driver.findElement(By.cssSelector("span[class*='transaction-name']")).getText());
        //Сумма оплаты должна равняться CHARGE_AMOUNT
        Assert.assertEquals(String.valueOf(CHARGE_AMOUNT), driver.findElement(By.cssSelector("span[class='transaction-item__name transaction-item__amount']")).getText());
        //Подтверждение операции у бизнеса
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.className("modal__btn")).click();
        /*Проверка истории операций бизнеса*/
        driver.get("https://depacc-front-dev.herokuapp.com/business/deposits/transactions");
        //Проверка наличия операции "Оплата"
        Assert.assertEquals("Оплата", driver.findElement(By.cssSelector("span[class*='transaction-name']")).getText());
        //Сумма оплаты должна равняться CHARGE_AMOUNT
        Assert.assertEquals(String.valueOf(CHARGE_AMOUNT), driver.findElement(By.cssSelector("span[class='transaction-item__name transaction-item__amount']")).getText());



    }
}
