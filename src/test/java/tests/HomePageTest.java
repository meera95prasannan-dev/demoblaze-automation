package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageLoadsSuccessfully() {

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.getCurrentUrl()
                        .startsWith("https://www.demoblaze.com"),
                "Incorrect application URL"
        );

        Assert.assertFalse(
                homePage.getPageTitle().trim().isEmpty(),
                "Page title should not be empty"
        );

        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "Home page logo is not displayed"
        );

        Assert.assertTrue(
                homePage.getProductCount() > 0,
                "No products are displayed"
        );

        System.out.println(
                "PS_TC_001: Home page loaded successfully"
        );
    }
}
