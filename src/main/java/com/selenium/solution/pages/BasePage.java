package com.selenium.solution.pages;

import com.selenium.solution.utilities.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public BasePage(WebDriver driver){
        this.driver = (ChromeDriver) driver;
    }
    public static WebDriver driver;

    public static int WAIT_UNTIL_TIMEOUT = 30;
    public static int PAGE_LOAD_TIMEOUT = 30;
    public static int IMPLICIT_WAIT = 30;

    public static void waitForPageLoad(){
        pauseExecution();
    }

    public static WebDriverWait wait(WebDriver driver, Integer time) {
        return new WebDriverWait(driver, time);
    }

    /**
     * WebDriverWait for 30 seconds
     * @param driver
     * @return WebDriverWait
     */
    public static WebDriverWait wait(WebDriver driver) {
        return wait(driver, WAIT_UNTIL_TIMEOUT);
    }

    public WebElement waitUntil(WebDriver driver, WebElement element) {
        return wait(driver).pollingEvery(3, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(linkText = "Users")
    public WebElement usersMenu;

    public WebElement usersMenu(){
        return waitUntil(driver, usersMenu);
    }

    public static void pauseExecution(){
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void pauseExecution(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        public boolean isElementDisplayed(WebElement element) {
        try {
            if (element != null) {
                return element.isDisplayed();
            }
        } catch (Exception e) {
            System.out.println("Element was not displayed.");
            return false;
        }
        return false;
    }
}
