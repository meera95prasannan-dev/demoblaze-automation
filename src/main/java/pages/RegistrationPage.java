
package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Registration form locators
    private By signUpLink = By.id("signin2");
    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By signUpButton =
            By.xpath("//button[normalize-space()='Sign up']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    // Open registration form
    public void openRegistrationForm() {
        wait.until(
                ExpectedConditions.elementToBeClickable(signUpLink)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField)
        );
    }

    // Enter username
    public void enterUsername(String username) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField)
        ).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click Sign up
    public void clickSignUp() {
        wait.until(
                ExpectedConditions.elementToBeClickable(signUpButton)
        ).click();
    }

    // Read and accept the registration result alert
    public String acceptRegistrationAlert() {
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent());

        String alertText = alert.getText();
        alert.accept();

        return alertText;
    }
}