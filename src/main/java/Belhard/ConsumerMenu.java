package Belhard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ConsumerMenu {

    public static final String URL_CONSUMER_SIGNIN = "https://depacc-front-dev.herokuapp.com/consumer/signIn";
    public static final String URL_CONSUMER_SIGNUP = "https://depacc-front-dev.herokuapp.com/consumer/signUp";
    public static final String URL_LANDING = "https://depacc-front-dev.herokuapp.com";
    public static final String EMAIL_CONSUMER = "automation.testing.depacc@gmail.com";
    public static final String EMAIL_CONSUMER_AUXILIARY = "automation.testing.depacc+consumer1@gmail.com";
    public static final double CHARGE_AMOUNT = 0.01;
    public static final double TRANSFER_AMOUNT = 0.01;
    public static final String DEFAULT_OFFER = "Automatic offer Type 1";
    public static final String CONSUMER_NAME = "Automatic User";
    public static final String PASSWORD = "Qwerty1234567";
    public static final SelenideElement BUTTON_MENU_CONSUMER = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-consumer')]"));
 //   public static final int CURRENCY_QUANTITY = $$(By.xpath("//section[contains(@class, 'header__desktop')]//li[@class='profile-info__amount']")).size();
    public static final String BUY_OFFER_AS_EMPLOYEE = "Оферта в долг";

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

    public void openOperationHistory() {
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='История операций']")).click();
    }
    /*Округление дробного числа до двух знаков*/
    public double roundDouble (double d) {
        d = d*100;
        int i = (int) Math.round(d);
        return (double) i/100;
    }

    /*Получение стоимости оффера*/
    public double getOfferAmmount() {
        sleep(1000);
        double amount = roundDouble(Double.parseDouble($(By.cssSelector("span[class='offer-current__amount']")).innerText()));
        System.out.println("Стоимость оффера = "+amount);
        return amount;
    }

    /*Получение суммы депака*/
    public double getDepaccAmmount() {
        sleep(1000);
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
        BUTTON_MENU_CONSUMER.click();
        sleep(1000);
        int size = $$(By.cssSelector("li[class='profile-info__amount']")).size() / 2;
        System.out.println("Количество валют = "+size);
        double[] balance = new double[size];
        for (int i = 0; i < size; i++) {
            String total = $(By.cssSelector("li[class='profile-info__amount']"), i).innerText();
            balance [i] = roundDouble(Double.parseDouble(total.substring(0,total.indexOf(' '))));
            System.out.println(balance [i]);
        }
        return balance;
    }

    /*Открытие оффера по названию (без использование поля "Поиск")*/
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

    /*Поиск и открытие оффера по названию*/
    public void searchOfferByName(String s) {
        $(By.cssSelector("input[type='search']")).setValue(s);
        sleep(1000);
        $(By.cssSelector("div[class='offer__photo-wrapper offers__item-photo-wrapper']")).click();
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
        gmail.openUnreadEmailBySubject("Подтверждение регистрации");
        $(By.xpath("//a[contains(text(), 'Подтвердить мой аккаунт')]")).click();
        //Удаление всех писем (для корректной работы все лишние письма должны удаляться)
        switchTo().window(0);
        gmail.deteleAllEmails();
        switchTo().window(1);
    }

    public void checkOperationsHistory(String title, String sender, String recipient, double amount, String currency){
        open("/deposit_transactions");
        //Проверка названия операции
        $(By.cssSelector("span[class*='transaction-item__title']")).shouldHave(Condition.text(title));
        //Проверка отправителя операции
        $(By.cssSelector("span[class='transaction-item__name']")).shouldHave(Condition.text(sender));
        //Проверка получателя операции
        $(By.cssSelector("span[class='transaction-item__name']"),1).shouldHave(Condition.text(recipient));
        //Проверка суммы операции
        $(By.cssSelector("span[class*='item__amount']")).shouldHave(Condition.text(String.valueOf(amount)));
        //Проверка валюты операции
        $(By.cssSelector("span[class*='item__amount']")).shouldHave(Condition.text(currency));
    }

    /*Выход из аккаунта*/
    public void singOut(){
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[contains(@value, 'Выход')]")).click();
        $(By.cssSelector("button[class*='primary']")).click();
    }
}
