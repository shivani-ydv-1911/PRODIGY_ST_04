package com.prodigy.task4.base;

import com.prodigy.task4.pages.SauceDemoLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

/**
 * Base Cross-Browser Test Setup supporting both:
 * 1. BrowserStack Selenium Cloud Grid (RemoteWebDriver)
 * 2. Local Cross-Browser Execution (Chrome, Edge, Firefox)
 */
public class BaseCrossBrowserTest {
    protected WebDriver driver;
    protected SauceDemoLoginPage loginPage;
    protected final String APP_URL = "https://www.saucedemo.com/";

    // BrowserStack Credentials (can also be read from environment variables)
    protected String bsUsername = System.getenv("BROWSERSTACK_USERNAME") != null 
            ? System.getenv("BROWSERSTACK_USERNAME") : "YOUR_BROWSERSTACK_USERNAME";
    protected String bsAccessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null 
            ? System.getenv("BROWSERSTACK_ACCESS_KEY") : "YOUR_BROWSERSTACK_ACCESS_KEY";

    @BeforeMethod
    @Parameters({
        "browser", 
        "browser_version", 
        "os", 
        "os_version", 
        "device", 
        "is_browserstack"
    })
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("latest") String browserVersion,
            @Optional("Windows") String os,
            @Optional("11") String osVersion,
            @Optional("") String device,
            @Optional("false") String isBrowserstack,
            Method method) throws Exception {

        boolean useBrowserStack = Boolean.parseBoolean(isBrowserstack) 
                && !bsUsername.equals("YOUR_BROWSERSTACK_USERNAME") 
                && !bsAccessKey.equals("YOUR_BROWSERSTACK_ACCESS_KEY");

        if (useBrowserStack) {
            // ==========================================
            // 1. BROWSERSTACK REMOTE WEBDRIVER GRID
            // ==========================================
            MutableCapabilities capabilities;
            HashMap<String, Object> bstackOptions = new HashMap<>();

            bstackOptions.put("userName", bsUsername);
            bstackOptions.put("accessKey", bsAccessKey);
            bstackOptions.put("projectName", "Prodigy InfoTech - Software Testing");
            bstackOptions.put("buildName", "PRODIGY_ST_04 Cross-Browser Build");
            bstackOptions.put("sessionName", method.getName() + " on " + browser + " (" + (device.isEmpty() ? os : device) + ")");
            bstackOptions.put("networkLogs", "true");
            bstackOptions.put("consoleLogs", "info");

            if (!device.isEmpty()) {
                // Mobile device configuration (e.g. iPhone 15 / Samsung Galaxy S23)
                capabilities = new MutableCapabilities();
                bstackOptions.put("deviceName", device);
                bstackOptions.put("osVersion", osVersion);
                capabilities.setCapability("bstack:options", bstackOptions);
            } else {
                // Desktop browser configuration (Chrome, Firefox, Safari, Edge)
                bstackOptions.put("os", os);
                bstackOptions.put("osVersion", osVersion);

                switch (browser.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions ffOpts = new FirefoxOptions();
                        ffOpts.setBrowserVersion(browserVersion);
                        ffOpts.setCapability("bstack:options", bstackOptions);
                        capabilities = ffOpts;
                        break;
                    case "safari":
                        SafariOptions safariOpts = new SafariOptions();
                        safariOpts.setBrowserVersion(browserVersion);
                        safariOpts.setCapability("bstack:options", bstackOptions);
                        capabilities = safariOpts;
                        break;
                    case "edge":
                        EdgeOptions edgeOpts = new EdgeOptions();
                        edgeOpts.setBrowserVersion(browserVersion);
                        edgeOpts.setCapability("bstack:options", bstackOptions);
                        capabilities = edgeOpts;
                        break;
                    case "chrome":
                    default:
                        ChromeOptions chromeOpts = new ChromeOptions();
                        chromeOpts.setBrowserVersion(browserVersion);
                        chromeOpts.setCapability("bstack:options", bstackOptions);
                        capabilities = chromeOpts;
                        break;
                }
            }

            URL hubUrl = new URL("https://" + bsUsername + ":" + bsAccessKey + "@hub-cloud.browserstack.com/wd/hub");
            driver = new RemoteWebDriver(hubUrl, capabilities);

        } else {
            // ==========================================
            // 2. LOCAL CROSS-BROWSER EXECUTION
            // ==========================================
            switch (browser.toLowerCase()) {
                case "edge":
                    try {
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                        driver = new EdgeDriver(edgeOptions);
                    } catch (Exception e) {
                        System.out.println("Edge fallback to Chrome: " + e.getMessage());
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions fallbackOpts = new ChromeOptions();
                        fallbackOpts.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                        driver = new ChromeDriver(fallbackOpts);
                    }
                    break;

                case "firefox":
                    try {
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions ffOptions = new FirefoxOptions();
                        ffOptions.addArguments("-headless");
                        driver = new FirefoxDriver(ffOptions);
                    } catch (Exception e) {
                        System.out.println("Firefox fallback to Chrome: " + e.getMessage());
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions fallbackOpts = new ChromeOptions();
                        fallbackOpts.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                        driver = new ChromeDriver(fallbackOpts);
                    }
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080", "--no-sandbox");
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(APP_URL);
        loginPage = new SauceDemoLoginPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            try {
                // Set status on BrowserStack if running remotely
                if (driver instanceof RemoteWebDriver && !driver.toString().contains("ChromeDriver")) {
                    String status = result.isSuccess() ? "passed" : "failed";
                    String reason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Completed";
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                            "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" 
                            + status + "\", \"reason\": \"" + reason.replace("\"", "'") + "\"}}");
                }
            } catch (Exception ignored) {
            } finally {
                driver.quit();
            }
        }
    }
}
