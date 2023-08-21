package AmazonPagesX;

import org.openqa.selenium.By;

public class ProductListPage {

	public static  By shopbyDropDown = By.xpath("//select[@id='searchDropdownBox']");
	public static  By searchText = By.xpath("//input[@id='twotabsearchtextbox']");
	//public static  By bookName = By.xpath("//span[@text()='The Alchemist']");
	public static  By bookName = By.xpath("//div[contains(@class,'s-result-list')]//a[contains(@class,'a-link-normal')]/span[text()='The Alchemist']");
	public static  By downPage = By.xpath("//span[@id='pagnPrevString']");
	public static  By nextPage = By.xpath("//span[@id='pagnNextString']");

	
	
}
