
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LogoutTest extends BaseTest {

    @Test
    public void verifyUserCanLogoutSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);

        // Step 1: Open the login form
        loginPage.openLoginForm();

        // Step 2: Enter registered credentials
        loginPage.enterUsername("MeeraP");
        loginPage.enterPassword("123456");

        // Step 3: Click Login
        loginPage.clickLogin();

        // Step 4: Verify successful login
        Assert.assertTrue(
                loginPage.isLogoutDisplayed(),
                "Login failed or Logout link is not displayed"
        );

        System.out.println("User logged in successfully");

        // Step 5: Click Logout
        loginPage.clickLogout();

        // Step 6: Verify successful logout
        Assert.assertTrue(
                loginPage.isLoginDisplayed(),
                "Login link is not displayed after logout"
        );

        System.out.println(
                "PS_TC_016: User logged out successfully"
        );
    }
}
