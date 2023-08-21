package step_definitions;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import restutil.RestUtils;
import utilities.ExcelDataUtil;
import utilities.GlobalUtil;
import utilities.HTMLReportUtil;
import utilities.KeywordUtil;

public class RestAPITests {

	public static HashMap<String, String> datamap = null;
	public static String jsonObjectString = null;
	public static Response response = null;

	@Given("^Read \"([^\"]*)\" from Testdata file required to create a user$")
	public void read_from_Testdata_file_and_convert_to_json_Object(String arg1) {
		try {
			datamap = ExcelDataUtil.getTestDataWithTestCaseID("RestAPITestData", arg1);
			jsonObjectString = Files.toString(new File("src/test/resources/testData/CreateUserRestAPITemp.txt"),
					Charsets.UTF_8);
//			jsonObjectString = jsonObjectString.replace("George", "Stefen");
//			jsonObjectString = jsonObjectString.replace("Bluth", "Robert");
			KeywordUtil.cucumberTagName = "API";
		} catch (Throwable e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Given("^I set the content type and body$")
	public void i_set_the_content_type_and_body() {
		try {
			// System.out.println(jsonObjectString);
			RestUtils.setBaseURI(GlobalUtil.getCommonSettings().getRestURL() + "/api/users/", "URI has been set.");
			RestUtils.setContentBodyType(ContentType.JSON, jsonObjectString, "Content Type set to JSON.");
		} catch (Throwable e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			RunCukesTest.logger.log(LogStatus.FAIL, HTMLReportUtil.failStringRedColor("URI and Content Type not set."));
			Assert.fail(e.getMessage());
		}
	}

	@When("^I post the create user data to the api$")
	public void i_post_the_json() {
		try {
			response = RestUtils.PostResponse("", "POST request sent.");
			// System.out.println(response.getBody().asString());
		} catch (Throwable e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			RunCukesTest.logger.log(LogStatus.FAIL, HTMLReportUtil.failStringRedColor("POST request failed."));
			Assert.fail(e.getMessage());
		}
	}

	@Then("^I verify the status as \"([^\"]*)\"$")
	public void i_verify_the_status_as(String arg1) {
		try {
			Assert.assertEquals(Integer.toString(response.getStatusCode()), arg1, "Status Check Failed!");
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor("Status code matched."));
		} catch (Throwable e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			RunCukesTest.logger.log(LogStatus.FAIL, HTMLReportUtil.failStringRedColor("Status code did not match."));
			Assert.fail(e.getMessage());
		}
	}

	@Then("^I verify the json response$")
	public void i_verify_the_json_response() {
		String expected = "data[0].first_name";
		String s=response.getBody().asString();
		Assert.assertEquals(RestUtils.getValueFromJson(response, expected, "Validated FirstName from API response.").toString(),
				"George", "Response verification failed!");
	}
}