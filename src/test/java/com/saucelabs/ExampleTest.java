package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class ExampleTest {
    private String browserName;
    private String browserVersion;
    private String platformName;
    private String url;
    private long startTime;
    private long endTime;
    private int counter = 50;

    @BeforeSuite
    public void beforeSuite() {
        startTime = System.currentTimeMillis();
    }

    @AfterSuite
    public void afterSuite() {
        endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) / 1000 + " seconds");
    }

    @BeforeTest
    public void beforeTest(ITestContext context) {
        browserName = context.getCurrentXmlTest().getParameter("browserName");
        browserVersion = context.getCurrentXmlTest().getParameter("browserVersion");
        platformName = context.getCurrentXmlTest().getParameter("platformName");
        url = context.getCurrentXmlTest().getParameter("url");

        if (context.getCurrentXmlTest().getParameter("runOnSauceLabs").equals("true")) {
            url = "https://" + System.getenv("SAUCE_USERNAME") + ":" + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.eu-central-1.saucelabs.com/wd/hub";
        }
    }

    @Test
    public void checkTitleTest(Method method) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);

        if (url.contains("sauce")) {
            MutableCapabilities sauceCapabilities = new MutableCapabilities();
            sauceCapabilities.setCapability("name", method.getName());
            sauceCapabilities.setCapability("build", System.getenv("BUILD_TAG"));
            capabilities.setCapability("sauce:options", sauceCapabilities);
        }

        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);

        driver.get("https://www.saucedemo.com");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");

        driver.quit();
    }

    @Test
    public void loginTest(Method method) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);

        if (url.contains("sauce")) {
            MutableCapabilities sauceCapabilities = new MutableCapabilities();
            sauceCapabilities.setCapability("name", method.getName());
            sauceCapabilities.setCapability("build", System.getenv("BUILD_TAG"));
            capabilities.setCapability("sauce:options", sauceCapabilities);
        }

        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);

        for (int i = 0; i <= counter; i++) {
            driver.get("https://www.saucedemo.com");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        }

        driver.quit();
    }
    
    @Test
    public void checkoutTest(Method method) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);

        if (url.contains("sauce")) {
            MutableCapabilities sauceCapabilities = new MutableCapabilities();
            sauceCapabilities.setCapability("name", method.getName());
            sauceCapabilities.setCapability("build", System.getenv("BUILD_TAG"));
            capabilities.setCapability("sauce:options", sauceCapabilities);
        }

        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);

        for (int i = 0; i <= counter; i++) {
            driver.get("https://www.saucedemo.com");
            driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
            driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
            driver.findElement(By.cssSelector("#login-button")).click();
            driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.cssSelector(".shopping_cart_badge")).click();
            driver.findElement(By.cssSelector("#checkout")).click();
            driver.findElement(By.cssSelector("#first-name")).sendKeys("Sauce");
            driver.findElement(By.cssSelector("#last-name")).sendKeys("Bot");
            driver.findElement(By.cssSelector("#postal-code")).sendKeys("12345");
            driver.findElement(By.cssSelector("#continue")).click();
            driver.findElement(By.id("finish")).click();

            Assert.assertTrue(driver.findElement(By.className("pony_express")).isDisplayed());
        }

        driver.quit();
    }

    @Test
    public void loginLogoutTest(Method method) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);

        if (url.contains("sauce")) {
            MutableCapabilities sauceCapabilities = new MutableCapabilities();
            sauceCapabilities.setCapability("name", method.getName());
            sauceCapabilities.setCapability("build", System.getenv("BUILD_TAG"));
            capabilities.setCapability("sauce:options", sauceCapabilities);
        }

        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);

        driver.get("https://www.saucedemo.com");

        for (int i = 0; i <= counter; i++) {
            driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
            driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
            driver.findElement(By.cssSelector("#login-button")).click();
            driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();

            try {
                driver.findElement(By.id("logout_sidebar_link")).click();
            } catch (ElementNotInteractableException e) {
                driver.executeScript("arguments[0].click();", driver.findElement(By.id("logout_sidebar_link")));
            }

            Assert.assertTrue(driver.findElement(By.cssSelector("#login-button")).isDisplayed());
        }

        driver.quit();
    }
}