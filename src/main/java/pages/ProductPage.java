package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Phones category locators
    private By phonesCategory =
            By.xpath("//a[normalize-space()='Phones']");

    private By productCards =
            By.cssSelector("#tbodyid .card");

    private By productNames =
            By.cssSelector("#tbodyid .card-title");

    // Product details locators
    private By firstProduct =
            By.cssSelector("#tbodyid .card-title a");

    private By productDetailName =
            By.cssSelector(".name");

    private By productDetailPrice =
            By.cssSelector(".price-container");

    private By productDescription =
            By.id("more-information");

    // Cart locators
    private By addToCartButton =
            By.xpath("//a[normalize-space()='Add to cart']");

    private By cartLink =
            By.id("cartur");

    private By cartRows =
            By.cssSelector("#tbodyid tr.success");

    private By cartProductNames =
            By.cssSelector("#tbodyid tr.success td:nth-child(2)");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    // Select Phones category
    public void selectPhonesCategory() {
        wait.until(
                ExpectedConditions.elementToBeClickable(phonesCategory)
        ).click();
    }

    // Get product count
    public int getProductCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(productCards)
        );

        List<WebElement> products =
                driver.findElements(productCards);

        return products.size();
    }

    // Verify products are displayed
    public boolean areProductsDisplayed() {
        return getProductCount() > 0;
    }

    // Verify displayed products have recognized phone brand names
    public boolean areProductsPhones() {
        return wait.until(driver -> {
            List<WebElement> products =
                    driver.findElements(productNames);

            if (products.isEmpty()) {
                return false;
            }

            try {
                for (WebElement product : products) {
                    String productName =
                            product.getText().toLowerCase();

                    if (!productName.contains("samsung")
                            && !productName.contains("nokia")
                            && !productName.contains("nexus")
                            && !productName.contains("iphone")
                            && !productName.contains("htc")
                            && !productName.contains("lg")
                            && !productName.contains("sony")) {
                        return false;
                    }
                }

                return true;

            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    // Open the first product displayed
    public void openFirstProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(firstProduct)
        ).click();
    }

    // Get product name from details page
    public String getProductDetailName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productDetailName)
        ).getText();
    }

    // Get product price from details page
    public String getProductDetailPrice() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productDetailPrice)
        ).getText();
    }

    // Get product description from details page
    public String getProductDescription() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productDescription)
        ).getText();
    }

    // Click Add to cart
    public void clickAddToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        ).click();
    }

    // Accept the browser alert after adding a product
    public String acceptAddToCartAlert() {
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent());

        String alertText = alert.getText();
        alert.accept();

        return alertText;
    }

    // Open the shopping cart
    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("tbodyid"))
        );
    }

    // Check whether a product is present in the cart
    public boolean isProductInCart(String expectedProductName) {
        return wait.until(driver -> {
            List<WebElement> products =
                    driver.findElements(cartProductNames);

            for (WebElement product : products) {
                try {
                    if (product.getText().trim()
                            .equalsIgnoreCase(expectedProductName)) {
                        return true;
                    }
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            return false;
        });
    }

    // Remove a specific product from the cart
    public void removeProductFromCart(String expectedProductName) {

        wait.until(driver -> {
            List<WebElement> rows =
                    driver.findElements(cartRows);

            for (WebElement row : rows) {
                try {
                    String name = row.findElement(
                            By.cssSelector("td:nth-child(2)")
                    ).getText().trim();

                    if (name.equalsIgnoreCase(expectedProductName)) {
                        row.findElement(
                                By.linkText("Delete")
                        ).click();

                        return true;
                    }

                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            return false;
        });

        // Wait until the product disappears from the cart
        wait.until(driver -> {
            List<WebElement> products =
                    driver.findElements(cartProductNames);

            for (WebElement product : products) {
                try {
                    if (product.getText().trim()
                            .equalsIgnoreCase(expectedProductName)) {
                        return false;
                    }
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            return true;
        });
    }

    // Verify a product is absent from the cart
    public boolean isProductAbsentFromCart(String expectedProductName) {

        List<WebElement> products =
                driver.findElements(cartProductNames);

        for (WebElement product : products) {
            try {
                if (product.getText().trim()
                        .equalsIgnoreCase(expectedProductName)) {
                    return false;
                }
            } catch (StaleElementReferenceException e) {
                return false;
            }
        }

        return true;
    }
}