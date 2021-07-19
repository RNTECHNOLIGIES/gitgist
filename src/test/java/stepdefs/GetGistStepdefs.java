package stepdefs;

import Utility.LoggerHelper;
import base.base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertEquals;


public class GetGistStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);


    @And("^set the gistget basepath (.*)$")
    public void setTheGistgetBasepathGistsId(String basepath) {
        try {
            basepath(basepath);
        } catch (Exception e) {
            Assert.fail("ERROR:  get gist base path gist id has been failed " + ": " + e);

        }
    }

    @Given("^The user send a get request to fetch different gist_id$")
    public void theUserSendAGetRequestToFetchDifferentGist_id() {
        try {
            fetchgistid();
        } catch (Exception e) {
            Assert.fail("ERROR:  fetching gist id has been failed " + ": " + e);
        }
    }

    @And("^The user send a call get gist service request with fetched gist_id$")
    public void theUserSendACallGetGistServiceRequestWithFetchedGist_id() {
        try {
            getgistsetpathParam();
        } catch (Exception e) {
            Assert.fail("ERROR:  fetch gist id has been failed " + ": " + e);

        }
    }


    @And("^The user set the gistId and base path (.*)$")
    public void theUserSetTheGistIdAndBasePathGistsId(String basepath) {
        try {
            getsetthebasePath(basepath);
        } catch (Exception e) {
            Assert.fail("ERROR: Get the service with path has failed " + ": " + e);

        }

    }


    @And("^Gistget Request Header authentication$")
    public void gistgetRequestHeaderAuthentication() {
        try {
            initheader();
        } catch (Exception e) {
            Assert.fail("ERROR: Header Authentication failed " + ": " + e);
        }
    }

    @And("^The Get gist service response code should be (\\d+)$")
    public void theGetGistServiceResponseCodeShouldBe(int arg0) {
        try {
            ResponseCodeShouldBe(arg0);
        } catch (Exception e) {
            Assert.fail("ERROR: service status code verification failed " + ": " + e);
        }
    }


    @Then("^The response fields should match with below get gist file objects$")
    public void theResponseFieldsShouldMatchWithBelowGetGistFileObjects(Map<String, String> resquestFields) {
        try {
            theResponseFieldsShouldMatch(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: response validation error " + ": " + e);
        }
    }

    @Given("^Gistget Request with unauthorized header$")
    public void gistgetRequestWithUnauthorizedHeader() {
        try {
            unauthheader();
        } catch (Exception e) {
            Assert.fail("ERROR: header inititation failed with error " + ": " + e);
        }
    }

    @And("^The response body should match the get gist JSON schema response objects (.*)$")
    public void theResponseBodyShouldMatchTheGetGistJSONSchemaResponseObjectsGet_gist_schemaJson(String schemafile) {
        try {
            jsonSchemaValidation(schemafile.trim());
        } catch (Exception e) {
            Assert.fail("ERROR: JSON schema fail to match: " + e);
        }
    }
}
