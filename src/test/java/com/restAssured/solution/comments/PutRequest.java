package com.restAssured.solution.comments;

import com.restAssured.solution.pojos.comments.Comments;
import com.restAssured.solution.utilities.DataHandler;
import com.restAssured.solution.utilities.PropertyHandler;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static com.restAssured.solution.specBuilder.SpecBuilder.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class PutRequest {

    List<Comments> wrongData;
    List<Comments> returnedCommentData = new ArrayList<>();
    List<Comments> comments;

    @Test(priority = 1)
    public void httpPutCall(){
        comments = DataHandler.commentsMessageBody(PropertyHandler.getInstance().getValue("CommentsPutBody"));
        for (Comments data : comments) {
            Response httpPutResponse =
            given()
                .spec(requestSpecification())
                .body(data)
                .pathParam("id", data.getId())
                .when()
                .put("/comments/{id}" )
                .prettyPeek()
                .then()
                .spec(responseSpecification())
                .assertThat()
                .statusCode(200)
                .body("postId", equalTo(data.getPostId()))
                .body("name", equalTo(data.getName()))
                .extract().response();
        }
    }

    @Test(priority = 2)
    public void validatePutWithGetRequest(){
        wrongData = DataHandler.commentsMessageBody(PropertyHandler.getInstance().getValue("CommentsWrongData"));
        comments = DataHandler.commentsMessageBody(PropertyHandler.getInstance().getValue("CommentsPutBody"));
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
            returnedCommentData.add(comment);
        }
        assertEquals(returnedCommentData,comments);
       // assertEquals(returnedUserData,wrongData); //Uncomment to run a negative scenario
    }
}
