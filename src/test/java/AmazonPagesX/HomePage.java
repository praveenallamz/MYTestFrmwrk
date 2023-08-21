package AmazonPagesX;

import org.openqa.selenium.By;

public class HomePage {
	
	public static By accountList=By.xpath("//a[@id='nav-link-accountList']");
	//public static By verifyAccount=By.xpath("//a[@id='nav-your-amazon']/span/span");
	
	public static By SignIN=By.xpath("//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][contains(text(),'Sign in')]");
	
	public static By COntinue=By.xpath("//input[@id='continue']");
	//input[@id='continue']
	

}
