package com.selenium.solution.pages;

import com.selenium.solution.utilities.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.xml.xpath.XPath;
import java.text.SimpleDateFormat;
import java.util.*;

public class UsersHomePage extends BasePage {
    public UsersHomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "page_title")
    public WebElement usersPageTitle;
    @FindBy(xpath = "//div[@id='q_username_input']//select")
    public WebElement userNameDropDown;
    @FindBy(xpath = "//div[@id='q_email_input']//select")
    public WebElement emailDropDown;
    @FindBy(id = "q_username")
    public WebElement userNameTextField;
    @FindBy(id = "q_email")
    public WebElement userEmailTextField;
    @FindBy(name = "commit")
    public WebElement filterButton;
    @FindBy(className = "clear_filters_btn")
    public WebElement clearFilterButton;
    @FindBy(xpath = "//table[@id='index_table_users']//tbody")
    public WebElement usersTable;
    @FindBy(xpath = "//div//div//tr")
    public WebElement userCreatedDate;
    @FindBy(xpath = "//table[@id='index_table_users']//tbody//")
    public WebElement table;
    @FindBy(className = "blank_slate")
    public WebElement noUserFound;
    @FindBy(xpath = "//span[@class='action_item']//a")
    public WebElement newUserButon;
    @FindBy(xpath = "//input[@id='q_created_at_gteq_datetime']")
    public WebElement startDate;
    @FindBy(xpath = "//input[@id='q_created_at_lteq_datetime']")
    public WebElement endDate;

    public NewUserPage clickNewUserButton(){
        newUserButon.click();
        return PageFactory.newUserPage(driver);
    }
    public String dataPath(){
        return "//div//div//tr";
    }
    public String table(){
        return "//table[@id='index_table_users']//tbody";
    }
    public WebElement noUserFound(){
        return waitUntil(driver, noUserFound);
    }
    public WebElement usersPageTitle(){
        return waitUntil(driver, usersPageTitle);
    }
    public WebElement userNameDropDown(){
        return waitUntil(driver, userNameDropDown);
    }
    public WebElement userEmailDropDown(){
        return waitUntil(driver, emailDropDown);
    }
    public void filterUserNameByContains(){
        Select filterOption = new Select(userNameDropDown());
        filterOption.selectByVisibleText("Contains");
    }
    public void filterUserNameByEquals(){
        Select filterOption = new Select(userNameDropDown());
        filterOption.selectByVisibleText("Equals");
    }
    public void filterUserNameByStartsWith(){
        Select filterOption = new Select(userNameDropDown());
        filterOption.selectByVisibleText("Starts with");
    }
    public void filterUserNameByEndsWith(){
        Select filterOption = new Select(userNameDropDown());
        filterOption.selectByVisibleText("Ends with");
    }
    public void filterUserEmailByContains(){
        Select filterOption = new Select(userEmailDropDown());
        filterOption.selectByVisibleText("Contains");
    }
    public void filterUserEmailByStartsWith(){
        Select filterOption = new Select(userEmailDropDown());
        filterOption.selectByVisibleText("Starts with");
    }
    public void filterUserEmailByEndsWith(){
        Select filterOption = new Select(userEmailDropDown());
        filterOption.selectByVisibleText("Ends with");
    }
    public void filterUserEmailByEquals(){
        Select filterOption = new Select(userEmailDropDown());
        filterOption.selectByVisibleText("Equals");
    }
    public void setUserNameValue(String value){
        userNameTextField.clear();
        userNameTextField.sendKeys(value);
    }
    public void setUserEmailValue(String value){
        userEmailTextField.sendKeys(value);
    }
    public void setStartDate(String date){
        startDate.clear();
        startDate.sendKeys(date);
    }
    public void setEndDate( String date){
        endDate.clear();
        endDate.sendKeys(date);
    }
    public void applyFilter(){
        filterButton.click();
        pauseExecution();
    }
    public boolean verifyNoUserFound(){
        return isElementDisplayed(noUserFound);
    }
    public Map<String, String> getTableValues(){
        String name = null;
        String email = null;
        List<WebElement> rowSize = usersTable.findElements(By.tagName("tr"));
        Map <String, String> values = new LinkedHashMap<>();
        for (int row = 1; row <= rowSize.size(); row++ ){
            for (int col = 3; col <= 4; col++){
                switch (col){
                    case 3:
                        name = driver.findElement(By.xpath(table() + "//tr[" + row + "]//" + "td[" + col + "]")).getText();
                    case 4:
                        email = driver.findElement(By.xpath(table() + "//tr[" + row + "]//" + "td[" + col + "]")).getText();
                }
            }
            values.put(name, email);
        }
        return values;
    }
    public ViewUserDetailsPage viewUserDetails(String userName){
        WebElement view = null;
        String name = null;
        if(!isElementDisplayed(noUserFound)){
            int rowSize = driver.findElements(By.xpath(String.valueOf(usersTable))).size();
            for (int row = 1; row <= rowSize; row++){
                for (int col = 3; col <= 4; col++){
                    name = driver.findElement(By.xpath(table() + "//tr[" + row + "]//" + "td[" + col + "]")).getText();
                    if(name.equals(userName)) {
                        driver.findElement(By.xpath(table() + "//tr[" + row + "]" + "//td[6]//div[1]//a[1]")).click();
                    }
                }
            }
        }
        return PageFactory.viewUserDetailsPage(driver);
    }
    public ViewUserDetailsPage editUserDetails(String userName){
        WebElement edit = null;
        String name = null;
        if(!isElementDisplayed(noUserFound)){
            int rowSize = driver.findElements(By.xpath(String.valueOf(usersTable))).size();
            for (int row = 1; row <= rowSize; row++){
                for (int col = 3; col <= 4; col++){
                    name = driver.findElement(By.xpath(table() + "//tr[" + row + "]//" + "td[" + col + "]")).getText();
                    if(name.equals(userName)) {
                        driver.findElement(By.xpath(table() + "//tr[" + row + "]" + "//td[6]//div[1]//a[2]")).click();
                    }
                }
            }
        }
        return PageFactory.viewUserDetailsPage(driver);
    }
    public void deleteSelectedUser(String userName){
        if(!isElementDisplayed(noUserFound)){
            String name = null;
            List<WebElement> rowSize = usersTable.findElements(By.tagName("tr"));
            for (int row = 1; row <= rowSize.size(); row++){
                for (int col = 3; col <= 4; col++){
                    name = driver.findElement(By.xpath(table() + "//tr[" + row + "]//" + "td[" + col + "]")).getText();
                    if(name.equals(userName)) {
                        driver.findElement(By.xpath(table() + "//tr[" + row + "]" + "//td[6]//div[1]//a[3]")).click();
                        driver.switchTo().alert().accept();
                    }
                }
            }
        }
    }
    public List<String> getListOfDates(){
        List<String> dates = new LinkedList<>();
        List<WebElement> rowSize = usersTable.findElements(By.tagName("tr"));
            for (int rows = 1; rows <= rowSize.size(); rows++){
                String createdDate = driver.findElement(By.xpath(dataPath() + "[" + rows + "]" +  "//td[5]")).getText();
                System.out.println(createdDate);
                dates.add(createdDate);
            }
        return dates;
    }
}
