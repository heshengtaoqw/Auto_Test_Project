package com.hst.testcase;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hst.pageObject.*;
import com.hst.util.*;


public class LoginTest extends BaseTest{

	DakaHomePage dakaHomePage = new DakaHomePage();
	DakaLoginPage loginPage = new DakaLoginPage();
	String username = null;
	String password = null;
	String verify_code=null;


	@Test//(dataProvider = "login")
	public void loginTest1() throws Exception{
		driver.get(dakaHomePage.dakaHomePageURL);
		
		try {
			dakaHomePage.click_EnterpriseLogin(driver);
			loginPage.do_Login(driver, "111111", "1122222", "456489");
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		/*
		finally {
			assertEquals(loginPage.isLoginSuccess(driver), true);
			}
		Thread.sleep(2000);
		*/
	}
	
	/*
	//��������2���˺ż����벻��ȷ
	@Parameters({"testName","username","password"})
	@Test
	public void loginTest2(String testName, String username, String password) throws Exception{
		driver.get(LoginURL);
		System.out.println("��������2"+ testName);
		System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
		
		try {
			loginPage.login(driver, username, password);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			assertEquals(loginPage.isLoginSuccess(driver),false);
			}
	}
	*/
	
	
}