package step_definitions;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mobileutil.AmazonAppFunctions;
import utilities.DriverUtil;
import utilities.GlobalUtil;
import utilities.KeywordUtil;

public class MobileTestsAmazon {

	public static String NDevice_Name;
	public static String ODevice_Name;

	@Given("^open the Amazon app $")
	public void launchAmazonLocal(String exeEnv) {
		try {
			KeywordUtil.cucumberTagName = "MobileTestsAmazon";
			DriverUtil.getMobileApp(exeEnv);
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@Given("^open the Amazon app on \"([^\"]*)\"$")
	public void open_the_Amazon_app(String deviceDetails) {
		try {
			KeywordUtil.cucumberTagName = "MobileTestsAmazon";
			if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Local"))
				DriverUtil.invokeLocalMobileApp(GlobalUtil.getCommonSettings().getExecutionEnv(), deviceDetails);
			
			else if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Remote"))
				DriverUtil.invokeBrowserStackMobileApp(deviceDetails);
			
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@Given("^click on skip sign in$")
	public void click_on_skip_sign_in() {
		try {
			// AmazonAppFunctions.skipSignIn(ODevice_Name, NDevice_Name);
			AmazonAppFunctions.skipSignIn();
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^select for an item by category$")
	public void search_for_an_item() {
		try {
			//AmazonAppFunctions.clickShopByCategory(ODevice_Name, NDevice_Name);
			AmazonAppFunctions.clickShopByCategory();
			AmazonAppFunctions.clickFirstCategoryExpandButton();
		 	AmazonAppFunctions.selectProduct();
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^add the item to cart$")
	public void add_the_item_to_cart() {
		try {
			// AmazonAppFunctions.addProductToCart(ODevice_Name, NDevice_Name);
			AmazonAppFunctions.addProductToCart();
			RunCukesTest.logger.log(LogStatus.PASS, "Selected Product Added to the cart");
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Then("^verify item is added to cart$")
	public void verify_item_is_added_to_cart() {
		try {
			// AmazonAppFunctions.verifyItemAddedToCart(ODevice_Name, NDevice_Name);
			AmazonAppFunctions.verifyItemAddedToCart();
		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Given("^login to the app with username \"([^\"]*)\"$")
	public void login_to_the_app_with_username_and_password(String username) {
		try {
			// AmazonAppFunctions.loginToAmazonApp(username);
			AmazonAppFunctions.loginToAmazonApp(username, ODevice_Name, NDevice_Name);

		} catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

}
