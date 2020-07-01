package com.selenium.solution.utilities;

import com.selenium.solution.pages.*;
import org.openqa.selenium.WebDriver;

public class PageFactory {
    private static <T extends BasePage> T getPageObject(WebDriver driver, Class<? extends BasePage> clazz, boolean wait) {
        try {
            @SuppressWarnings("unchecked")
            T object = (T) org.openqa.selenium.support.PageFactory.initElements(driver, clazz);
            BasePage.driver = driver;
            if(wait) {
                object.waitForPageLoad();
            }
            return object;
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AdminPage adminPage(WebDriver driver){
        return getPageObject(driver, AdminPage.class, true);
    }
    public static UsersHomePage usersHomePage(WebDriver driver){
        return getPageObject(driver, UsersHomePage.class,true);
    }
    public static ViewUserDetailsPage viewUserDetailsPage(WebDriver driver){
        return getPageObject(driver, ViewUserDetailsPage.class, true);
    }
    public static NewUserPage newUserPage(WebDriver driver){
        return getPageObject(driver, NewUserPage.class, true);
    }

}
