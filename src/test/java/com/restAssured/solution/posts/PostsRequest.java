package com.restAssured.solution.posts;

import com.restAssured.solution.pojos.posts.Posts;
import com.restAssured.solution.utilities.DataHandler;
import com.restAssured.solution.utilities.PropertyHandler;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.restAssured.solution.specBuilder.SpecBuilder.requestSpecification;
import static com.restAssured.solution.specBuilder.SpecBuilder.responseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PostsRequest {
    List<Posts> wrongData;
    List<Posts> returnedPostData = new ArrayList<>();
    static List<Posts> newJsonObject = new LinkedList<>();
    List<Posts> postsData;
    String postsPutFilePath = PropertyHandler.getInstance().getValue("PutsApiPostBodyData");
    String postsPostFilePath = PropertyHandler.getInstance().getValue("PostsApiPostBodyData");
    
    @Test(priority = 1)
    public void httpPostCall(){
       postsData = DataHandler.postMessageBody(postsPostFilePath);
       for (Posts data : postsData) {
           data.setUserId(randomNum());
           data.setId(randomNum());
           newJsonObject.add(data);
           Response createNewPost =
           given()
               .spec(requestSpecification())
               .body(data)
               .when()
               .post("/posts")
               .prettyPeek()
               .then()
               .spec(responseSpecification())
               .assertThat()
               .statusCode(201)
               .body("userId", equalTo(data.getUserId()))
               .body("id", equalTo(data.getId()))
               .extract().response();
       }
        DataHandler.objectToJson(newJsonObject, postsPutFilePath);
        DataHandler.objectToJson(newJsonObject, postsPostFilePath);
    }

    @Test(priority = 2)
    public void validatePostWithGetRequest(){
        wrongData = DataHandler.postMessageBody(PropertyHandler.getInstance().getValue("PostsWrongData"));
        postsData = DataHandler.postMessageBody(postsPostFilePath);
        for (Posts data : postsData) {
            JsonPath getPosts =
            given()
                .spec(requestSpecification())
                .param("id", data.getId())
                .when()
                .get("/posts")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .extract().body().jsonPath();
            List<Posts> responseObject = getPosts.getList("", Posts.class);
            Posts posts = responseObject.get(0);
            returnedPostData.add(posts);
        }
        assertEquals(returnedPostData,postsData);
        //assertEquals(returnedUserData,wrongData); Uncomment to run a negative scenario
    }

    public static int randomNum(){
        Random rand = new Random();
        int n = rand.nextInt(23455) + 72323;
        return n;
    }
}
