package com.restAssured.solution.users;

import com.restAssured.solution.pojos.users.User;
import com.restAssured.solution.utilities.DataHandler;
import com.restAssured.solution.utilities.PropertyHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.restAssured.solution.specBuilder.SpecBuilder.requestSpecification;
import static com.restAssured.solution.specBuilder.SpecBuilder.responseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class PutRequest {
    List<User> usersData;
    List<User> wrongData;
    List<User> returnedUserData = new ArrayList<>();
    String usersPutFilePath = PropertyHandler.getInstance().getValue("UsersPutData");

    @Test(priority = 3)
    public void httpPutCall(){
        usersData = DataHandler.usersMessageBody(usersPutFilePath);
        for (User data : usersData) {
            Response updateUser =
            given()
                .spec(requestSpecification())
                .body(data)
                .pathParam("id", data.getId())
                .when()
                .put("/users/{id}")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(data.getId()))
                .extract().response();
        }
    }

    @Test(priority = 4)
    public void validatePutWithGetRequest(){
        wrongData = DataHandler.usersMessageBody(PropertyHandler.getInstance().getValue("UsersWrongData"));
        usersData = DataHandler.usersMessageBody(usersPutFilePath);
        for (User data : usersData) {
            JsonPath getUser =
            given()
                .spec(requestSpecification())
                .param("id", data.getId())
                .when()
                .get("/users")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .extract().body().jsonPath();
            List<User> responseObject = getUser.getList("", User.class);
            User users = responseObject.get(0);
            returnedUserData.add(users);
        }
        assertEquals(returnedUserData,usersData);
        //assertEquals(returnedUserData,wrongData); Uncomment to run negative scenario
    }
}
