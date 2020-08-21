package steps;

import api.models.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.TestData;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CommentsSteps {

    private static final String ACTUAL_COMMENTS_RESPONSE_KEY = "actual-comments-response";
    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String JSON_CONTENT_TYPE_HEADER_VALUE = "application/json";
    private static final String COMMENTS_ENDPOINT = "/comments";

    @Given("that the following comments are present in the system")
    public void thatTheFollowingCommentsArePresentInTheSystem(List<Comment> givenComments) {
    }

    @When("the user requests to read all comments")
    public void theUserRequestsToReadAllComments() {
        Response response =
                given()
                        .header(CONTENT_TYPE_HEADER_NAME, JSON_CONTENT_TYPE_HEADER_VALUE)
                .when()
                        .get(COMMENTS_ENDPOINT)
                .thenReturn();

        TestData.currentScenario().put(ACTUAL_COMMENTS_RESPONSE_KEY, response);
    }

    @Then("OK response containing the following comments should be returned to the user")
    public void okResponseContainingTheFollowingCommentsShouldBeReturnedToTheUser(List<Comment> expectedComments) throws JsonProcessingException {
        Response response = (Response) TestData.currentScenario().get(ACTUAL_COMMENTS_RESPONSE_KEY);
        List<Comment> actualComments = response.jsonPath().getList(".", Comment.class);
        final ObjectMapper mapper = new ObjectMapper();

        assertThat(response.getStatusCode(), is(200));
        assertThat(mapper.writeValueAsString(actualComments),
                is(equalTo(mapper.writeValueAsString(expectedComments))));
    }
}
