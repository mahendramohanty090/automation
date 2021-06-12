package auto.qa.workflow;

import org.openqa.selenium.By;

public class DashboardPage extends BasePage{
 
	
	//Locators
	By lnkManageContent=By.xpath("//*[@id='mCSB_1_container']/ul/li[2]/a");
	By lnkContentLibrary=By.xpath("//a[@href='/cmscontent/managecontent']");
	By linkAddedCaption = By.xpath("(//div[@class='caption'])[1]");
	public DashboardPage clickManageContent() throws Throwable
	{
		mouseover(lnkManageContent, "Manage Content Link");
		return this;
		 
	}
	public void clickContentLibrary() throws Throwable
	{
		click(lnkContentLibrary, "Content Library Link");
		 
	}
	
	public String getAddedContentText() throws Throwable
	{
		
		return getText(linkAddedCaption, " Added content text");
				
	}
	
}
