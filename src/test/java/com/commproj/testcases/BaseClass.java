package com.commproj.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.commproj.utilities.ReadConfig;

public class BaseClass 
{
	ReadConfig readconfig = new ReadConfig();
	public WebDriver driver;
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUserName();
	public String password=readconfig.getPassword();
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br)
	{
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		driver=new ChromeDriver();
		driver.manage().window().maximize();
			}
		else if(br.equals("edge"))
		{
		System.setProperty("webdriver.edge.driver", readconfig.getEdgepath());
		driver=new EdgeDriver();
		driver.manage().window().maximize();
			}
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
			}
		else
		{
			System.out.println("Invalid Browser Input, please retry");
		}
		
	}
	@AfterClass
	public void teardown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.close();
			}
	
	public void captureScreen(WebDriver driver, String tstName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String TimeStampSS = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time Stamp
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tstName + "_" + TimeStampSS + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken..");
			}
	
//The below method will generate a random string which can be used to 
	//set the email
	 public static String randomstring()
	 {
		 String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		 return(generatedString1);
	 		}

//The below method will generate a random number  
	 public static String randomnum()
	 {
		 String generatedString2 = RandomStringUtils.randomNumeric(4);
		 return(generatedString2);
	 		}
	 
}