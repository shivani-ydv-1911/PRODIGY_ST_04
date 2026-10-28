package com.prodigy.task4.tests;

import com.prodigy.task4.base.BaseCrossBrowserTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Task-04: Cross-Browser Test Suite for SauceDemo Login & Compatibility
 */
public class CrossBrowserLoginTest extends BaseCrossBrowserTest {

    @Test(priority = 1, description = "Verify successful login and UI rendering across browsers")
    public void testValidLoginAcrossBrowsers() {
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), 
                "User should be navigated to inventory page across all browsers");
        Assert.assertEquals(loginPage.getPageHeaderTitle(), "Products", 
                "Product header text should be consistent across all browsers");
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(), 
                "Inventory list container should render properly");
    }

    @Test(priority = 2, description = "Verify invalid credentials error message across browsers")
    public void testInvalidCredentialsErrorAcrossBrowsers() {
        loginPage.login("invalid_user_999", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error container should be visible across all browsers");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), 
                "Error message copy must be identical across all browsers");
    }

    @Test(priority = 3, description = "Verify empty input validation across browsers")
    public void testEmptyFieldsValidationAcrossBrowsers() {
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Validation error must appear for empty fields across all browsers");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                "Username required error text should match expected across all browsers");
    }

    @Test(priority = 4, description = "Verify user logout functionality and session termination")
    public void testLogoutFlowAcrossBrowsers() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(), "Login must succeed prior to logout");

        loginPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), 
                "Should redirect back to login page after logout across all browsers");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), 
                "Login button should be visible and clickable again");
    }
}
