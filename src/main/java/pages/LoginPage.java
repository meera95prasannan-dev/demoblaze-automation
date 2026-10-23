
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Login locators
    private By loginLink = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton =
            By.xpath("//button[text()='Log in']");

    // Logout locators
    private By logoutLink = By.id("logout2");
    private By welcomeMessage = By.id("nameofuser");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    // Open login form
    public void openLoginForm() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginLink)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField)
        );
    }

    // Enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click Login
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Verify user has logged in
    public boolean isLogoutDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            logoutLink)
            ).isDisplayed();

        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Click Logout
    public void clickLogout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loginLink)
        );
    }

    // Verify Login link is displayed after logout
    public boolean isLoginDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loginLink)
        ).isDisplayed();
    }
}
