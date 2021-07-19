package stepdefs;

import Utility.LoggerHelper;
import base.base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import java.util.Map;

import static org.testng.Assert.assertEquals;


public class ListGistunauthStepdefs extends base {

    Logger log = LoggerHelper.getLogger(LoggerHelper.class);

    @And("^GistList unauth set the basepath (.*)$")
    public void gistlistSetTheBasepathGists(String list) {
        basepath(list);
    }


    @Given("^GistList for unauth user Request Header authentication$")
    public void gistlistForUnauthUserRequestHeaderAuthentication() {
        initheader();
    }


    @Given("^The user send a call to List gist unauth service request$")
    public void theUserSendACallToListGistUnauthServiceRequest() {
        response = request.when().get();
    }

    @Given("^The user Lists public gists for the specified(.*) user(.*)$")
    public void theUserListsPublicGistsForTheSpecifiedUser(String userparam, String userid) {
        request.pathParam(userparam, userid);
    }

    @When("^The List gist user unauth service response code should be (\\d+)$")
    public void theListGistUserUnauthServiceResponseCodeShouldBe(int arg0) {
        response.then().statusCode(arg0);
    }

    @Then("^The List gist unauth user response fields should match with below objects$")
    public void theListGistUnauthUserResponseFieldsShouldMatchWithBelowObjects(Map<String, String> resquestFields) {

        for (Map.Entry<String, String> field : resquestFields.entrySet()) {
            String ColumnData = response.jsonPath().getString(field.getKey());
            log.info(field.getKey() + "\nExpected Value: " + field.getValue() + "\nActual Value: " + ColumnData);

            if (ColumnData.contains(field.getValue()) == true) {
                assertEquals(ColumnData, field.getValue());
                log.info("Result: Test Passed");
            } else {
                log.info("Result: Test Failed");
                assertEquals(ColumnData, field.getValue());
            }
        }

    }

    @Given("^The user set the list gist unauth user and request the service (.*)$")
    public void theUserSetTheListGistUnauthUserAndRequestTheServiceUsersUseridGists(String username) {
        response = request.when().get(username);
    }

    @Given("^The user set the query parameter (.*)with(.*) and (.*)with(.*) and (.*)with(.*)$")
    public void theUserSetTheQueryParameterSince_paramWithSince_dateAndPerpage_paramWithPer_pageAndPage_paramWithPage(String sinceparam, String since, String perpageparam, String perpage, String pageparam, String page) {
        request.queryParams(sinceparam, since, perpageparam, perpage, pageparam, page);
    }
}
