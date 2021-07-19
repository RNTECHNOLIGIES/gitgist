package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.Logger;
import base.*;
import Utility.LoggerHelper;
import org.hamcrest.core.Is;
import org.json.JSONException;
import org.junit.Assert;

import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.testng.Assert.assertEquals;

public class CreateGistStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);

    @Given("Header authentication")
    public void headerAuthentication() {
        try {
            initheader();
        } catch (Exception e) {
            Assert.fail("ERROR: Header Authentication failed " + ": " + e);
        }
    }

    @And("^set the basepath(.*)$")
    public void setTheBasepathGist(String basepathCreateGist) {
        try {
            basepath(basepathCreateGist);
        } catch (Exception e) {
            Assert.fail("ERROR: Faild to set the base path " + ": " + e);
        }
    }


    @Given("The user set the parameter with below combinations description")
    public void theUserSetTheParameterWithbelowCombinationsDescription(Map<String, String> resquestFields) {
        try {
            setGistDescription(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to set the gist description " + ": " + e);
        }
    }


    @And("The user set the parameter with below combinations of object public")
    public void theUserSetTheParameterWithbelowCombinationsOfObjectPublic(Map<String, String> resquestFields) {
        try {
            setGistDescription(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to set the gist object public " + ": " + e);
        }
    }


    @And("The user set the parameter with below combinations for creating gist files")
    public void theUserSetTheParameterWithBelowCombinationsForCreatingGistFiles(Map<String, String> gistFiles) {
        try {
            setGistFiles(gistFiles);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to set the gist object for file " + ": " + e);
        }
    }


    @When("The user send a post request")
    public void theUserSendAPostRequest() {
        try {
            response = request.body(requestObjectCreategist.toString()).post();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send the post request" + ": " + e);
        }
    }

    @Then("^The response status code should be (.*)$")
    public void TheresponseStatusCodeShouldBe(int arg0) {
        response.then().statusCode(arg0).log().all();
    }

    @And("^I validate response content \"([^\"]*)\" \"([^\"]*)\"$")
    public void iValidateResponseContent(String arg0, String arg1) {
        String ColumnData = response.jsonPath().getString(arg0);
        log.info("\nExpected Value: " + arg1 + "\nActual Value: " + ColumnData);

        if (ColumnData.contains(arg1) == true) {
            log.info("Result: Test Passed");
        } else {
            log.info("Result: Test Failed");
            assertEquals(ColumnData, arg1);
        }
    }

    @And("^The response fields should match with below objects$")
    public void theResponseFieldsShouldMatchWithBelowObjects(Map<String, String> resquestFields) {
        try {
            theResponseFieldsShouldMatch(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: The response field validation failed" + ": " + e);
        }
    }

    @Given("^The user call for service with no authorization$")
    public void theUserCallForServiceWithNoAuthorization() {

        unauthheader();
    }


    @And("^The response body should match the JSON schema response objects(.*)$")
    public void theResponseBodyShouldMatchTheJSONSchemaResponseObjects(String schemafile) {
        try {
            jsonSchemaValidation(schemafile.trim());
        } catch (Exception e) {
            Assert.fail("ERROR: JSON schema fail to match: " + e);
        }

    }
}
