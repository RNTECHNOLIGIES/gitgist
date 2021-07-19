package stepdefs;

import Utility.LoggerHelper;
import base.base;
import com.google.common.base.Strings;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Map;

import static org.testng.Assert.assertEquals;


public class ListGistStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);

    @Given("^GistList Request Header authentication$")
    public void gistlistRequestHeaderAuthentication() {
        try {
            initheader();
        } catch (Exception e) {
            Assert.fail("ERROR: Header Authentication failed " + ": " + e);
        }

    }


    @And("^GistList set the basepath (.*)$")
    public void gistlistSetTheBasepathGists(String basepath) {
        try {
            basepath(basepath);
        } catch (Exception e) {
            Assert.fail("ERROR:  get gist base path gist id has been failed " + ": " + e);

        }
    }

    @Given("^The user send a call to List gist service request$")
    public void theUserSendACallToListGistServiceRequest() {

        response = request.when().get();
    }

    @When("^The List gist service response code should be (\\d+)$")
    public void theListGistServiceResponseCodeShouldBe(int arg0) {
        try {
            ResponseCodeShouldBe(arg0);
        } catch (Exception e) {
            Assert.fail("ERROR: service status code verification failed " + ": " + e);
        }

    }

    @Then("^The response fields should match with below List gist file objects$")
    public void theResponseFieldsShouldMatchWithBelowListGistFileObjects(Map<String, String> resquestFields) {
        try {
            theResponseFieldsShouldMatch(resquestFields);
        } catch (Exception e) {
            Assert.fail("ERROR: response validation error " + ": " + e);
        }

    }

    @Given("^The user send a call to List gist service request with different parameter$")
    public void theUserSendACallToListGistServiceRequestWithDifferentParameter(Map<String, String> resquestFields) {
        for (Map.Entry<String, String> field : resquestFields.entrySet()) {
            request.queryParam(field.getKey(), field.getValue());

        }
    }


    @Given("^The user send a call to List gist service request with (.*) page parameter(.*)$")
    public void theUserSendACallToListGistServiceRequestWithpageParameter(String page, String number) {
        request.queryParam(page, number).log().all();
    }

    @Given("^The user send a call to List gist service request with (.*) perpage parameter(.*)$")
    public void theUserSendACallToListGistServiceRequestWithperpageParameter(String page, String number) {
        request.queryParam(page, number).log().all();

    }


    @And("^Gistaccess set the basepath (.*)$")
    public void gistaccessSetTheBasepathGists(String list) {
        basepath(list);
    }


    @Given("^Header request for authentication (.*)$")
    public void HeaderRequestForAuthentication(String str) {
        gistraw(str);
    }

    @Given("^header request for with out authentication token (.*)$")
    public void headerRequestForWithOutAuthenticationTokenHeader(String str) {
        gistrawithouttoken(str);
    }

    @And("^Validate the response header for Gists accessibility test$")
    public void validateTheResponseHeaderForGistsAccessibilityTest(Map<String, String> resquestFields) {
        for (Map.Entry<String, String> field : resquestFields.entrySet()) {

            String ColumnData = response.header(field.getKey());
            log.info(field.getKey() + "\nExpected Value: " + field.getValue() + "\nActual Value: " + ColumnData);

            if (field.getValue().equalsIgnoreCase("null")) {
                assertEquals((Strings.nullToEmpty(response.header(field.getKey())).length()), 0);
                log.info("Result: Test Passed");
            } else if (ColumnData.contains(field.getValue()) == true) {
                assertEquals(ColumnData, field.getValue());
                log.info("Result: Test Passed");
            } else {
                log.info("Result: Test Failed");
                assertEquals(ColumnData, field.getValue());
            }
        }
    }
}
