package com.commproj.testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import com.commproj.pageobjects.LoginPage;


public class TC_LoginTest_0001 extends BaseClass
{
	public Logger logger;
	
	@Test
	public void login() throws InterruptedException, IOException
	{
		logger=Logger.getLogger("CommProj");
		PropertyConfigurator.configure("log4j.properties");
		
		driver.get(baseURL);
		logger.info("URL Opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username has been entered successfully");
		lp.setPassword(password);
		logger.info("Password has been entered successfully");
		lp.btnLogin();
		logger.info("Clicked on the button");
		Thread.sleep(5000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
		{
			System.out.println("Login Successful");
			logger.info("Login Test Pass");
			}
		else
		{
			captureScreen(driver, "login");
			System.out.println("Login Unsuccessful");
			logger.info("Login Test Fail");
			}
		
		Thread.sleep(3000);
		lp.linkLogout();
		logger.info("Clicked on Logout successfully");
	  }
	
}