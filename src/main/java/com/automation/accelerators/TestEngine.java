package com.automation.accelerators;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automation.reports.Reporter;
import com.automation.reports.ReportsExtent;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestEngine extends ReportsExtent {

	public static RemoteWebDriver driver = null;

	@BeforeSuite(alwaysRun = true)
	public void fnBeforeSuite() throws InterruptedException, IOException {
		System.out.println("Before Suite");
		extent = fnCreateExtentReport();
	}

	@AfterSuite(alwaysRun = true)
	public void fnAfterSuite() {
		System.out.println("After Suite");
		fnCloseExtentReports(extent);
		ReportsExtent.fnCopyReportToHistory();
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "BROWSER" })
	public void fnBeforeTest(String BROWSER, Method method) throws IOException {
		Reporter.blnStatus = true;
		String strTestName = method.getName();
		String MethodDescription = "Description : " + strTestName;
		test = fnStartTest(extent, strTestName, MethodDescription);
		driver = fnOpenBrowser(BROWSER);
	}

	@AfterMethod(alwaysRun = true)
	public void fnAfterTest() {
		System.out.println("After Method");
		fnEndTest(extent, test);
		if (driver != null) {
			driver.close();
		}
	}

	public static RemoteWebDriver fnOpenBrowser(String strBrowserName) throws MalformedURLException {

		if (strBrowserName.toLowerCase().startsWith("ch")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (strBrowserName.toLowerCase().startsWith("fire")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static void fnCloseBrowser() {
		if (driver != null)
			driver.quit();
	}

	public void fnCloseTest() {
		if (Reporter.blnStatus == false) {
			Assert.fail();
		}
	}
}
