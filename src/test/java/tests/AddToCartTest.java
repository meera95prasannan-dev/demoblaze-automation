package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductPage;
import utils.BaseTest;

public class AddToCartTest extends BaseTest {

    @Test
    public void verifyProductCanBeAddedToCart() {

        ProductPage productPage = new ProductPage(driver);

        // Open the first product
        productPage.openFirstProduct();

        // Capture the product name before adding it
        String expectedProductName =
                productPage.getProductDetailName();

        // Click Add to cart and handle the alert
        productPage.clickAddToCart();

        String alertMessage =
                productPage.acceptAddToCartAlert();

        Assert.assertTrue(
                alertMessage.toLowerCase().contains("added"),
                "Expected confirmation that the product was added"
        );

        // Navigate to the cart
        productPage.openCart();

        // Verify that the product appears in the cart
        Assert.assertTrue(
                productPage.isProductInCart(expectedProductName),
                "Product was not found in the shopping cart"
        );

        System.out.println(
                "Added product: " + expectedProductName);

        System.out.println(
                "PS_TC_007: Product added to cart successfully");
    }
}