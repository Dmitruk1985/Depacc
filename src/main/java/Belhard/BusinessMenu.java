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
    public static final SelenideElement BUTTON_MENU_BUSINESS = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-business')]"));

    /*Вход в аккаунт с данными по умолчанию*/
    public void loginBusiness() {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/business";
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(EMAIL_BUSINESS);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
        sleep(1000);
    }

    public void searchOffer(String s){
        open("https://depacc-front-dev.herokuapp.com/business/offers");
        $(By.cssSelector("input[type='search']")).setValue(s);
    }

    public void searchDepacc(String s){
        open("https://depacc-front-dev.herokuapp.com/business/offers_accepted");
        $(By.cssSelector("input[type='search']")).setValue(s);
    }

    public void loginBusinessByData(String s1, String s2) {
        open(URL_BUSINESS_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }
}
