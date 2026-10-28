package com.prodigy.task4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model for SauceDemo Cross-Browser Testing (Task-04)
 */
public class SauceDemoLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorBanner = By.cssSelector("[data-test='error']");
    
    // Inventory Locators
    private final By inventoryContainer = By.className("inventory_list");
    private final By productHeader = By.className("title");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public SauceDemoLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        element.clear();
        if (username != null && !username.isEmpty()) {
            element.sendKeys(username);
        }
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        element.clear();
        if (password != null && !password.isEmpty()) {
            element.sendKeys(password);
        }
    }

    public void clickLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        WebElement logoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(logoutLink));
        try {
            Thread.sleep(1000); // Wait for CSS menu slide transition across different browsers
        } catch (InterruptedException ignored) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner)).getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInventoryPageDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("/inventory.html"));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productHeader)).getText();
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}
