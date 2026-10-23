package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductPage;
import utils.BaseTest;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void verifyProductCanBeRemovedFromCart() {

        ProductPage productPage = new ProductPage(driver);

        // Step 1: Open the first product
        productPage.openFirstProduct();

        // Step 2: Capture the product name
        String productName =
                productPage.getProductDetailName();

        // Step 3: Add the product to the cart
        productPage.clickAddToCart();

        String alertMessage =
                productPage.acceptAddToCartAlert();

        Assert.assertTrue(
                alertMessage.toLowerCase().contains("added"),
                "Product was not added successfully"
        );

        // Step 4: Open the cart
        productPage.openCart();

        // Step 5: Verify the product is present
        Assert.assertTrue(
                productPage.isProductInCart(productName),
                "Product was not found in the cart before deletion"
        );

        System.out.println(
                "Product found in cart: " + productName
        );

        // Step 6: Delete the product
        productPage.removeProductFromCart(productName);

        // Step 7: Verify the product is no longer present
        Assert.assertTrue(
                productPage.isProductAbsentFromCart(productName),
                "Product is still displayed in the cart after deletion"
        );

        System.out.println(
                "PS_TC_011: Product removed from cart successfully"
        );
    }
}