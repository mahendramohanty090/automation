package auto.qa.workflow;

import org.openqa.selenium.By;

public class ContentLibraryPage extends BasePage{
	 
	
		//Locators
		By btnAdd=By.xpath("//*[@id='body_alert']/div[5]/div[3]/div[3]/div/a[1]/button");
		By lnkContenetLibrary=By.xpath("//*[@id=/mCSB_1_container']/ul/li[2]/ul/li[1]/a");
		By txtEmail=By.id("LoginForm_email");
		By txtPassword=By.id("LoginForm_password");
		By btnLogin=By.id("btn-login");
		By linkDelete = By.xpath("//*[@id='movie_list_tbl']/tbody/tr[1]/td[5]/h5[2]/a");
		By btnYes = By.xpath("//button[contains(text(),'Yes')]");
		public ContentLibraryPage clickAddBtn() throws Throwable
		{
			click(btnAdd, "Add Content");
			return this;
			 
		}
		public ContentLibraryPage clickDeleteLink() throws Throwable
		{
			try {
			click(linkDelete, "Delete Content");
			click(btnYes, "Confirm Yes");
			}catch(Exception e) {}
			return this;
		}	 
			 
		 
}
