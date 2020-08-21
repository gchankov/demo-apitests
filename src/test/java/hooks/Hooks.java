package hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import utils.TestData;

public class Hooks {

    @Before
    public void beforeScenario() {
        RestAssured.baseURI = TestData.environment().getProperty("baseUri");
        RestAssured.port = Integer.parseInt(TestData.environment().getProperty("port"));
        RestAssured.basePath = TestData.environment().getProperty("basePath");

        TestData.reset();
    }
}
