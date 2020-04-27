package Belhard.Business;

import Belhard.BusinessMenu;
import org.junit.Test;
import org.openqa.selenium.By;

import static Belhard.BusinessMenu.BUSINESS_LOGO_NEW;
import static com.codeborne.selenide.Selenide.*;

public class ChangeLogo {
    @Test
    public void changeLogo() {
        BusinessMenu business = new BusinessMenu();
        business.login();
        open("/profile/edit");
        $(By.cssSelector("span[class*='edit']")).click();
        $(By.name("newImage")).setValue(BUSINESS_LOGO_NEW);
        $(By.cssSelector("input[class*='save']")).click();
        $(By.cssSelector("input[class*='submit']")).click();
    }
}
