package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;

import java.util.List;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void startDriver() {
        String browser ="CHROME";
        driver = new DriverManager().initializeDriver(browser);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies((cookies));
        for(Cookie cookie: seleniumCookies){
            driver.manage().addCookie(cookie);
        }
    }

}
