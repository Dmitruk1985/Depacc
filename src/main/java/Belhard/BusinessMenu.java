package Belhard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.*;

public class BusinessMenu {

    public static final String URL_BUSINESS_SIGNIN = "https://depacc-front-dev.herokuapp.com/business/signIn";
    public static final String EMAIL_BUSINESS = "automation.testing.depacc+business@gmail.com";
    public static final String BUSINESS_LOGO = "D:\\Automationtesting\\logo_business.jpg";
    public static final String BUSINESS_LOGO_NEW = "D:\\Automationtesting\\logo_business_2.jpg";
    public static final String OFFER_IMAGE_1 = "D:\\Automationtesting\\offer.jpg";
    public static final String OFFER_IMAGE_2 = "D:\\Automationtesting\\offer2.jpg";
    public static final String OFFER_IMAGE_EMPLOYEE = "D:\\Automationtesting\\offer_employee.jpg";
    public static final String OFFER_NAME_GENERAL = "Automatic offer Genaral";
    public static final String OFFER_NAME_BOOKABLE = "Automatic offer Bookable";
    public static final String OFFER_NAME_EMPLOYEE = "Automatic offer Employee";
    public static final String BUSINESS_NAME = "Automatic Business";
    public static final String EMPLOYEE_NAME = "Automatic Employee";
    public static final SelenideElement BUTTON_MENU_BUSINESS = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-business')]"));
    public static final SelenideElement BUTTON_ACCEPT_PAYMENT = $(By.cssSelector("button[class*='page-btn-primary']"));

    public static final String MAIL_NEW_DEPACC_BUSINESS = "Открыт новый Депак";
    public static final String HISTORY_NEW_DEPACC_BUSINESS = "Оплаченная оферта";

    /**
     * Блок функций входа и выхода из аккаунта
     **/
    /*Вход в аккаунт с данными по умолчанию*/
    public void login() {
        Configuration.baseUrl = "https://depacc-front-dev.herokuapp.com/business";
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(EMAIL_BUSINESS);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
        sleep(1000);
    }

    /*Вход в аккаунт с определенными данными*/
    public void loginBusinessByData(String s1, String s2) {
        Configuration.baseUrl = "https://depacc-front-dev.herokuapp.com/business";
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }

    /*Выход из аккаунта*/
    public void signOut() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Выход']")).click();
        $(By.cssSelector("button[class*='primary']")).click();
    }

    /*Округление дробного числа до двух знаков*/
    public double roundDouble(double d) {
        d = d * 100;
        int i = (int) Math.round(d);
        return (double) i / 100;
    }

    /**
     * Блок открытия разделов меню
     **/
    public void openCompanyProfile() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Профиль компании']")).click();
    }

    public void openMyOffers() {
        $(By.cssSelector("input[value='Мои предложения']")).click();
    }

    public void openDepaccs() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Депаки клиентов']")).click();
    }

    public void openEmployees() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Сотрудники']")).click();
    }

    public void openClients() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Клиенты']")).click();
    }

    public void openOperationsHistory() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='История операций']")).click();
    }

    public void openSettings() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Настройки']")).click();
    }

    public void openQR() {
        $(By.cssSelector("input[value='Мои QR']")).click();
    }

    public void openMessages() {
        $(By.cssSelector("input[value='Сообщения']")).click();
    }

    /*Блок поиска элементов*/
    public void searchOffer(String s) {
        openMyOffers();
        $(By.cssSelector("input[type='search']")).setValue(s);
    }

    public void searchDepacc(String s) {
        openDepaccs();
        $(By.cssSelector("input[type='search']")).setValue(s);
    }

    /*Изменение логотипа*/
    public void changeLogo() {
        openCompanyProfile();
        $(By.cssSelector("span[class*='edit']")).click();
        $(By.name("newImage")).setValue(BUSINESS_LOGO_NEW);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.cssSelector("input[class*='submit']")).click();
    }

    /**
     * Блок проверок
     **/
    //Проверка раздела "История операций"
    public void checkOperationsHistory(String title, String sender, String recipient, String date, double amount, String currency) {
        openOperationsHistory();
        //1. Проверка названия операции
        $(By.cssSelector("span[class*='transaction-item__title']")).shouldHave(Condition.text(title));
        //2. Проверка отправителя
        $(By.cssSelector("span[class='transaction-item__name']")).shouldHave(Condition.text(sender));
        //3. Проверка получателя
        $(By.cssSelector("span[class='transaction-item__name']"), 1).shouldHave(Condition.text(recipient));
        //4. Проверка даты
        $(By.cssSelector("span[class*='date']")).shouldHave(Condition.text(date));
        //5. Проверка суммы
        double frac = roundDouble(amount % 1);
        if (frac == 0.0) {
            String sub = String.valueOf(amount).substring(0, String.valueOf(amount).length() - 2);
            $(By.cssSelector("span[class*='item__amount']")).shouldHave(Condition.text(sub));
        } else {
            $(By.cssSelector("span[class*='item__amount']")).shouldHave(Condition.text(String.valueOf(amount)));
        }
        //6. Проверка валюты
        $(By.cssSelector("span[class*='item__amount']")).shouldHave(Condition.text(currency));
    }
}
