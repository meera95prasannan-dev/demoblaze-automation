package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"MeeraP", "123456", true},
            {"MeeraP", "WrongPassword123", false},
            {"InvalidUser123", "AnyPassword123", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(
            String username,
            String password,
            boolean shouldLoginSucceed) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openLoginForm();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        if (shouldLoginSucceed) {

            try {
                wait.until(d ->
                        d.findElement(By.id("nameofuser")).isDisplayed()
                        && d.findElement(By.id("nameofuser"))
                             .getText()
                             .contains(username)
                );

                System.out.println(
                        "PASS: Login successful for " + username
                );

            } catch (TimeoutException e) {
                Assert.fail(
                        "Login was not successful for user: " + username
                );
            }

        } else {

            try {
                Alert alert = wait.until(
                        ExpectedConditions.alertIsPresent()
                );

                String alertText = alert.getText();
                String actualMessage = alertText.toLowerCase();

                Assert.assertTrue(
                        actualMessage.contains("wrong")
                                || actualMessage.contains(
                                        "user does not exist"),
                        "Expected a login failure alert, but got: "
                                + alertText
                );

                alert.accept();

                System.out.println(
                        "PASS: Invalid login rejected for " + username
                );

            } catch (TimeoutException e) {
                Assert.fail(
                        "Expected a login error alert for user: "
                                + username
                );
            }
        }
    }
}