package com.commproj.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time Stamp
		String repName="Test-Report-" + TimeStamp + ".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);//Specify the location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "localhost");
		extent.setSystemInfo("Environment","QA" );
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("User", "Ramesh.Bathina");
		extent.setSystemInfo("Title", "Automation Specialist");
				
		htmlReporter.config().setDocumentTitle("Automation Report"); //Title of the Report
		htmlReporter.config().setReportName("Functional Test Report"); //Name of the Report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//Location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
	}

	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //Create a new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //Create a new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time Stamp
		
		//When Test is failed we need to provide screenshot, so below is the code
		String ScreenShotPath=System.getProperty("user.dir") +  "/Screenshots/" + tr.getName() + TimeStamp + ".png";
		
		File f = new File(ScreenShotPath);
		
		if(f.exists())
		{
			try
			{
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(ScreenShotPath));
					}
			catch(IOException e)
			{
				e.printStackTrace();
					}
		}
	}	
	
	public void onTestSkip(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); //Create a new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
