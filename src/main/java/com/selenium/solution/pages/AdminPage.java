package com.selenium.solution.pages;

import com.selenium.solution.utilities.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Users")
    public WebElement usersMenuTab;

    public WebElement usersMenuTab(){
        return waitUntil(driver, usersMenuTab);
    }

    public UsersHomePage navigateToUserTab(){
        usersMenuTab().click();
        return PageFactory.usersHomePage(driver);
    }

}
