package Belhard;

import com.codeborne.selenide.Configuration;
import org.junit.Test;


public class UnitTest {

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
double d=0;
        String s = String.valueOf(d);
        String s2=s.substring(0,1);
        System.out.println(s2);


    }
}
