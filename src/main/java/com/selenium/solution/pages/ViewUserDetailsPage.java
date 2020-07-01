package com.selenium.solution.pages;

import com.selenium.solution.utilities.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewUserDetailsPage extends BasePage {
    public ViewUserDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='customer-details_sidebar_section']//tr[1]//td[1]")
    public WebElement userName;
    @FindBy(xpath = "//div[@id='customer-details_sidebar_section']//tr[2]//td[1]")
    public WebElement userEmail;
    @FindBy(xpath = "//tr[3]//td[1]")
    public WebElement dateCreated;
    @FindBy(xpath = "//div[@id='titlebar_right']//span[1]//a[1]")
    public  WebElement editUserButton;
    @FindBy(xpath = "//span[2]//a[1]")
    public  WebElement deleteUserButton;
    @FindBy(xpath = "//div[@class='flash flash_notice']")
    public WebElement userSuccessfullyCreated;
    @FindBy(linkText = "Users")
    public WebElement userTab;
    @FindBy(xpath = "//li[@class='cancel']//a")
    public WebElement cancelButton;

    public String getUserName(){
        return userName.getText();
    }
    public String getUserEmail(){
        return userEmail.getText();
    }
    public String getDateCreated(){
        return dateCreated.getText();
    }
    public NewUserPage editUser(){
        editUserButton.click();
        return PageFactory.newUserPage(driver);
    }
    public UsersHomePage deleteUser(){
        deleteUserButton.click();
        pauseExecution(2);
        driver.switchTo().alert().accept();
        pauseExecution(2);
        return PageFactory.usersHomePage(driver);
    }
    public UsersHomePage navigateToUserTab(){
        userTab.click();
        return PageFactory.usersHomePage(driver);
    }
    public void deleteAndCancel(){
        deleteUserButton.click();
        driver.switchTo().alert().dismiss();
    }
    public UsersHomePage cancelUserCreation(){
        cancelButton.click();
        return PageFactory.usersHomePage(driver);
    }

}