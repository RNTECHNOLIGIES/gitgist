package base;

import Utility.Configist;
import com.google.common.base.Strings;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import Utility.LoggerHelper;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class base {
    protected RequestSpecification request;
    protected ValidatableResponse json;
    protected static String randomGistId;
    public Response response;
    public Logger log = LoggerHelper.getLogger(LoggerHelper.class);
    public static JSONObject requestObjectCreategist = new JSONObject();

    protected Configist configist = ConfigFactory.create(Configist.class, System.getProperties());

    static RestAssuredConfig restconfig = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 60000));


    public RequestSpecification initheader() {
        request = given().config(restconfig).log().all()
                .relaxedHTTPSValidation()
                .header("Authorization", "token " + configist.authToken())
                .contentType("application/vnd.github.v3+json")
                .baseUri(configist.baseUri());
        log.info("Header initialized" + configist.baseUri());
        return request;
    }

    public RequestSpecification unauthheader() {
        request = given().config(restconfig).log().all()
                .relaxedHTTPSValidation()
                .contentType("application/vnd.github.v3+json")
                .baseUri(configist.baseUri());
        log.info("Header unauth initialized");
        return request;
    }

    public RequestSpecification gistraw(String str) {
        request = given().config(restconfig).log().all()
                .relaxedHTTPSValidation()
                .header("Authorization", "token " + configist.authToken())
                .contentType(str)
                .baseUri(configist.baseUri());
        log.info("Header initialized");
        return request;
    }

    public RequestSpecification gistrawithouttoken(String str) {
        request = given().config(restconfig).log().all()
                .relaxedHTTPSValidation()
                .contentType(str)
                .baseUri(configist.baseUri());
        log.info("Header initialized");
        return request;
    }

    public RequestSpecification basepath(String basepath) {
        request.basePath(basepath);
        log.info("Header base path is set");
        return request;
    }

    public void setGistDescription(Map<String, String> resquestFields) throws JSONException {
        for (Map.Entry<String, String> field : resquestFields.entrySet()) {
            requestObjectCreategist.put(field.getValue(), field.getKey());
            log.info("description ::" + requestObjectCreategist);

        }
    }

    public String setGistFiles(Map<String, String> gistFiles) throws JSONException {
        JSONObject file = new JSONObject();
        for (Map.Entry<String, String> field : gistFiles.entrySet()) {
            JSONObject content = new JSONObject();
            content.put("content", field.getValue());
            file.put(field.getKey(), content);
        }
        log.info(requestObjectCreategist);
        return requestObjectCreategist.put("files", file).toString();

    }

    public void getsetthebasePath(String basepath) {
        response = request.when().get(basepath).prettyPeek();

    }

    public void getgistsetpathParam() {
        request.pathParam("id", fetchgistid());
    }


    public void ResponseCodeShouldBe(int arg0) {
        response.then().statusCode(arg0).log().all();
    }


    public void theResponseFieldsShouldMatch(Map<String, String> gistFiles) throws JSONException {
        for (Map.Entry<String, String> field : gistFiles.entrySet()) {
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

    public String fetchgistid() {
        ArrayList<String> gistIds = given().log().all().baseUri(configist.baseUri())
                .header("Authorization", "token " + configist.authToken())
                .get("users/" + configist.sysuserid() + "/gists").prettyPeek().path("id");
        int index = (int) (Math.random() * gistIds.size());
        log.info("###Index   :: " + index + ":::=" + gistIds);
        randomGistId = gistIds.get(index);
        return randomGistId;
    }

    public void jsonSchemaValidation(String schemafile) {
        try {
            FileReader reader =
                    new FileReader(System.getProperty("user.dir") + "/src/test/resources/schemaResponse/" + schemafile);
            JSONObject jsonSchema = new JSONObject(
                    new JSONTokener(reader));
            JSONObject jsonSubject = new JSONObject(
                    new JSONTokener(response.asString()));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
            log.info("@@@@" + response.asString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    public void validateTheResponseHeaderForRatelimitfornull(Map<String, String> resquestFields) {
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