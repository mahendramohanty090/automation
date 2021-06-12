package auto.qa.test;

 
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.reports.Reporter;
import auto.qa.settings.SettingsVariable;
import auto.qa.workflow.BasePage;
 

public class ContentTest extends BasePage{

	@Test(description =" Add content and validate")
	public void validateContentTextTest() throws Throwable
	{
		 
			Reporter.info("Validate Content Text", "Validate the added content");
			launchUrl(SettingsVariable.strURL);
			landingPage.clickLoginLink();
			landingPage.enterEmail(SettingsVariable.userName);
			landingPage.enterPassword(SettingsVariable.password);
			landingPage.clickLoginBtn();
			dashBoardPage.clickManageContent().clickContentLibrary();
			contentLibraryPage.clickDeleteLink().clickAddBtn();
			//contentLibraryPage.clickAddBtn();
			addContentPage.typeTxtContentName(addContentPage.randonText());
			String file = System.getProperty("user.dir")+"/files/banner.jpg";
			addContentPage.clickBrowseUploadPoster().posterUpload(file).clickBtnNext();
	        addContentPage.selectListContentCategory("Movie").clicktxtPermalink().clickBtnSaveAndContinue();
	        dashBoardPage.clickManageContent().clickContentLibrary();
	        Assert.assertEquals(dashBoardPage.getAddedContentText() ,addContentPage.randomText);
	       
		 
	}
}
