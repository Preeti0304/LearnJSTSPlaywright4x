package com.enterprise.framework.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username' or @name='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password' or @name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='Login' or @name='Login' or @value='Log In']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn' or @name='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//*[contains(text(),'Please check your username and password') or contains(text(),'Error:') or contains(@id,'error') or contains(@class,'error') or contains(@class,'loginError')]")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(usernameInput));
            element.clear();
            element.sendKeys(username);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Unable to locate or interact with username field.", e);
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(passwordInput));
            element.clear();
            element.sendKeys(password);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new RuntimeException("Unable to locate or interact with password field.", e);
        }
    }

    public void clickRememberMe() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
            if (!element.isSelected()) {
                element.click();
            }
        } catch (TimeoutException | ElementClickInterceptedException | NoSuchElementException e) {
            throw new RuntimeException("Unable to click Remember Me checkbox.", e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_form")));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("document.getElementById('login_form').submit();");
        } catch (TimeoutException | ElementClickInterceptedException | NoSuchElementException e) {
            throw new RuntimeException("Unable to submit the login form.", e);
        }
    }

    public void performLogin(String username, String password) {
        try {
            if (isUsernameVisible()) {
                enterUsername(username);
                clickLogin();
            }

            if (isPasswordVisible()) {
                enterPassword(password);
                clickLogin();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Login attempt failed.", e);
        }
    }

    public boolean isUsernameVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(usernameInput)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPasswordVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorMessageVisible() {
        String pageSource = driver.getPageSource();
        if (pageSource.contains("Please check your username and password") || pageSource.contains("Error:")) {
            return true;
        }

        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Please check your username and password') or contains(text(),'Error:') or contains(@id,'error') or contains(@class,'error') or contains(@class,'loginError')]")));
            return error.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        String pageSource = driver.getPageSource();
        if (pageSource.contains("Please check your username and password")) {
            return "Please check your username and password.";
        }
        if (pageSource.contains("Error:")) {
            return "Error:";
        }

        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Please check your username and password') or contains(text(),'Error:') or contains(@id,'error') or contains(@class,'error') or contains(@class,'loginError')]")));
            return error.getText();
        } catch (TimeoutException | NoSuchElementException e) {
            return "";
        }
    }

    public void waitForPageToLoad() {
        wait.until(driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete"));
    }
}
