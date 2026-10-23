
package tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.RegistrationPage;
import utils.BaseTest;

public class RegistrationTest extends BaseTest {

    @Test
    public void verifyUserCanRegisterSuccessfully() {

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        // Generate a unique username for each run
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("MMddHHmmss"));

        String username = "qauser" + timestamp;
        String password = "Test@12345";

        // Step 1: Open registration form
        registrationPage.openRegistrationForm();

        // Step 2: Enter username and password
        registrationPage.enterUsername(username);
        registrationPage.enterPassword(password);

        // Step 3: Submit registration
        registrationPage.clickSignUp();

        // Step 4: Capture registration result
        String alertMessage =
                registrationPage.acceptRegistrationAlert();

        System.out.println(
                "Registration result: " + alertMessage);

        // Step 5: Verify successful registration
        Assert.assertTrue(
                alertMessage.toLowerCase().contains("sign up successful"),
                "Registration failed. Alert message: " + alertMessage
        );

        System.out.println(
                "PS_TC_002: User registration completed successfully");

        System.out.println("Registered test username: " + username);
    }
}
