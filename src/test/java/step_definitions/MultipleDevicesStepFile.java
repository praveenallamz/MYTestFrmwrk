package step_definitions;

import java.io.FileInputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import mobileutil.AmazonAppFunctions;
import mobileutil.MultipleDeviceAmazonMobilefunc;


public class MultipleDevicesStepFile {
	
	public static String userName = "praveenqa1";
	public static String accessKey = "kSNuvtpRs4dxaj7vy6eT";
	public static  AndroidDriver<MobileElement> Mdriver;
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet1;
 
	
	public static int row;
	public static String NDevice_Name=null;
	public static String ODevice_Name;
	
	@Given("^Launch the amazon apk file$")
	public static  AndroidDriver<MobileElement> launch_the_amazon_apk_file() throws Throwable {
		//MultipleDeviceAmazonMobilefunc.OpenAPP();
		FileInputStream fis = new FileInputStream("D:\\TestData\\AutomationControlSheet.xlsx");
        wb = new XSSFWorkbook(fis); 
        sheet1 = wb.getSheet("AndroidList");
        //int rowCount=sheet1.getLastRowNum()-sheet1.getFirstRowNum();
        int rowCount=sheet1.getLastRowNum();
 	    for (int row = 1; row<=rowCount; row++) {
 	    	   
 	    	  String ExecuteFlag = sheet1.getRow(row).getCell(4).getStringCellValue();
    			if (ExecuteFlag.equals("Yes"))
	    		{
	    			 ODevice_Name = sheet1.getRow(row).getCell(1).getStringCellValue();
	    			 
	    			 
	    			 DataFormatter formatter = new DataFormatter();
	    			 String Device_Version = formatter.formatCellValue(sheet1.getRow(row).getCell(2));
	    			 System.out.println(ODevice_Name);
	    			 System.out.println(Device_Version);
	    			 DesiredCapabilities caps = new DesiredCapabilities();
         		     caps.setCapability("device", ODevice_Name);
         		     caps.setCapability("os_version", Device_Version);
         		     caps.setCapability("name", "Bstack-[Java] Sample Test");
         		    //caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c"); // For Wiki 
         		    caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");;
         		   //AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
                   Mdriver = new AndroidDriver<MobileElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps); 
                   Mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    		  /* DesiredCapabilities capabilities = new DesiredCapabilities();
      		       capabilities.setCapability(MobileCapabilityType.PLATFORM,"Android");
      		       capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,ODevice_Name);
      		       capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,Device_Version);
      		       capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
      		       capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
      		       Mdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);*/
   	 
 	    	      click_on_SKIP_SIGN_IN_button();
 	    	       NDevice_Name=ODevice_Name;
	    		}
  	    }
		return Mdriver;
	}

	@Given("^click on SKIP SIGN IN button$")
	public static void click_on_SKIP_SIGN_IN_button() throws Throwable {
	 System.out.println("Old Device Name passed=" +ODevice_Name);
		if(ODevice_Name!=NDevice_Name)
		{
			MultipleDeviceAmazonMobilefunc.skipSignIn(Mdriver);
  		}
		Mdriver.quit();
		 
	}

	
}
