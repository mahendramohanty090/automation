package com.automation.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.accelerators.TestEngine;
import com.automation.utilities.FileUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter extends TestEngine {
	public static boolean blnReportTempStatus=true;
	public static boolean blnStatus=true;
	/***
	 * Name of Function 	:- fnPrintException
	 * Developed By 		:- Mahendra
	 * Date 				:- 8-June-2021
	 * Function Description :- To Print Exception in Console
	 * @param Exception
	 */
	public static void fnPrintException(Exception e)
	{
		e.printStackTrace();
	}
	
	/***
	 * Name of Function 	:- pass
	 * Developed By 		:- Mahendra
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log pass in Extent Report
	 * @param Exception
	 */
	public static void pass(String stepName, String description) 
	{
		try{
			test.log(LogStatus.PASS, stepName, description);
		}catch(Exception e){fnPrintException(e);}			
	}
	
	/***
	 * Name of Function 	:- info
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log info in Extent Report
	 * @param Exception
	 */
	public static void info(String stepName, String description) 
	{
		try{
			test.log(LogStatus.INFO, stepName, description);
			
		}catch(Exception e){fnPrintException(e);}	
	}
	
	/***
	 * Name of Function 	:- skip
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log skip in Extent Report
	 * @param Exception
	 */
	public static void skip(String stepName, String description) 
	{
		try{
			test.log(LogStatus.SKIP, stepName, description);
		}catch(Exception e){fnPrintException(e);}	
	}
	

	/***
	 * Name of Function 	:- fail
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log fail in Extent Report
	 * @param Exception
	 */
	public static void fail(String stepName, String description) 
	{
		try{
				String strImagePath="./ScreenShot/"+getScreenshot();
				String strMarkup=getMarkup(strImagePath);
				test.log(LogStatus.FAIL, stepName, description +"\n" + strMarkup);						
				Reporter.blnStatus=false;
				blnReportTempStatus=false;
		}catch(Exception e){fnPrintException(e);}	
	}
	
	/***
	 * Name of Function 	:- fatal
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log fatal in Extent Report
	 * @param Exception
	 */
	public static void fatal(String stepName, String description) 
	{
		try{
			test.log(LogStatus.FATAL, stepName, description);
			Reporter.blnStatus=false;
		}catch(Exception e){fnPrintException(e);}	
	}
	
	/***
	 * Name of Function 	:- error
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log error in Extent Report
	 * @param Exception
	 */
	public static void error(String stepName, String description) 
	{
		try{
			test.log(LogStatus.ERROR, stepName, description);
			Reporter.blnStatus=false;
		}catch(Exception e){fnPrintException(e);}	
	}
	
	/***
	 * Name of Function 	:- warning
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log warning in Extent Report
	 * @param Exception
	 */
	public static void warning(String stepName, String description) 
	{
		try{
			test.log(LogStatus.WARNING, stepName, description);
		}catch(Exception e){fnPrintException(e);}	
	}
	
	/***
	 * Name of Function 	:- unknown
	 * Developed By 		:- Mahendra 
	 * Date 				:- 8-June-2021
	 * Function Description :- To Log warning in Extent Report
	 * @param Exception
	 */
	public static void unknown(String stepName, String description) 
	{
		try{
			test.log(LogStatus.UNKNOWN, stepName, description);
		}catch(Exception e){fnPrintException(e);}	
	}
	
	public static String getScreenshot() throws IOException
	{
		String strScreenshotName=null;
		try
		{
		strScreenshotName="ScreenShot"+FileUtilities.GetCurrentTimeStamp().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "")+".png";
		System.out.println(strScreenshotName);
		File strScreenShotPath=new File(FileUtilities.fnGetCurrentUserDir()+"/Reports/ScreenShot/"+strScreenshotName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(strScreenShotPath);
		FileUtils.copyFile(source,strScreenShotPath);
		}
		catch(Exception e){}
		return strScreenshotName;
	}
	
	public static String getMarkup(String strScreenshotPath) {
		String strScreen="<a href=\""+strScreenshotPath+"\"><img class=\"report-img\" src=\""+strScreenshotPath+"\"></a>";
		System.out.println(strScreen);
		String strHTMLCode="<ul class=\"collapsible\" data-collapsible=\"expandable\">";
        strHTMLCode=strHTMLCode+"\n"+"<li class=\"active\">";
        strHTMLCode=strHTMLCode+"\n"+"<div class=\"collapsible-header\" <strong>>>>> &nbsp;&nbsp;&nbsp;&nbsp; Screenshot </div>";
        strHTMLCode=strHTMLCode+"\n"+"<div class=\"collapsible-body\">"+strScreen+"</div>";
        strHTMLCode=strHTMLCode+"\n"+"</li>";
        strHTMLCode=strHTMLCode+"\n"+"</ul>";
        return strHTMLCode;
}
	
}
