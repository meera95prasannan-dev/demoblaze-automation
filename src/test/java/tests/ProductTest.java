package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductPage;
import utils.BaseTest;

public class ProductTest extends BaseTest {

    @Test
    public void verifyPhonesCategorySelection() {

        ProductPage productPage = new ProductPage(driver);

        productPage.selectPhonesCategory();

        Assert.assertTrue(
            productPage.areProductsDisplayed(),
            "No products displayed after selecting Phones category"
        );

        Assert.assertTrue(
            productPage.areProductsPhones(),
            "Non-phone products displayed in the Phones category"
        );

        System.out.println("PS_TC_004: Phones category selection verified");
    }
}