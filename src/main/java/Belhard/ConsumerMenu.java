package Belhard;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ConsumerMenu {

    public static final String URL_CONSUMER_SIGNIN = "https://depacc-front-dev.herokuapp.com/consumer/signIn";
    public static final String URL_CONSUMER_SIGNUP = "https://depacc-front-dev.herokuapp.com/consumer/signUp";
    public static final String EMAIL_CONSUMER = "automation.testing.depacc@gmail.com";
    public static final String EMAIL_CONSUMER_AUXILIARY = "automation.testing.depacc+consumer1@gmail.com";
    public static final double CHARGE_AMOUNT = 0.01;
    public static final double TRANSFER_AMOUNT = 0.01;
    public static final String DEFAULT_OFFER = "Automatic offer Type 1";
    public static final String PASSWORD = "Qwerty1234567";
    public static final SelenideElement BUTTON_MENU_CONSUMER = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-consumer')]"));

    /*Вход в аккаунт с данными по умолчанию*/
    public void loginConsumerByDefault() {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/consumer";
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(EMAIL_CONSUMER);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }
    /*Вход в аккаунт с определенными данными*/
    public void loginConsumerByData(String s1, String s2) {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/consumer";
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }

    /*Округление дробного числа до двух знаков*/
    double roundDouble (double d) {
        d = d*100;
        int i = (int) Math.round(d);
        return (double) i/100;
    }

    /*Получение суммы депака*/
    public double getDepaccAmmount() {
        sleep(500);
        double amount = Double.parseDouble($(By.cssSelector("span[class='offer-current-accepted__amount']")).innerText());
        return roundDouble (amount);
    }

    public double getBalance(String s) {
        $(By.cssSelector("button[class*='profile-consumer']"), 1).click();
        double balance = 0.0;
        int size = $$(By.cssSelector("li[class='profile-info__amount']")).size() / 2;
        for (int i = 0; i < size; i++) {
            String balance2 = $(By.cssSelector("li[class='profile-info__amount']"), i).innerText();
            if (balance2.contains(s)) {
                balance = Double.parseDouble(balance2.replace(" " + s, ""));
                break;
            }
        }
        return balance;
    }

    /*Получение суммы баланса всех доступных валют*/
    public double[] getTotalBalance() {
        $(By.cssSelector("button[class*='profile-consumer']"), 1).click();
        int size = $$(By.cssSelector("li[class='profile-info__amount']")).size() / 2;
        double[] balance = new double[size];
        for (int i = 0; i < size; i++) {
            String total = $(By.cssSelector("li[class='profile-info__amount']"), i).innerText();
            balance [i] = roundDouble(Double.parseDouble(total.substring(0,total.indexOf(' '))));
        }
        return balance;
    }

    /*Открытие оффера по названию*/
    public void openOfferByName(String s) {
        $(By.cssSelector("input[class*='menu__item-link']"), 0).click();
        SelenideElement offer = $(By.xpath("//h2[contains(text(), '" + s + "')]/parent::div/preceding-sibling::div"));
        for (int i = 0, j = 1; i < j; i++, j++) {
            $(By.cssSelector("div[class='offer__photo-wrapper offers__item-photo-wrapper']"), i).scrollTo();
            if (offer.isDisplayed()) {
                offer.click();
                break;
            }
        }
    }

    /*Открытие депака по названию*/
    public void openDepaccByName(String s) {
        $(By.cssSelector("input[class*='menu__item-link']"), 1).click();
        SelenideElement offer = $(By.xpath("//h2[contains(text(), '" + s + "')]/parent::div/preceding-sibling::div"));
        for (int i = 0, j = 1; i < j; i++, j++) {
            $(By.cssSelector("div[class='offer-accepted__photo-wrapper']"), i).scrollTo();
            if (offer.isDisplayed()) {
                offer.click();
                break;
            }
        }
    }
    /*Частичное подтверждение регистрации*/
    public void confirmRegistration(){
        //Подтверждение регистрации по ссылке на почте
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject("Инструкция подтверждения");
        $(By.xpath("//a[contains(text(), 'Подтвердить мой аккаунт')]")).click();
        //Удаление писем с подтверждением регистрации
        switchTo().window(0);
        gmail.deteleAllEmailsBySubject("Инструкция подтверждения");
        switchTo().window(1);
    }

    /**/
    public void singOut(){
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[contains(@value, 'Выход')]")).click();
        $(By.cssSelector("button[class*='primary']")).click();
    }
}
