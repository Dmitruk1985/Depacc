package Belhard;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Landing {

    public static final String URL_CONSUMER_SIGNIN = "https://depacc-front-dev.herokuapp.com/consumer/signIn";
    public static final String URL_CONSUMER_SIGNUP = "https://depacc-front-dev.herokuapp.com/consumer/signUp";
    public static final String URL_BUSINESS_SIGNIN = "https://depacc-front-dev.herokuapp.com/business/signIn";
    public static final String URL_ADMIN = "https://depacc-front-dev.herokuapp.com/admin/signIn";
    public static final String EMAIL_CONSUMER = "automation.testing.depacc@gmail.com";
    public static final String EMAIL_CONSUMER_AUXILIARY = "automation.testing.depacc+consumer1@gmail.com";
    public static final String EMAIL_BUSINESS = "automation.testing.depacc+business@gmail.com";
    public static final String EMAIL_ADMIN = "automation.testing.depacc+admin@gmail.com";
    public static final String PASSWORD = "Qwerty1234567";

    public void ConsumerLoginByData(String s1, String s2) {
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }

    public void ConsumerLoginByDefault() {
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(EMAIL_CONSUMER);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }

    public void loginBusiness() {
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(EMAIL_BUSINESS);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }

    public void loginBusinessByData(String s1, String s2) {
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }

    public void loginAdmin() {
        open(URL_ADMIN);
        $(By.id("email")).setValue(EMAIL_ADMIN);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }

}
