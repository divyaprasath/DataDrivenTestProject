package com.DataDrivenTest;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.util.TestUtil;

public class DDFExample {

	WebDriver driver;

	@Test(dataProvider="testDataToInsert")
	public void dataDrivenTest(String username, String password) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Prasath\\eclipse-workspace\\DataDrivenExample\\Resource\\chromedriver.exe");;
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.get("http://newtours.demoaut.com/");
		driver.findElement(By.name("userName")).clear();
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("login")).click();
	  
		Thread.sleep(4000);
	
		//Assert.assertTrue(driver.getTitle().matches("Find a Flight: Mercury Tours:"), "Invalid credentials"); 
	    System.out.println("Login Successful");
	    
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] testDataToInsert()
	{
		TestUtil config = new TestUtil("C:\\Users\\Prasath\\eclipse-workspace\\DataDrivenExample\\src\\com\\ExcelSheet\\DDTestData.xlsx");
		
		int rows = config .getRowCount(0);//index
	    Object [][] data = new Object[rows][2];
	    for (int i=0; i<rows; i++) {
	    	data[i][0] = config.getData(0, i, 0);
	    	data[i][1] = config.getData(0, i, 1);
	        
	    }
	  return data;
	}
	
}
