//The purpose of this project is to automate the login process of the demo eCommerce application
//Author: Ramesh.Bathina, Title: Automation Specialist
//Date: 01/30/2020

package com.commproj.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
			}
	
//Identify the required elements to automate
	@FindBy(id="Email")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/input")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(linkText="Logout")
	@CacheLookup
	WebElement lnkLogout;

	//Create Action Methods for the above webelements
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
		}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
		}
	
	public void btnLogin()
	{
		btnLogin.click();
		}
	
	public void linkLogout()
	{
		lnkLogout.click();
		}

}