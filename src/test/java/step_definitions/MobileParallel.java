package step_definitions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import mobileutil.AmazonAppFunctions;
import mobileutil.MobileKeywords;
import utilities.GlobalUtil;

public  class MobileParallel  {
 
	   String platformName;
	   String platformVersion;
	   String deviceName;
	   String port;
	   String URL;
	   
	    private AppiumDriverLocalService service;
		private AppiumServiceBuilder builder;
		private DesiredCapabilities cap;

	   
    @Parameters({"platformName","platformVersion", "deviceName" ,"port","URL"})
 	@Test(priority = 0)
	@Given("^Open the amazon apk file$")
	public void open_the_amazon_apk_file(String platformName,String platformVersion, String deviceName, String port,String URL) throws Throwable {
		
		//AppiumDriverLocalService service = new AppiumServiceBuilder().usingPort(Integer.valueOf(port)).build();
		//service.start();
		int portz = Integer.valueOf(port);
			 	
		System.out.println("platformName =" +platformName);
		System.out.println("platformVersion =" +platformVersion);
		System.out.println("deviceName =" +deviceName);
		System.out.println("URL =" +URL);
		
		System.out.println("Port Number =" +portz);
		String URLL = URL +":" + portz;
		
		System.out.println("The Final URL is =" +URLL);
		
		
		Runtime runtime = Runtime.getRuntime();
		try {
			//runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			//runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a" +URL +"-p" +portz +"\"");
			//runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a" +URL +" -p" + portz);
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		//URL url = new URL("http:127.0.0.1:");
		//String Appium_Node_Path="C:\\Program Files\\nodejs\\node.exe";
		//String Appium_JS_Path= "C:\\Users\\Praveen\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\appium.js";
	
			  DesiredCapabilities capabilities = new DesiredCapabilities();
			  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			  capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
			  capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			  capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			  capabilities.setCapability("appActivity",  "com.amazon.mShop.home.HomeActivity");
			  capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			  
			  try { 
				  //mdriver = new AndroidDriver<AndroidElement>(new  URL("http://0.0.0.0:4723/wd/hub"),capabilities); 
				  GlobalUtil.mdriver = new AndroidDriver<MobileElement>(new URL("http://" +URLL +"/wd/hub"), capabilities); 
				  //GlobalUtil.mdriver = new AndroidDriver<>(new URL(appiumServiceUrl), capabilities);
			     GlobalUtil.mdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			  } catch (Exception e) { 
				  e.printStackTrace(); 
			  	 }
	 }
			 
		

	@Test(priority = 1)
	@Given("^click on SKIP SIGN in button$")
	public void click_on_SKIP_SIGN_in_button() throws Throwable {
		
			try {
				// AmazonAppFunctions.skipSignIn(ODevice_Name, NDevice_Name);
				AmazonAppFunctions.skipSignIn();
			} catch (Exception e) {
				GlobalUtil.ErrorMsg = e.getMessage();
				Assert.fail(e.getMessage());
			}
	}

	@Test(priority = 2)
	@When("^select item by category in the application$")
	public void select_item_by_category_in_the_application() throws Throwable {
		 try {
	           AmazonAppFunctions.clickShopByCategory();
			  
			} catch (Exception e) {
				GlobalUtil.ErrorMsg = e.getMessage();
				Assert.fail(e.getMessage());
			}
	}

	@Test(priority = 3)
	@When("^add selected item to cart on the screen$")
	public void add_selected_item_to_cart_on_the_screen() throws Throwable {
			try {
				// AmazonAppFunctions.addProductToCart(ODevice_Name, NDevice_Name);
				AmazonAppFunctions.clickFirstCategoryExpandButton();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
	}

	@Test(priority = 4)
	@Then("^verify selected item is added to cart on screen$")
	public void verify_selected_item_is_added_to_cart_on_screen() throws Throwable {
			try {
				//AmazonAppFunctions.verifyItemAddedToCart(ODevice_Name, NDevice_Name);
				AmazonAppFunctions.selectProduct();
				AmazonAppFunctions.addProductToCart();
				AmazonAppFunctions.verifyItemAddedToCart();
				
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("taskkill /F /IM node.exe");
					runtime.exec("taskkill /F /IM cmd.exe");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				GlobalUtil.ErrorMsg = e.getMessage();
				Assert.fail(e.getMessage());
			}
	
}

}
	/*
	 * @Test(priority = 5) public void MOb() throws TimeoutException,
	 * InterruptedException { AmazonAppFunctions.addProductToCart();
	 * AmazonAppFunctions.verifyItemAddedToCart();
	 * 
	 * }
	 */


