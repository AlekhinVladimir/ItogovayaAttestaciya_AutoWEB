import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;// для возможного будущего использования
    private final WebDriverWait wait;

    private final By loginInput = By.cssSelector("input[name='login']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By loginButton = By.cssSelector("input[type='submit']");
    private final By errorList = By.cssSelector("div.form-error-list");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterLogin(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).sendKeys(login);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isErrorListPresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorList)) != null;
    }
}