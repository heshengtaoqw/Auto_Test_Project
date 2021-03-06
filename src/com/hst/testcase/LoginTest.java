package com.hst.testcase;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hst.pageObject.*;
import com.hst.util.*;


public class LoginTest extends BaseTest{

	DakaHome dakaHomePage = new DakaHome();
	DakaLogin loginPage = new DakaLogin();
	WorkHome workHome = new WorkHome();
	String username = null;
	String password = null;
	String verify_code=null;


	@Test(dataProvider = "login")
	public void loginTest1(String username,String password,String verify_code) throws Exception{
		driver.get(dakaHomePage.dakaHomePageURL);
		
		try {
			dakaHomePage.click_EnterpriseLogin(driver);
			loginPage.do_Login(driver, username, password, verify_code);
			//driver.switchTo().defaultContent();//切换到原始登录状态下的html，每切换一个工作区域，都要先切换回最初状态
			workHome.workArea_createMeeting(driver);
			Thread.sleep(10000);
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