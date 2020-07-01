package com.selenium.solution.tests;

import com.selenium.solution.pages.AdminPage;
import com.selenium.solution.pages.UsersHomePage;
import com.selenium.solution.utilities.PageFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserFiltersTest extends BaseTest{

    @Test
    @Parameters({"name", "email"})
    public void filter_User_By_Name_Email_Equals(String name, String email){
        Map<String, String> userTable ;
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();

        userPage.filterUserNameByEquals();
        userPage.setUserNameValue(name);
        userPage.filterUserEmailByEquals();
        userPage.setUserEmailValue(email);
        userPage.applyFilter();
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), Matchers.equalTo(email));
        }
    }

    @Test
    @Parameters({"name", "email"})
    public void filter_User_By_Name_Email_Contains(String name, String email){
        Map<String, String> userTable ;
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();

        userPage.filterUserNameByContains();
        userPage.setUserNameValue(name);
        userPage.filterUserEmailByContains();
        userPage.setUserEmailValue(email);
        userPage.applyFilter();
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), Matchers.containsStringIgnoringCase(email));
        }
    }

    @Test
    @Parameters({"name", "email"})
    public void filter_User_By_Name_Email_StartsWith(String name, String email){
        Map<String, String> userTable ;
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();

        userPage.filterUserNameByStartsWith();
        userPage.setUserNameValue(name);
        userPage.filterUserEmailByStartsWith();
        userPage.setUserEmailValue(email);
        userPage.applyFilter();
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), Matchers.startsWithIgnoringCase(email));
        }
    }

    @Test
    @Parameters({"name", "email"})
    public void filter_User_By_Name_Email_EndsWith(String name, String email){
        Map<String, String> userTable ;
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();

        userPage.filterUserNameByEndsWith();
        userPage.setUserNameValue(name);
        userPage.filterUserEmailByEndsWith();
        userPage.setUserEmailValue(email);
        userPage.applyFilter();
        userTable = userPage.getTableValues();
        for (Map.Entry<String, String> entry : userTable.entrySet()){
            assertThat(entry.getValue(), Matchers.endsWithIgnoringCase(email));
        }
    }

    @Test
    @Parameters({"startDate", "endDate"})
    public void filter_User_By_Date_From_To(String startDate, String endDate){
        List<String> dates = new LinkedList<>();
        AdminPage adminPage = PageFactory.adminPage(driver);
        UsersHomePage userPage = adminPage.navigateToUserTab();

        userPage.setStartDate(startDate);
        userPage.setEndDate(endDate);
        userPage.applyFilter();
        dates = userPage.getListOfDates();
    }
}
