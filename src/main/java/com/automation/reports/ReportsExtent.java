package com.automation.reports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;

import com.automation.utilities.FileUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportsExtent {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentReports fnCreateExtentReport() throws InterruptedException, IOException
	{
		ExtentReports objExtent=new ExtentReports(FileUtilities.fnGetCurrentUserDir()+"/Reports/ExtentReportResults.html");
		objExtent.loadConfig(new File(FileUtilities.fnGetCurrentUserDir()+"/src/main/java/com/automation/reports/extent_xml.xml"));
		FileUtilities.DeleteFolder(FileUtilities.fnGetCurrentUserDir()+"/Reports", "ScreenShot");  
		Thread.sleep(5000);
		FileUtilities.createfolder(FileUtilities.fnGetCurrentUserDir()+"/Reports", "ScreenShot");
		Thread.sleep(5000);
		return objExtent;
		
	}
	
	public static void fnCloseExtentReports(ExtentReports objExtent)
	{
		objExtent.flush();
	}
	
	public static ExtentTest fnStartTest(ExtentReports objExtent,String strTestName,String strDescription)
	{
		ExtentTest test=objExtent.startTest(strTestName, strDescription);
		return test;
	}
	
	
	public static void fnEndTest(ExtentReports objExtent,ExtentTest test)
	{
		objExtent.endTest(test);
	}
	
	public static void fnPass(ExtentTest test,LogStatus objStatus,String strStepName,String strStepDescription)
	{
		test.log(objStatus.PASS,strStepName,strStepDescription);
	}
	
	public static void fnFail(ExtentTest test,LogStatus objStatus,String strStepName,String strStepDescription)
	{
		test.log(objStatus.FAIL,strStepName,strStepDescription);
	}
	
	public static void fnFatal(ExtentTest test,LogStatus objStatus,String strStepName,String strStepDescription)
	{
		test.log(objStatus.FATAL,strStepName,strStepDescription);
	}
	
	public static void fnSkip(ExtentTest test,LogStatus objStatus,String strStepName,String strStepDescription)
	{
		test.log(objStatus.SKIP,strStepName,strStepDescription);
	}
	
	public static void fnError(ExtentTest test,LogStatus objStatus,String strStepName,String strStepDescription)
	{
		test.log(objStatus.ERROR,strStepName,strStepDescription);
	}
	
	public static void fnCopyReportToHistory()
	{
		try
		{
		String strName=FileUtilities.GetCurrentTimeStamp();
		System.out.println(strName);
		strName=strName.replaceAll(":", "_").replaceAll("-", "_").replaceAll(" ", "_");
		System.out.println(strName);
		String strHistoryFolderName=FileUtilities.fnGetCurrentUserDir()+"/HistoryOfReports/"+strName;
		System.out.println(strHistoryFolderName);
		File newFolder = new File(strHistoryFolderName);
		String strSourcePath=FileUtilities.fnGetCurrentUserDir()+"/Reports/ExtentReportResults.html";
		String strDestination=strHistoryFolderName+"/ExtentReportResults.html";
		String strSourceImage=FileUtilities.fnGetCurrentUserDir()+"/Reports/ScreenShot";
		String strImage=strHistoryFolderName;
		
	    boolean blnStatus =  newFolder.mkdirs();
	    if(blnStatus)
	    {
	    	System.out.println("Created Folder'");
	    	Files.copy(new File(strSourcePath).toPath(), new File(strDestination).toPath());
	    	FileUtilities.createfolder(strImage, "ScreenShot");
	    	FileUtilities.fnCopyFolder(strSourceImage,strHistoryFolderName+"/ScreenShot");
	    }
	    else
	    {
	    	System.out.println("Folder not created");
	    }
		}catch(Exception e){Reporter.fnPrintException(e);}
	}
	
}
