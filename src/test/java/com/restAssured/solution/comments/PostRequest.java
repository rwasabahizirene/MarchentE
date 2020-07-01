package com.restAssured.solution.comments;

import com.restAssured.solution.pojos.comments.Comments;
import com.restAssured.solution.utilities.DataHandler;
import com.restAssured.solution.utilities.PropertyHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.*;

import static com.restAssured.solution.specBuilder.SpecBuilder.requestSpecification;
import static com.restAssured.solution.specBuilder.SpecBuilder.responseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PostRequest {
    List<Comments> wrongData;
    static List<Comments> newJsonObject = new LinkedList<>();
    List<Comments> returnedUserData = new ArrayList<>();
    List<Comments> comments;
    String commentsPutFilePath = PropertyHandler.getInstance().getValue("CommentsPutBody");
    String commentsPostFilePath = PropertyHandler.getInstance().getValue("CommentsPostBody");

    @Test(priority = 1)
    public void httpPostCall(){
        comments = DataHandler.commentsMessageBody(commentsPostFilePath);
        for (Comments data : comments) {
            data.setPostId(randomNum());
            data.setId(randomNum());
            newJsonObject.add(data);
            Response postResponse =
            given()
                .spec(requestSpecification())
                .body(data)
                .when()
                .post("/comments")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(201)
                .body("postId", equalTo(data.getPostId()))
                .body("name", equalTo(data.getName()))
                .extract().response();
        }
        DataHandler.objectToJson(newJsonObject,commentsPutFilePath);
        DataHandler.objectToJson(newJsonObject,commentsPostFilePath);
    }

    @Test(priority = 2)
    public void validatePostWithGetRequest(){
        wrongData = DataHandler.commentsMessageBody(PropertyHandler.getInstance().getValue("CommentsWrongData"));
        comments = DataHandler.commentsMessageBody(PropertyHandler.getInstance().getValue("CommentsPostBody"));
        for (Comments data : comments) {
            JsonPath getComments =
            given()
                .spec(requestSpecification())
                .param("id", data.getId())
                .when()
                .get("/comments")
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .extract().body().jsonPath();
            List<Comments> responseObject = getComments.getList("", Comments.class);
            Comments comment = responseObject.get(0);
            returnedUserData.add(comment);
        }
        assertEquals(returnedUserData,comments);
        //assertEquals(returnedUserData,wrongData); Uncomment to run a negative scenario
    }

    public static int randomNum(){
        Random rand = new Random();
        int n = rand.nextInt(23455) + 72323;
        return n;
    }
}
