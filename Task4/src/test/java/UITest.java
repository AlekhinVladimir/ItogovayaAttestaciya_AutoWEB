import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UITest extends BaseUITest {

    @Test
    void testGBNotEmailLogin() {
        driver.get("https://gb.ru/login");
        LoginPage loginPage = new LoginPage(driver, wait); // передаем объект WebDriverWait
        loginPage.enterLogin("login");
        loginPage.enterPassword("password");
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.isErrorListPresent());
    }

    @Test
    void testGBWithoutPassword() {
        driver.get("https://gb.ru/login");
        LoginPage loginPage = new LoginPage(driver, wait); // передаем объект WebDriverWait
        loginPage.enterLogin("login@login.ru");
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.isErrorListPresent());
    }
}