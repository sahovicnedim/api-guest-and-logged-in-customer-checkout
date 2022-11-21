package org.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;


public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderButton = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    private final By animation = By.cssSelector(".blockUI.blockOverlay");
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By directBankTransferRadioButton = By.id("payment_method_bacs");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }


    public CheckoutPage enterFirstName(String firstName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        e.clear();
        e.sendKeys(lastName);

        return this;
    }

    public CheckoutPage selectCountry(String countryName){

        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneField));
        e.clear();
        e.sendKeys(addressLineOne);

        return this;
    }

    public CheckoutPage enterCity(String city){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityField));
        e.clear();
        e.sendKeys(city);

        return this;
    }


    public CheckoutPage selectState(String stateName){

        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }
    public CheckoutPage enterPostCode(String postCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeField));
        e.clear();
        e.sendKeys(postCode);

        return this;
    }

    public CheckoutPage enterEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailField));
        e.clear();
        e.sendKeys(email);

        return this;
    }


    public CheckoutPage setBillingAddress(BillingAddress billingAddress){

        return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostCode()).
                enterEmail(billingAddress.getEmail());
    }


    public CheckoutPage placeOrder(){
        waitForAnimationsToDissapear(animation);
        driver.findElement(placeOrderButton).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();

    }


    public CheckoutPage selectDirectBankTransferRadioButton(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioButton));

        if(!e.isSelected()){
            e.click();
        }
        return this;
    }


}
