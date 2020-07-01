package com.restAssured.solution.posts;

import com.restAssured.solution.pojos.posts.Posts;
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
    List<Posts> wrongData;
    List<Posts> returnedPostData = new ArrayList<>();
    List<Posts> postsData;
    String postsPostFilePath = PropertyHandler.getInstance().getValue("PostsApiPostBodyData");
    String postsPutFilePath = PropertyHandler.getInstance().getValue("PutsApiPostBodyData");

    @Test(priority = 1)
    public void httpPutCall(){
        postsData = DataHandler.postMessageBody(postsPostFilePath);
        for (Posts data : postsData) {
            Response createNewPost =
            given()
                .spec(requestSpecification())
                .body(data)
                .pathParam("id", data.getId())
                .when()
                .put("/posts/{id}")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .body("userId", equalTo(data.getUserId()))
                .body("id", equalTo(data.getId()))
                .extract().response();
        }
    }

    @Test(priority = 2)
    public void validatePutWithGetRequest(){
        wrongData = DataHandler.postMessageBody(PropertyHandler.getInstance().getValue("PostsWrongData"));
        postsData = DataHandler.postMessageBody(postsPutFilePath);
        for (Posts data : postsData) {
            JsonPath getPosts =
            given()
                .spec(requestSpecification())
                .param("id", data.getId())
                .when()
                .get("/posts/")
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
}
