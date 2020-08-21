package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestData {

    private static final String ENVIRONMENT_PROPERTIES_FILE_PATH = "src/test/resources/testData/environments/%e.properties";
    private static Map<String, Object> currentScenarioTestData = null;
    private static Properties environment = null;

    private TestData() {}

    public static Map<String, Object> currentScenario() {
        if (currentScenarioTestData == null) {
            currentScenarioTestData = new HashMap<String, Object>();
        }

        return currentScenarioTestData;
    }

    public static Properties environment() {
        if (environment == null) {
            try {
                environment = System.getProperties();
                String targetEnvironmentPath = ENVIRONMENT_PROPERTIES_FILE_PATH
                        .replace("%e", environment.getProperty("target.environment", "tst"));

                environment.load(new FileInputStream(new File(targetEnvironmentPath)));
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return environment;
    }

    public static void reset() {
        currentScenarioTestData = new HashMap<String, Object>();
    }
}
