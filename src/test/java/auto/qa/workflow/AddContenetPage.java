package auto.qa.workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;



public class AddContenetPage extends BasePage {

	By txtContentName = By.id("mname");
	By browseUploadPoster = By.id("browse-upload-poster");
	By btnUpload = By.id("celeb_pic");
	By btnNext = By.xpath("(//*[@id='myLargeModalLabel']//button)[2]");
	By listContentCategory = By.id("content_category_value");
	By btnSaveAndContinue = By.id("save-btn");
	By lnkContentLibrary = By.linkText("Content Library ");
	By txtPermalink = By.id("permalink");
 
	public static String randomText = "";


	public AddContenetPage clicktxtPermalink() throws Throwable {
		//waitForElementHasSomeText(txtPermalink, randomText);
		click(txtPermalink, "Tesxt Box Click");
		driver.findElement(txtPermalink).sendKeys(Keys.TAB);
		return this;
	}

	public void clickContentLibrary() throws Throwable {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(lnkContentLibrary));
		click(lnkContentLibrary, "Content Library Link");

	}

	public AddContenetPage selectListContentCategory(String selectByValue) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		selectByVisibleText(listContentCategory, selectByValue, "Select List content");
		return this;
	}

	public AddContenetPage clickBrowseUploadPoster() throws Throwable {
		click(browseUploadPoster, "Browser Upload Poster");
		return this;
	}

	public AddContenetPage posterUpload(String strText) throws Throwable {
		type(btnUpload, strText, "filepath");
		return this;
	}

	public AddContenetPage typeTxtContentName(String strText) throws Throwable {
		type(txtContentName, strText, "filepath");
		return this;
	}

	public AddContenetPage clickBtnNext() throws Throwable {
		click(btnNext, "Next");
		return this;
	}
	

	public AddContenetPage clickBtnSaveAndContinue() throws Throwable {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(btnSaveAndContinue));
		click(btnSaveAndContinue, "Save and Continue");
		Thread.sleep(5000);
		return this;
	}

	public String randonText() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(4);

		for (int i = 0; i < 4; i++) 
		{
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		randomText= sb.toString().toLowerCase();
		return randomText;
	}
	
	

}
