
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductPage;
import utils.BaseTest;
import pages.CheckoutPage;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void verifyValidOrderCanBePlaced() {

        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Step 1: Open a product
        productPage.openFirstProduct();

        // Step 2: Add the product to the cart
        productPage.clickAddToCart();
        String alertMessage =
                productPage.acceptAddToCartAlert();

        Assert.assertTrue(
                alertMessage.toLowerCase().contains("added"),
                "Product was not added to the cart"
        );

        // Step 3: Open the cart
        productPage.openCart();

        Assert.assertTrue(
                productPage.isProductInCart("Samsung galaxy s6"),
                "Expected product was not found in the cart"
        );

        // Step 4: Click Place Order
        checkoutPage.clickPlaceOrder();

        // Step 5: Enter test customer details
        checkoutPage.enterCustomerDetails(
                "Test Customer",
                "India",
                "Bengaluru",
                "1234567890123456",
                "October",
                "2027"
        );

        // Step 6: Click Purchase
        checkoutPage.clickPurchase();

        // Step 7: Verify confirmation
        String confirmation =
                checkoutPage.getConfirmationMessage();

        Assert.assertTrue(
                confirmation.toLowerCase().contains("thank you"),
                "Order confirmation message was not displayed"
        );

        System.out.println(
                "Order confirmation: " + confirmation);

        System.out.println(
                "PS_TC_013: Valid order placed successfully");

        // Step 8: Close the confirmation dialog
        checkoutPage.closeConfirmation();
    }
}