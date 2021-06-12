package auto.qa.workflow;

import org.openqa.selenium.By;

public class LandingPage extends BasePage{
	//Locators
		By lnkLogin=By.id("load_login");
		By txtEmail=By.id("LoginForm_email");
		By txtPassword=By.id("LoginForm_password");
		By btnLogin=By.id("btn-login");
		
		public void clickLoginLink() throws Throwable
		{
			click(lnkLogin, "Login Link");
		}
		
		public void enterEmail(String strText) throws Throwable
		{
			type(txtEmail, strText, "Input E-Mail");
		}
		
		public void enterPassword(String strText) throws Throwable
		{
			type(txtPassword, strText, "Input Password");
		}
		
		public void clickLoginBtn() throws Throwable
		{
			click(btnLogin, "Login button");
		}

}
