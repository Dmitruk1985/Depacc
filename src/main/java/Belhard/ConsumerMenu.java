package Belhard;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ConsumerMenu {

    public double getBalanceBYN() {
        $(By.cssSelector("button[class*='profile-consumer']"), 1).click();
        double balance = Double.parseDouble($(By.cssSelector("li[class='profile-info__amount']")).innerText().replace(" BYN", ""));
        return balance;
    }

    public double getBalance(String s) {
        $(By.cssSelector("button[class*='profile-consumer']"), 1).click();
        Double balance = 0.0;
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


    public void menuNavigation(String s) {
        $(By.cssSelector("button[class='trigger trigger--profile-consumer']"), 1).click();
        sleep(1000);
        switch (s) {
            case "my profile":
                $(By.cssSelector("input[class='menu-profile__item-link']"), 5).click();
                break;
            case "my depaccs":
                $(By.cssSelector("input[class='menu-profile__item-link']"), 6).click();
                break;
            case "operations history":
                $(By.cssSelector("input[class='menu-profile__item-link']"), 7).click();
                break;
            case "settings":
                $(By.cssSelector("input[class='menu-profile__item-link']"), 8).click();
                break;
            case "sign out":
                $(By.cssSelector("input[class='menu-profile__item-link']"), 9).click();
                $(By.cssSelector("button[class='page-btn-primary signout__btn']")).click();
                break;
            default:
                System.out.println("Введено некорректное значение. Возможные варианты: 1)my profilе 2)my depaccs 3)operations history 4)settings 5)sign out ");
                break;
        }
    }
}
