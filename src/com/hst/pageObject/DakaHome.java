package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.hst.pageElement.*;
import com.hst.util.BaseTest;
import com.hst.util.SeleniumUtil;

import org.openqa.selenium.JavascriptExecutor;

public class DakaHome extends DakaHomeElement  {
	
	public void click_EnterpriseLogin(WebDriver driver) throws InterruptedException {
		try {
			//使用js click可以增强元素点击事件的稳定性，如果还是不稳定，尝试升级selenium和firefox版本
			//String s = "driver.findElement(DakaHomePageElement.enterpriseLoginButton).click()";
			//js.executeScript(s);
			SeleniumUtil.click(driver.findElement(DakaHomeElement.enterpriseLoginButton));
			SeleniumUtil.switchWindows();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
