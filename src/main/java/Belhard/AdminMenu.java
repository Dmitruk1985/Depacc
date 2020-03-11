package Belhard;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static Belhard.ConsumerMenu.PASSWORD;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AdminMenu {

    public static final String URL_ADMIN = "https://depacc-front-dev.herokuapp.com/admin/signIn";
    public static final String EMAIL_ADMIN = "automation.testing.depacc+admin@gmail.com";


    public void loginAdmin() {
        Configuration.baseUrl="https://depacc-front-dev.herokuapp.com/admin";
        open(URL_ADMIN);
        $(By.id("email")).setValue(EMAIL_ADMIN);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }
}
