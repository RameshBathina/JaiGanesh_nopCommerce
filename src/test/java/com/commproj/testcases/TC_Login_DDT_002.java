package com.commproj.testcases;

import java.awt.Toolkit;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commproj.pageobjects.LoginPage;
import com.commproj.utilities.XLUtils2;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;

public class TC_Login_DDT_002 extends BaseClass 
{

	@Test(dataProvider="LoginData")
	public void LoginTest(String uname, String pwd) throws InterruptedException, IOException
	{
		Logger logger = Logger.getLogger("CommProj");
		PropertyConfigurator.configure("log4j.properties");
		
		driver.get(baseURL);
		logger.info("URL is Opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		logger.info("Username has been entered successfully");
		lp.setPassword(pwd);
		logger.info("Password has been entered successfully");
		lp.btnLogin();
		logger.info("Clicked on the button");
		Thread.sleep(3000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
		{
			Assert.assertTrue(true);
			lp.linkLogout();
			logger.info("Login Test Pass");
			}
		else
		{
			logger.info("Login Test Fail");
			Toolkit.getDefaultToolkit().beep();
			Assert.assertTrue(false);
			}
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir") + "/src/test/java/com/commproj/testdata/LoginData.xlsx";
		
		int rownum=XLUtils2.getRowCount(path, "Sheet1");
		int colcount=XLUtils2.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]= new String[rownum][colcount];
		
		for(int r=1; r<=rownum; r++)
		{
			for(int c=0; c<colcount; c++)
			{
				logindata[r-1][c]=XLUtils2.getCellData(path, "Sheet1", r, c);
					}
		   }
		
		return logindata;
	}
}