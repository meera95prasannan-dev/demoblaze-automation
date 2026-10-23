
package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Cart and checkout locators
    private By placeOrderButton =
            By.xpath("//button[normalize-space()='Place Order']");

    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By cardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");

    private By purchaseButton =
            By.xpath("//button[normalize-space()='Purchase']");

    // Order confirmation
    private By confirmationMessage =
            By.cssSelector(".sweet-alert h2");

    private By confirmationDetails =
            By.cssSelector(".sweet-alert .lead");

    private By confirmationOkButton =
            By.xpath("//button[normalize-space()='OK']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    public void clickPlaceOrder() {
        wait.until(
                ExpectedConditions.elementToBeClickable(placeOrderButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        );
    }

    public void enterCustomerDetails(
            String name,
            String country,
            String city,
            String card,
            String month,
            String year) {

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    public void clickPurchase() {
        wait.until(
                ExpectedConditions.elementToBeClickable(purchaseButton)
        ).click();
    }

    public String getConfirmationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationMessage)
        ).getText();
    }

    public String getConfirmationDetails() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationDetails)
        ).getText();
    }

    public void closeConfirmation() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmationOkButton)
        ).click();
    }
}