package com.enterprise.framework.tests;

import com.enterprise.framework.pages.LoginPage;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageInvalidTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/?locale=in");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void invalidLoginShouldDisplayErrorMessage() {
        try {
            loginPage.enterUsername("invalid.user@test.com");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isPasswordVisible(), "Password field did not appear after username submission.");

            loginPage.enterPassword("WrongPassword123!");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageVisible(), "Expected login validation error for invalid credentials.");
            String errorText = loginPage.getErrorMessage();
            Assert.assertTrue(errorText.toLowerCase().contains("username") || errorText.toLowerCase().contains("password") || errorText.toLowerCase().contains("error") || errorText.toLowerCase().contains("check your username"),
                    "Unexpected error text: " + errorText);
        } catch (Exception e) {
            Assert.fail("Invalid login scenario failed: " + e.getMessage());
        }
    }
}
