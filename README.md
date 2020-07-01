# MarchentE
API and UI automation test interview test

Framework Overview
This is a maven java project and the POM xml contains all required dependancies. All utilities, helper classes and POJOs are under src/main directory and
all test classes are under src/test directory. All test data can be found in src/test/resources and they are pointed to by a property handler class that maintains their location
in a single place. 

The framework comprise of two solution for the interview assignement.restassured based solution for API automation testing and selenium based solution for UI automation testing
  Rest Assured based solution
    In src/main/java/com/restassured.solution, Thre are sub-directories that contain class representation of each resource under test
    In src/main/java/com/restassured.solution, There are sub-directories that contain test class representing the tests done for each resource 
    In src/main/java/com/reports, is where the report will be stored
  Selenium based solution
    In src/test/java/com/selenium.solution, There are sub-directories that contain class representation of each page under test
    In src/test/java/com/selenium.solution, There are three classes. BaseTest for test setup, CreateNewUserTest for testing creating user functionality and UserFilters for filtering users
    In src/test/java/com/extentReports, There are extent report configuration related classes
    
How to run the test
  Using intellij
    I have preconfigured three custom maven based run configuration that i beleive should be setup if you open the project in Intellij.
    NOTE: Change the POM maven plugin tag <suiteXmlFile></suiteXmlFile> to the xml you want to run
      UI_Test_Solution: This will run the selenium based solution using maven below command. 
                        (mvn clean test -DtestSuiteFile=src/test/resources/testSuites/UI_Test_Solution.xml -DplatformPropertyFile=src/main/resources/interview.properties)
                        <suiteXmlFile>src/test/resources/testSuites/UI_Test_Solution.xml</suiteXmlFile>
      API_Test_Solutio: This will run the restassured based solution using below command.
                        (mvn clean test -DtestSuiteFile=src/test/resources/testSuites/API_Test_Solution.xml -DplatformPropertyFile=src/main/resources/interview.properties)
                        <suiteXmlFile>src/test/resources/testSuites/API_Test_Solution.xml</suiteXmlFile>                        
      Run_All_Test_Solution:This will run selenium and restassured based solution in a single run
                            (mvn clean test -DtestSuiteFile=src/test/resources/testSuites/Run_All_Suite.xml -DplatformPropertyFile=src/main/resources/interview.properties)
                            <suiteXmlFile>src/test/resources/testSuites/Run_All_Suite.xml</suiteXmlFile>

Framework and Libraries used
  Selenium
  TestNg
  Rest Assured
  Gson
  Bonigarcia
  Extent Report
  Hamcrest
Selenium Based Solution
  This project is implemented using the Page Object Model design pattern
