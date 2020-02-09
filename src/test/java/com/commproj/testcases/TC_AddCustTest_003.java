package com.commproj.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.commproj.pageobjects.LoginPage;
import com.commproj.pageobjects.AddCustPage;

public class TC_AddCustTest_003 extends BaseClass 
{
		
	@Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
	Logger logger = Logger.getLogger("CommProj");
	PropertyConfigurator.configure("log4j.properties");

	//Opening the URL	
	driver.get(baseURL);
	logger.info("URL is Opened");
		
	LoginPage lp = new LoginPage(driver);
	//Login with username and password
	lp.setUserName(username);
	logger.info("Username has been entered successfully");
	lp.setPassword(password);
	logger.info("Password has been entered successfully");
	lp.btnLogin();
	logger.info("Clicked on the button");
	Thread.sleep(3000);
	
	//Adding New Customer 
	logger.info("Providing the new customer details..");
	AddCustPage addcust = new AddCustPage(driver);
	
	addcust.clkOnCustomers();
	addcust.clkOnCustomersMenuItem();
	
	addcust.AddNewButton();
	Thread.sleep(3000);
	
	String email = randomstring() + "@gmail.com";
	addcust.setEmail(email);
	
	addcust.setPassword("Test123");
	addcust.setFirstName("Rambo");
	addcust.setLastName("John");
	addcust.setGender("Male");
	logger.info("Gender has been selected");
	
	addcust.setDateOfBirth("7/05/1985");
	//addcust.setDob("7/05/1985"); // Format: D/MM/YYY
	
	logger.info("DOB has been entered");
	addcust.setCompany("X-Axis");
	addcust.setTaxExempt();
	addcust.setYourStoreName();
	logger.info("Store Name has been entered");
	
	addcust.cancelDefaultRole();//To cancel the default role that was already auto populated
	
	addcust.setCustRoles("Forum Moderators");
	addcust.setCustRoles("Guests");
	logger.info("Role has been selected successfully");
	
	addcust.setVendorMngr("Vendor 2");
	//addcust.setManagerOfVendor("Vendor 2");
	
	logger.info("Vendor has been selected");
	addcust.setAdminComment("This is end to end selenium project");
	addcust.clickSaveBtn();
	
	logger.info("Validation has been started");
	
	String msg=driver.findElement(By.tagName("body")).getText();
	
	if(msg.contains("The new customer has been added successfully"))
	{
		Assert.assertTrue(true);
		logger.info("Test Pass");
		}
	else
		{
		captureScreen(driver, "AddNewCustomer");
		logger.info("Test Failed");
		Assert.assertTrue(false);
		}
	}
		
} //Class Ends Here...