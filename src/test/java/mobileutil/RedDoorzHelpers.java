package mobileutil;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebElement;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import mobileutil.AndriodConstants;
import mobileutil.MobileKeywords;
import step_definitions.RunCukesTest;
import utilities.GlobalUtil;

public class RedDoorzHelpers {

	public static void clickDismisspopup() {
		
			if(MobileKeywords.isWebElementPresent(RedDoorzConstants.id_popup_dismiss,AndriodConstants.Common.type_id)) {
				MobileKeywords.click(RedDoorzConstants.id_popup_dismiss, AndriodConstants.Common.type_id, "click on dismiss popup");
		} 
	}
	
	public static void clickBookNow() throws InterruptedException {
		Thread.sleep(5000);
			 if(MobileKeywords.isWebElementPresent(RedDoorzConstants.id_book_now,AndriodConstants.Common.type_id)) {
				//clickByText("pm_book_now", "BOOK Now Clicked");
				MobileKeywords.click(RedDoorzConstants.id_book_now, AndriodConstants.Common.type_id, "click on book now");
			}
			 else if(MobileKeywords.isWebElementPresent(RedDoorzConstants.id_continue_with_RC,AndriodConstants.Common.type_id)) {
					MobileKeywords.click(RedDoorzConstants.id_redclub_price, AndriodConstants.Common.type_id, "click on club price");
				} 
			else  
			{
				clickByText("pm_book_now", "BOOK Now Clicked");
			}
		 
	}
	public static void clickDismissBtn() throws InterruptedException {
		Thread.sleep(5000);
		if(MobileKeywords.isWebElementPresent(RedDoorzConstants.id_btn_dismiss,AndriodConstants.Common.type_id)) {
			MobileKeywords.click(RedDoorzConstants.id_btn_dismiss, AndriodConstants.Common.type_id, "click on button dismiss");
		}
 
	}
	
	public static void clickByText(String text, String logstep) throws InterruptedException {
		Thread.sleep(12000);
		
			try {
				List<?> ListElemnts =  GlobalUtil.getMDriver().findElements(MobileBy.xpath("//android.widget.TextView"));

				for (int i = 1; i <= ListElemnts.size(); i++) { 
					//  System.out.println("Element=" + i + "=" + ListElemnts.get(i).getText());
					  if (((RemoteWebElement) ListElemnts.get(i)).getText().contains(text))
					  {
					  System.out.println(text); 
					  ((RemoteWebElement) ListElemnts.get(i)).click();
					  RunCukesTest.logger.log(LogStatus.PASS, logstep);
					  break; 
					  }
					  
				  }
			} catch (Exception e) {
				//AmazonAppFunctions.click(AndriodConstants.Amazon.first_category_expand_btn, AndriodConstants.Common.type_xpath, "First Category option selected");
				// RunCukesTest.logger.log(LogStatus.PASS, "First Category option selected");
			}
	}
	
	public static void enterByText(String text,String data) throws InterruptedException {
		//executionDelay(12000);
		Thread.sleep(12000);
		//executionDelay(5000);
		
			try {
				List<?> ListElemnts =  GlobalUtil.getMDriver().findElements(MobileBy.xpath("//android.widget.EditText"));

				for (int i = 1; i <= ListElemnts.size(); i++) { 
					  System.out.println("Element=" + i + "=" + ((RemoteWebElement) ListElemnts.get(i)).getText());
					  if (((RemoteWebElement) ListElemnts.get(i)).getText().contains(text))
					  {
					  System.out.println(text); 
					  ((RemoteWebElement) ListElemnts.get(i)).sendKeys(data);
					//  RunCukesTest.logger.log(LogStatus.PASS, "First Category option selected");
					  break; 
					  }
					  
				  }
			} catch (Exception e) {
				//AmazonAppFunctions.click(AndriodConstants.Amazon.first_category_expand_btn, AndriodConstants.Common.type_xpath, "First Category option selected");
				// RunCukesTest.logger.log(LogStatus.PASS, "First Category option selected");
				System.out.print(e);
			}
	}
}
