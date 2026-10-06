package com.enterprise.framework.tests;

import com.enterprise.framework.pages.LoginPage;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageValidTest {
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
    public void validLoginShouldNavigateToHomePage() {
        String username = System.getProperty("salesforce.username");
        String password = System.getProperty("salesforce.password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new SkipException("Set -Dsalesforce.username and -Dsalesforce.password to run the valid login test.");
        }

        try {
            loginPage.performLogin(username, password);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("lightning") || currentUrl.contains("home") || currentUrl.contains("one.app") || !currentUrl.contains("login"),
                    "Valid login did not redirect away from login page.");
        } catch (Exception e) {
            Assert.fail("Valid login scenario failed: " + e.getMessage());
        }
    }
}
