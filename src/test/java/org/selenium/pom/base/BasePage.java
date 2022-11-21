package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }


    public void waitForAnimationsToDissapear(By animation){

        List<WebElement> animations = driver.findElements(animation);
        System.out.println("Animation size: " + animations.size());
        if(animations.size()>0){
            wait.until(ExpectedConditions.invisibilityOfAllElements(animations));
            System.out.println("ANIMATIONS ARE INVISIBLE");
        }
        else{
            System.out.println("Animation not found");
        }
    }
}
