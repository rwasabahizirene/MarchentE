package com.restAssured.solution.users;

import com.restAssured.solution.pojos.posts.Posts;
import com.restAssured.solution.pojos.users.User;
import com.restAssured.solution.utilities.DataHandler;
import com.restAssured.solution.utilities.PropertyHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.restAssured.solution.specBuilder.SpecBuilder.requestSpecification;
import static com.restAssured.solution.specBuilder.SpecBuilder.responseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PostRequest {
    List<User> usersData;
    List<User> wrongData;
    List<User> returnedUserData = new ArrayList<>();
    List<User> newJsonObject = new LinkedList<>();
    String usersPutFilePath = PropertyHandler.getInstance().getValue("UsersPutData");
    String usersPostFilePath = PropertyHandler.getInstance().getValue("UsersPostData");

    @Test(priority = 1)
    public void httpPostRequest(){
        usersData = DataHandler.usersMessageBody(usersPostFilePath);
        for (User data : usersData) {
            data.setId(randomNum());
            newJsonObject.add(data);
            Response createNewPost =
            given()
                .spec(requestSpecification())
                .body(data)
                .when()
                .post("/users")
                .prettyPeek()
                .then()
                .assertThat()
                .spec(responseSpecification())
                .statusCode(201)
                .body("id",equalTo(data.getId()))
                .body("address.geo.lat",equalTo(data.address.geo.getLat()))
                .extract().response();
        }
        DataHandler.objectToJson(newJsonObject, usersPutFilePath);
        DataHandler.objectToJson(newJsonObject, usersPostFilePath);
    }


    @Test(priority = 2)
    public void validatePostWithGetRequest(){
        wrongData = DataHandler.usersMessageBody(PropertyHandler.getInstance().getValue("UsersWrongData"));
        usersData = DataHandler.usersMessageBody(usersPostFilePath);
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
        //assertEquals(returnedUserData,wrongData); Uncomment to run a negative scenario
    }

    public static int randomNum(){
        Random rand = new Random();
        int n = rand.nextInt(23455) + 72323;
        return n;
    }
}
