package com.commproj.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustPage  
{
	public WebDriver ldriver;
	
	public AddCustPage(WebDriver rdriver) 
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
			}

	//Identify the required elements to automate
	@FindBy(xpath="/html/body/div[3]/div[2]/div/ul/li[4]/a/span")
	@CacheLookup
	WebElement lnkCustomers;
	
	@FindBy(xpath="/html/body/div[3]/div[2]/div/ul/li[4]/ul/li[1]/a/span")
	@CacheLookup
	WebElement lnk2ndCustomers;
	
	@FindBy(xpath="/html/body/div[3]/div[3]/div/form[1]/div[1]/div/a")
	@CacheLookup
	WebElement btnAddNew;
	
//New Customer Form - Fields	
	@FindBy(id="Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id="FirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(id="LastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(id="Gender_Male")
	@CacheLookup
	WebElement rdbtnGendMale;
	
	@FindBy(id="Gender_Female")
	@CacheLookup
	WebElement rdbtnGendFemale;
	
	@FindBy(xpath="//*[@id=\'DateOfBirth\']")
	@CacheLookup
	WebElement txtDateOfBirth;
		
	@FindBy(id="Company")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(id="IsTaxExempt")
	@CacheLookup
	WebElement chkbxTaxExempt;
	
	@FindBy(name="SelectedNewsletterSubscriptionStoreIds")
	@CacheLookup
	WebElement chkbxYourStoreName;

//Customer roles
	@FindBy(xpath="//*[@id=\'customer-info\']/div[2]/div[1]/div[10]/div[2]/div/div[1]/div")
	@CacheLookup
	WebElement listCustRoles;
	
	@FindBy(xpath="//*[@id='SelectedCustomerRoleIds_taglist']/li/span[2]")
	@CacheLookup
	WebElement DefaultRoleClose;
	
	@FindBy(xpath="//*[@id=\'SelectedCustomerRoleIds_listbox\']/li[1]")
	@CacheLookup
	WebElement listItemAdminRole;
	
	@FindBy(xpath="//*[@id=\'SelectedCustomerRoleIds_listbox\']/li[2]")
	@CacheLookup
	WebElement listItemForumModRole;
	
	@FindBy(xpath="//*[@id=\'SelectedCustomerRoleIds_listbox\']/li[3]")
	@CacheLookup
	WebElement listItemGuestsRole;
	
	@FindBy(xpath="//*[@id=\'SelectedCustomerRoleIds_listbox\']/li[4]")
	@CacheLookup
	WebElement listItemRgstdRole;
		
	@FindBy(xpath="//*[@id=\'SelectedCustomerRoleIds_listbox\']/li[5]")
	@CacheLookup
	WebElement listItemVendorsRole;

//Manager of Vendor Dropdown
	@FindBy(id="VendorId")
	@CacheLookup
	WebElement drpDwnMngVndr;
		
//Active Checbox
	@FindBy(id="Active")
	@CacheLookup
	WebElement chkbxActive;
	
	@FindBy(name="AdminComment")
	@CacheLookup
	WebElement txtAdmComment;

//Save the Form 
	@FindBy(name="save")
	@CacheLookup
	WebElement btnSave;

	
//*****Define Action Methods*****

	public void clkOnCustomers()
	{
		lnkCustomers.click();
		}
	
	public void clkOnCustomersMenuItem()
	{
		lnk2ndCustomers.click();
		}

	public void AddNewButton()
	{
		btnAddNew.click();
		}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
		}
	
	public void setPassword(String passwd)
	{
		txtPassword.sendKeys(passwd);
		}
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
		}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
		}
	
	public void setGender(String Gender)
	{
		if(Gender.equals("Male"))
		{
			rdbtnGendMale.click();
			}
		else if(Gender.equals("Female"))
		{
			rdbtnGendFemale.click();
			}
		else
		{
			rdbtnGendMale.click();
			}
	  }
	
	public void setDateOfBirth(String DOB)
	{
		txtDateOfBirth.sendKeys(DOB);
		
		}
		
	public void setCompany(String company)
	{
		txtCompany.sendKeys(company);
		}
	
	public void setTaxExempt() 
	{
		chkbxTaxExempt.click();
		}
	
	public void setYourStoreName()
	{
		chkbxYourStoreName.click();
		}
	
	public void cancelDefaultRole()
	{
		DefaultRoleClose.click();
	}
	
	public void setCustRoles(String role)
	{
		
		listCustRoles.click();
		
		WebElement listItem;
		
		switch(role)
		{
		case "Administrators":
			listItem=listItemAdminRole; break;
		case "Forum Moderators":	
			listItem=listItemForumModRole; break;
		case "Guests":	
			listItem=listItemGuestsRole; break;
		case "Registered":	
			listItem=listItemRgstdRole; break;
		case "Vendors":	
			listItem=listItemVendorsRole; break;
		default:
			listItem=listItemGuestsRole; 
				  }
		
			listItem.click();
				
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click();", listItem);

		}	
	
	public void setVendorMngr(String vendorValue)
	{
		Select drp = new Select(drpDwnMngVndr);
		drp.selectByVisibleText(vendorValue);
		}
	
	public void setActiveChkBx() 
	{
		chkbxActive.click();
		}
	
	public void setAdminComment(String comment)
	{
		txtAdmComment.sendKeys(comment);
		}
	
	public void clickSaveBtn()
	{
		btnSave.click();	
		}
	
}//Class Ends Here
