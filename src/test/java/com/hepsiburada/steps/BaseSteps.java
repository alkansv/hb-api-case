package com.hepsiburada.steps;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class BaseSteps {

    protected static Response response;
    private static final Logger LOGGER = LogManager.getLogger();

    @Step("Check status code <code> control")
    public void checkStatusCode(String code) {
        LOGGER.info("checked " + code + " equals " + response.getStatusCode());
        Assertions.assertEquals(code, String.valueOf(response.getStatusCode()));
    }
}
