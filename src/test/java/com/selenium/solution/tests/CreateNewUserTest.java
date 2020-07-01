package com.selenium.solution.tests;

import com.selenium.solution.pages.AdminPage;
import com.selenium.solution.pages.NewUserPage;
import com.selenium.solution.pages.UsersHomePage;
import com.selenium.solution.pages.ViewUserDetailsPage;
import com.selenium.solution.utilities.PageFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class CreateNewUserTest extends BaseTest{

    @Test
    @Parameters({"name", "email", "password", "newName", "newEmail"})
    public void createNewUser(String name, String email, String password, String newName, String newEmail){
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();
        NewUserPage newUserPage =  userPage.clickNewUserButton();
        ViewUserDetailsPage userDetailsPage = newUserPage.createUser(name, email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userDetailsPage.getUserName(),name);
        softAssert.assertEquals(userDetailsPage.getUserEmail(),email);

        newUserPage = userDetailsPage.editUser();
        userDetailsPage = newUserPage.createUser(newName, newEmail, password);
        softAssert.assertEquals(userDetailsPage.getUserName(),newName);
        softAssert.assertEquals(userDetailsPage.getUserEmail(),newEmail);
        softAssert.assertAll();

        userDetailsPage.pauseExecution();
    }

    @Test
    @Parameters({"userName", "userEmail", "userPassword","firstUser"})
    public void createsAndDeleteUsers(String name, String email, String password, String firstUser){
        Map<String, String> userTable;

        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();
        NewUserPage newUserPage =  userPage.clickNewUserButton();
        ViewUserDetailsPage userDetailsPage = newUserPage.createUser(name, email, password);
        userPage = userDetailsPage.deleteUser();
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), not(Matchers.equalTo(name)));
        }
        userPage.filterUserNameByEquals();
        userPage.setUserNameValue(firstUser);
        userPage.applyFilter();
        userPage.deleteSelectedUser(firstUser);
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), not(Matchers.equalTo(firstUser)));
        }
    }

    @Test
    @Parameters({"userName", "userEmail", "userPassword"})
    public void createUserErrorValidation(String name, String email, String password){
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();
        NewUserPage newUserPage =  userPage.clickNewUserButton();
        ViewUserDetailsPage userDetailsPage = newUserPage.createUser(name, email, password);
        userPage = userDetailsPage.navigateToUserTab();
        newUserPage = userPage.clickNewUserButton();
        newUserPage.createWrongUser(name, email);
        assertThat(newUserPage.getUserNameError(), equalTo("has already been taken"));
        assertThat(newUserPage.getUserPasswordError(), equalTo("can't be blank"));
        assertThat(newUserPage.getUserEmailError(), equalTo("has already been taken"));

        newUserPage.createUser("e", "test@", "1");
        assertThat(newUserPage.getUserPasswordError(), equalTo("is too short (minimum is 4 characters)"));
        assertThat(newUserPage.getUserEmailError(), equalTo("is invalid"));

        userPage = newUserPage.cancelUserCreation();
        userPage.filterUserNameByEquals();
        userPage.setUserNameValue(name);
        userPage.applyFilter();
        userPage.deleteSelectedUser(name);
    }
}
