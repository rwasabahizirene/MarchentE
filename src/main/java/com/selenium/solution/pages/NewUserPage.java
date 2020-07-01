package com.selenium.solution.pages;

import com.selenium.solution.utilities.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewUserPage extends BasePage {
    public NewUserPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h2[@id='page_title']")
    public WebElement pageTitle;
    @FindBy(xpath = "//input[@id='user_username']")
    public WebElement userNameTextField;
    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement passwordTextField;
    @FindBy(xpath = "//input[@id='user_email']")
    public WebElement emailTextField;
    @FindBy(xpath = "//li[@id='user_submit_action']//input")
    public WebElement submitButton;
    @FindBy(xpath = "//li[@class='cancel']//a")
    public WebElement cancelButton;
    @FindBy(xpath = "//li[@id='user_username_input']//p")
    public WebElement userNameError;
    @FindBy(xpath = "//li[@id='user_password_input']//p")
    public WebElement userPasswordError;
    @FindBy(xpath = "//li[@id='user_email_input']//p")
    public WebElement userEmailError;

    public void setUserName(String name){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userNameTextField.clear();
        userNameTextField.sendKeys(name);
    }
    public void setUserMail(String email){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }
    public void setUserPassword(String password){
        passwordTextField.clear();
        passwordTextField.sendKeys(password);
    }
    public String getUserNameError(){
        return userNameError.getText();
    }
    public String getUserEmailError(){
        return userEmailError.getText();
    }
    public String getUserPasswordError(){
        return userPasswordError.getText();
    }
    public ViewUserDetailsPage createUser(String name, String email, String password){
        setUserName(name);
        setUserPassword(password);
        setUserMail(email);
        submitButton.click();
        return PageFactory.viewUserDetailsPage(driver);
    }
    public void createWrongUser(String name, String email){
        setUserName(name);
        setUserMail(email);
        submitButton.click();
    }
    public UsersHomePage cancelUserCreation(){
        cancelButton.click();
        return PageFactory.usersHomePage(driver);
    }

}
