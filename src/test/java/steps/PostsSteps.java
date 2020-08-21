package steps;

import api.models.Post;
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

public class PostsSteps {

    private static final String ACTUAL_POSTS_RESPONSE_KEY = "actual-posts-response";
    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String JSON_CONTENT_TYPE_HEADER_VALUE = "application/json";
    private static final String POSTS_ENDPOINT = "/posts";

    @Given("that the following posts are present in the system")
    public void thatTheFollowingPostsArePresentInTheSystem(List<Post> posts) {
    }

    @When("the user requests to read all posts")
    public void theUserRequestsToReadAllPosts() {
        Response response =
                given()
                        .header(CONTENT_TYPE_HEADER_NAME, JSON_CONTENT_TYPE_HEADER_VALUE)
                .when()
                        .get(POSTS_ENDPOINT)
                .thenReturn();

        TestData.currentScenario().put(ACTUAL_POSTS_RESPONSE_KEY, response);
    }

    @Then("OK response containing the following posts should be returned to the user")
    public void okResponseContainingTheFollowingPostsShouldBeReturnedToTheUser(List<Post> expectedPosts) throws JsonProcessingException {
        Response response = (Response) TestData.currentScenario().get(ACTUAL_POSTS_RESPONSE_KEY);
        List<Post> actualPosts = response.jsonPath().getList(".", Post.class);
        final ObjectMapper mapper = new ObjectMapper();

        assertThat(response.getStatusCode(), is(200));
        assertThat(mapper.writeValueAsString(actualPosts),
                is(equalTo(mapper.writeValueAsString(expectedPosts))));
    }
}
