package stepdefs;

import Utility.LoggerHelper;
import base.base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Map;

import static org.testng.Assert.assertEquals;

;

public class DeleteGistStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);


    @And("^set the gistdelete basepath (.*)$")
    public void setTheGistdeleteBasepathGistsId(String basepath) {
        try {
            basepath(basepath);
        } catch (Exception e) {
            Assert.fail("ERROR: Found error while setting up base path " + ": " + e);
        }
    }

    @Given("^The user send a request to fetch different gist_id$")
    public void theUserSendAGetRequestToFetchDifferentGist_id() {
        try {
            fetchgistid();
        } catch (Exception e) {
            Assert.fail("ERROR: To fetch different gist_id failed " + ": " + e);
        }
    }

    @And("^The user send a call delete gist service request with fetched gist_id$")
    public void theUserSendACallGetGistServiceRequestWithFetchedGist_id() {
        try {
            request.pathParam("id", fetchgistid());
        } catch (Exception e) {
            Assert.fail("ERROR: To fetch different gist_id failed " + ": " + e);
        }
    }

    @And("^The user set the gistId and basepath for delete (.*)$")
    public void theUserSetTheGistIdAndBasePathGistsId(String basepath) {
        try {
            response = request.when().delete(basepath).prettyPeek();
        } catch (Exception e) {
            Assert.fail("ERROR: Error while deleting records " + ": " + e);
        }
    }


    @And("^Gistdelete Request Header authentication$")
    public void gistdeleteRequestHeaderAuthentication() {
        try {
            initheader();
        } catch (Exception e) {
            Assert.fail("ERROR: Header Authentication failed " + ": " + e);
        }
    }

    @When("^The Get gist delete service response code should be (\\d+)$")
    public void theGetGistServiceResponseCodeShouldBe(int arg0) {
        try {
            ResponseCodeShouldBe(arg0);
        } catch (Exception e) {
            Assert.fail("ERROR: service status code verification failed " + ": " + e);
        }

    }


    @Then("^The response fields should match with below delete gist file objects$")
    public void theResponseFieldsShouldMatchWithBelowGetGistFileObjects(Map<String, String> resquestFields) {
        try {
            theResponseFieldsShouldMatch(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: response validation error " + ": " + e);
        }

    }


    @Given("^The user call for  delete gist service with no authorization$")
    public void theUserCallForDeleteGistServiceWithNoAuthorization() {
        try {
            unauthheader();
        } catch (Exception e) {
            Assert.fail("ERROR: found error while initalizing authorizated header " + ": " + e);
        }
    }
}
