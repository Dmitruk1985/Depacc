package Belhard;

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
    public static final String DEFAULT_OFFER_NAME = "Automatic offer Type 1";
    public static final String BUSINESS_NAME = "Automatic Business";
    public static final SelenideElement BUTTON_MENU_BUSINESS = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-business')]"));
    public static final SelenideElement BUTTON_ACCEPT_PAYMENT = $(By.cssSelector("button[class*='page-btn-primary']"));

   /*Блок функций входа и выхода из аккаунта*/
    /*Вход в аккаунт с данными по умолчанию*/
    public void login() {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/business";
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(EMAIL_BUSINESS);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
        sleep(1000);
    }
/*Вход в аккаунт с определенными данными*/
    public void loginBusinessByData(String s1, String s2) {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/business";
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

     /**Блок открытия разделов меню**/
    public void openProfile() {
        BUTTON_MENU_BUSINESS.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Профиль компании']")).click();
    }

    public void openOffers() {
        $(By.cssSelector("input[value='Мои QR']")).click();
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

    public void openHistory() {
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
    public void searchOffer(String s){
       openOffers();
        $(By.cssSelector("input[type='search']")).setValue(s);
    }

    public void searchDepacc(String s){
        openDepaccs();
        $(By.cssSelector("input[type='search']")).setValue(s);
    }
}
