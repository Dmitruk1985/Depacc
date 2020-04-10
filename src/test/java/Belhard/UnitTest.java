package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;

        AdminMenu admin = new AdminMenu();
        admin.loginAdmin();
        admin.setBusinessShopByEmail("automation.testing.depacc+business9238@gmail.com");
      // admin.setBusinessShopByEmail("dmitry.dmitruk1985+b8@gmail.com");


    }
}
