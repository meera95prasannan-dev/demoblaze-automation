package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By storeLogo = By.id("nava");
    private By productCards = By.cssSelector("#tbodyid .card");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    // Verify home page logo
    public boolean isHomePageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(storeLogo)
        ).isDisplayed();
    }

    // Get page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Count displayed products
    public int getProductCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(productCards)
        );

        List<WebElement> products =
                driver.findElements(productCards);

        return products.size();
    }
}