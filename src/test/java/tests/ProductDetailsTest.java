package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductPage;
import utils.BaseTest;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void verifyProductDetails() {

        ProductPage productPage = new ProductPage(driver);

        // Open the first product
        productPage.openFirstProduct();

        // Capture product details
        String productName = productPage.getProductDetailName();
        String productPrice = productPage.getProductDetailPrice();
        String productDescription = productPage.getProductDescription();

        // Validate product details
        Assert.assertFalse(
            productName.trim().isEmpty(),
            "Product name is missing"
        );

        Assert.assertTrue(
            productPrice.contains("$"),
            "Product price is missing or invalid"
        );

        Assert.assertFalse(
            productDescription.trim().isEmpty(),
            "Product description is missing"
        );

        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Description: " + productDescription);

        System.out.println("PS_TC_006: Product details verified successfully");
    }
}
