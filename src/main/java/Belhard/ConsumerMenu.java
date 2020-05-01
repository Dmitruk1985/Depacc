package Belhard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class ConsumerMenu {

    public static final String URL_CONSUMER_SIGNIN = "https://depacc-front-dev.herokuapp.com/consumer/signIn";
    public static final String URL_CONSUMER_SIGNUP = "https://depacc-front-dev.herokuapp.com/consumer/signUp";
    public static final String URL_LANDING = "https://depacc-front-dev.herokuapp.com";
    public static final String EMAIL_CONSUMER = "automation.testing.depacc@gmail.com";
    public static final String EMAIL_CONSUMER_TEST = "automation.testing.depacc+consumer1@gmail.com";
    public static final String EMAIL_CONSUMER_TEST_2 = "automation.testing.depacc+consumer2@gmail.com";
    public static final double CHARGE_AMOUNT = 0.01;
    public static final double TRANSFER_AMOUNT = 0.01;
    public static final String DEFAULT_OFFER = "Automatic offer Type 1";
    public static final String CONSUMER_NAME = "Automatic User";
    public static final String CONSUMER_TEST_NAME = "Automatic Auxiliary User";
    public static final String PASSWORD = "Qwerty1234567";
    public static final SelenideElement BUTTON_MENU_CONSUMER = $(By.xpath("//section[contains(@class, 'header__desktop')]//button[contains(@class, 'profile-consumer')]"));
    public static final SelenideElement MODAL_BUTTON = $(By.cssSelector("input[class='modal__btn ']"));
    public static final SelenideElement SUBMIT_BUTTON = $(By.cssSelector("input[class*='submit']"));
    public static final ElementsCollection CURRENCIES = $$(By.xpath("//section[contains(@class, 'header__desktop')]//li[@class='profile-info__amount']"));
    public static final String NO_OPEN_DEPOSITE = "Отсутствует открытый депозит";
    public static final String NO_OPERATIONS = "Операций пока нет";
    public static final String NO_DEPACCS = "Депаков пока нет";
    public static final String NO_COUPONS = "Купонов не найдено";
    public static final String NO_MESSAGES = "Новых уведомлений нет, вернуться на страницу предложений";

    /*Блок названий записей в разделе "История операций"*/
    public static final String HISTORY_BUY_OFFER_AS_CLIENT = "Оплаченная оферта";
    public static final String HISTORY_BUY_OFFER_AS_EMPLOYEE = "Оферта в долг";
    public static final String HISTORY_COUPON_TRANSFER = "Трансфер купона";
    public static final String HISTORY_DEPACC_TRANSFER = "Трансфер оферты";
    public static final String HISTORY_COUPON_RELEASE = "Использование купона";

    /*Блок названий записей в разделе "Сообщения"*/
    public static final String MESSAGE_NEW_DEPACC = "Вы открыли новый депак";
    public static final String MESSAGE_COUPON_TRANSFER = "Вы получили трансфер купона";
    public static final String MESSAGE_COUPON_RELEASE = "успешно реализован";
    public static final String MESSAGE_DEPACC_TRANSFER = "Вы получили трансфер";

    /*Блок названий в уведомлениях на почте*/
    public static final String MAIL_CONFIRMATION_TITLE = "Подтверждение регистрации";
    public static final String MAIL_CONFIRMATION_LINK = "Подтвердить мой аккаунт";
    public static final String MAIL_NEW_DEPACC = "Новый депак";
    public static final String MAIL_COUPON_RELEASE = "Реализация купона";
    public static final String MAIL_PASSWORD_RECOVERY = "Восстановление пароля";
    public static final String MAIL_CHANGE_PASSWORD = "Изменить пароль";
    public static final String MAIL_DEPACC_TRANSFER = "Трансфер депозита";
    public static final String MAIL_PAYMENT_TITLE = "Уведомление об успешной оплате";
    public static final String MAIL_PAYMENT_MESSAGE = "Ваш платеж был успешным";

    /**
     * Блок функций входа и выхода из аккаунта
     **/
    /*Вход в аккаунт с данными по умолчанию*/
    public void loginByDefault() {
        Configuration.baseUrl = "https://depacc-front-dev.herokuapp.com/consumer";
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(EMAIL_CONSUMER);
        $(By.id("password")).setValue(PASSWORD).pressEnter();
    }

    /*Вход в аккаунт с определенными данными*/
    public void loginConsumerByData(String s1, String s2) {
        Configuration.baseUrl = "https://depacc-front-dev.herokuapp.com/consumer";
        open(URL_CONSUMER_SIGNIN);
        $(By.id("email")).setValue(s1);
        $(By.id("password")).setValue(s2).pressEnter();
    }

    /*Логин через Google*/
    public void loginByGoogle() {
        open(URL_CONSUMER_SIGNIN);
        $(By.cssSelector("img[alt='google']")).click();
        $(By.id("identifierId")).setValue(EMAIL_CONSUMER).pressEnter();
        $(By.name("password")).setValue(PASSWORD).pressEnter();
    }

    /*Логин через vk.com*/
    public void loginByVK() {
        open(URL_CONSUMER_SIGNIN);
        $(By.cssSelector("img[alt='vk']")).click();
        $(By.name("email")).setValue(EMAIL_CONSUMER);
        $(By.name("pass")).setValue("QWerty1234567").pressEnter();
    }

    /*Выход из аккаунта*/
    public void signOut() {
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Выход']")).click();
        $(By.cssSelector("button[class*='primary']")).click();
    }

    /*Блок открытия определнных разделов меню*/
    public void openOffers() {
        $(By.cssSelector("input[value='Предложения']")).click();
    }

    public void openMyDepaccs() {
        $(By.cssSelector("input[value='Мои депаки']")).click();
    }

    public void openMyCoupons() {
        $(By.cssSelector("input[value='Мои купоны']")).click();
    }

    public void openMessages() {
        $(By.cssSelector("input[class*='notifications']")).click();
    }

    public void openMyProfile() {
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Мой профиль']")).click();
    }

    public void openOperationsHistory() {
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='История операций']")).click();
    }

    public void openSettings() {
        BUTTON_MENU_CONSUMER.click();
        $(By.xpath("//section[contains(@class, 'header__desktop')]//input[@value='Настройки']")).click();
    }


    /*Округление дробного числа до двух знаков*/
    public double roundDouble(double d) {
        d = d * 100;
        int i = (int) Math.round(d);
        return (double) i / 100;
    }


    public void setCouponsFilter(String name) {
        $(By.cssSelector("button[class*='filter']")).click();
        $(By.cssSelector("button[class='filters__btn-top']")).click();
        $(By.xpath("//li[contains(text(), '" + name + "')]")).click();
    }


    /*Открытие оффера по названию (без использования поля "Поиск")*/
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

    public void searchDepacc(String s) {
        $(By.cssSelector("input[type='search']")).setValue(s);
        sleep(1000);
        $(By.cssSelector("button[class*='depacc-link']")).click();
    }

    /*УСТАРЕВАШАЯ ФУНКЦИЯ (ИЗМЕНЕН ДИЗАЙН ДЕПАКОВ) Открытие депака по названию (без использования поля "Поиск")*/
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
    public void confirmRegistration() {
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject(MAIL_CONFIRMATION_TITLE);
        $(By.xpath("//a[contains(text(), '" + MAIL_CONFIRMATION_LINK + "')]")).click();
        switchTo().window(0);
        //Удаление всех писем (для корректной работы все лишние письма должны удаляться)
        gmail.deteleAllEmails();
        switchTo().window(1);
    }

    /*Блок получения данных*/
    /*Получение стоимости оффера*/
    public double getOfferAmmount() {
        sleep(1000);
        double amount = roundDouble(Double.parseDouble($(By.cssSelector("span[class='offer-current__amount']")).innerText()));
        System.out.println("Стоимость оффера = " + amount);
        return amount;
    }

    /*Получение валюты оффера*/
    public String getOfferCurrency() {
        String currency = ($(By.cssSelector("span[class*='currency']")).innerText());
        System.out.println("Валюта оффера = " + currency);
        return currency;
    }

    /*Получение суммы депака*/
    public double getDepaccAmmount() {
        sleep(1000);
        double amount = Double.parseDouble($(By.cssSelector("span[class='offer-current-accepted__amount']")).innerText());
        System.out.println("Сумма депака = " + amount);
        return roundDouble(amount);
    }

    /*Получение валюты депака*/
    public String getDepaccCurrency() {
        String currency = ($(By.cssSelector("span[class*='currency']")).innerText());
        System.out.println("Валюта депака = " + currency);
        return currency;
    }

    /*Получение суммы баланса определенной валюты*/
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
        return roundDouble(balance);
    }

    /*Получение суммы баланса всех доступных валют*/
    public double[] getTotalBalance() {
        BUTTON_MENU_CONSUMER.click();
        sleep(1000);
        int size = CURRENCIES.size();
        System.out.println("Количество валют = " + size);
        double[] balance = new double[size];
        String[] currency = new String[size];
        for (int i = 0; i < size; i++) {
            String total = $(By.cssSelector("li[class='profile-info__amount']"), i).innerText();
            balance[i] = roundDouble(Double.parseDouble(total.substring(0, total.indexOf(' '))));
            currency[i] = total.substring(total.indexOf(' ') + 1);
            System.out.println(balance[i] + currency[i]);
        }
        BUTTON_MENU_CONSUMER.click();
        return balance;
    }

    /*Получение индекса нужной валюты*/
    public int getCurrencyIndex(String currency) {
        int size = CURRENCIES.size();
        String[] currency2 = new String[size];
        int index = 0;
        for (int i = 1; i < size; i++) {
            String total = $(By.cssSelector("li[class='profile-info__amount']"), i).innerText();
            currency2[i] = total.substring(total.indexOf(' ') + 1);
            if (currency2[i] == currency) {
                index = i;
            }
        }
        System.out.println("Индекс валюты = "+index);
        return(index);
    }


    /*Получение текущенй даты в формате дд.мм.гггг*/
    public String getDate() {
        Date dateGeneral = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(dateGeneral);
        return (date);
    }

    /*Блок проверок*/
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

    //Проверка раздела "Сообщения"
    public void checkMessages(String title, String date, double amount, String currency) {
        openMessages();
        //1. Проверка заголовка
        $(By.cssSelector("p[class='notification-item__message']")).shouldHave(Condition.text(title));
        //2. Проверка даты
        $(By.cssSelector("p[class*='date']")).shouldHave(Condition.text(date));
        //3. Проверка суммы (здесь осуществляется проверка, является ли сумма 0, дробным или целым числом)
        double frac = roundDouble(amount % 1);
        SelenideElement amountField = $(By.cssSelector("span[class*='notification-item__amount']"));
        if (amount == 0) {
            amountField.shouldNotBe(Condition.visible);
        } else {
            if (frac == 0.0) {
                String sub = String.valueOf(amount).substring(0, String.valueOf(amount).length() - 2);
                amountField.shouldHave(Condition.text(sub));
            } else {
                amountField.shouldHave(Condition.text(String.valueOf(amount)));
            }
        }
        //4. Проверка валюты
        $(By.cssSelector("p[class*='notification-item__amount-wrapper']")).shouldHave(Condition.text(currency));
    }

    //Проверка уведомлений на почте
    public void checkNotification(String title, String message) {
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject(title);
        //1. Проверка заголовка
        $(By.cssSelector("h2[class='hP']")).should(Condition.have(Condition.text(title)));
        //2. Проверка текста
        $(By.xpath("//p[contains(text(), '" + message + "')]")).shouldBe(Condition.visible);
        gmail.deteleAllEmails();
    }

    /*Проверка уведомления на почте без удаления писем*/
    public void checkSecondNotification(String title, String message) {
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject(title);
        //1. Проверка заголовка
        $(By.cssSelector("h2[class='hP']")).should(Condition.have(Condition.text(title)));
        //2. Проверка текста
        $(By.xpath("//*[contains(text(), '" + message + "')]")).shouldBe(Condition.visible);
    }

    /*Проверка на почте двух уведомлений в одной ветке (НЕ ВСЕГДА КОРРЕКТНО РАБОТАЕТ, 1. первое письмо может быть свернуто 2. функция удаления писем не работает)*/
    public void checkNotifications(String title, String message1, String message2) {
        Gmail gmail = new Gmail();
        gmail.login();
        gmail.openUnreadEmailBySubject(title);
      //  $(By.cssSelector("div[class='iA g6']"),1).click();
        //1. Проверка заголовка
        $(By.cssSelector("h2[class='hP']")).should(Condition.have(Condition.text(title)));
        //2. Проверка текстов
        $(By.xpath("//p[contains(text(), '" + message1 + "')]")).shouldBe(Condition.visible);
        $(By.xpath("//p[contains(text(), '" + message2 + "')]")).shouldBe(Condition.visible);
        gmail.deteleAllEmails();
    }

    /*Проверка того, что баланс валют, кроме выбранной, остался неизменным*/
    public void checkOtherCurrencies(int i, double[] currency1,double[] currency2){
        int size = CURRENCIES.size();
        for (int j = 0; j < size; j++) {
            if (i!=j) {
                Assertions.assertEquals(0, (currency1[i] - currency2[i]));
            }
        }
    }

    /*Проверка того, что баланс валюты с заданным индексом увеличился на значение amount, а баланс остальных остался неизменным*/
    public void checkCurrencies(int i, double amount, double[] currency1,double[] currency2){
        int size = CURRENCIES.size();
        for (int j = 0; j < size; j++) {
            if (i!=j) {
                Assertions.assertEquals(0, (currency1[i] - currency2[i]));
            }
            else{
                Assertions.assertEquals((roundDouble(currency1[i] + amount)), currency2);
            }
        }
    }

    /*Проверка имени пользователя в выпадающем меню (может быть ФИО или емейл)*/
    public void checkUserName(String name) {
        BUTTON_MENU_CONSUMER.click();
        $(By.className("profile-info__name-content"), 1).shouldHave(Condition.text(name));
    }


}
