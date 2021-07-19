package stepdefs;

import Utility.LoggerHelper;
import base.base;
import com.google.common.base.Strings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Map;

import static org.testng.Assert.assertEquals;


public class RatelimitStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);

    @Given("^Rate limit Header request for authenticated user$")
    public void rateLimitHeaderRequestForAuthenticatedUserHeader() {
        initheader();
    }


    @And("^Rate limit set the basepath (.*)$")
    public void rateLimitSetTheBasepathRate_limit(String str) {
        basepath(str);
    }

    @And("^The user send a call to rate limit service request$")
    public void theUserSendACallToRateLimitServiceRequest() {
        response = request.when().get().prettyPeek();
    }

    @When("^The rate limit service response code should be (\\d+)$")
    public void theRateLimitServiceResponseCodeShouldBe(int arg0) {
        response.then().statusCode(arg0);
    }

    @Then("^The response fields should match with below rate limit objects$")
    public void theResponseFieldsShouldMatchWithBelowRateLimitObjects(Map<String, String> resquestFields) {

        try {
            theResponseFieldsShouldMatch(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: response validation error " + ": " + e);
        }
    }

    @And("^Validate the response header for ratelimit$")
    public void validateTheResponseHeaderForRatelimit(Map<String, String> resquestFields) {
        try {
            validateTheResponseHeaderForRatelimitfornull(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: response validation error for null value " + ": " + e);
        }


    }

    @Given("^Rate limit Header request for unauthenticated user$")
    public void rateLimitHeaderRequestForUnauthenticatedUser() {
        unauthheader();
    }
}
