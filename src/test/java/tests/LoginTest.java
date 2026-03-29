package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        LoginPage login = new LoginPage(driver);
        login.clickLogin();
        login.login("test123@gmail.com", "123456");
    }
}