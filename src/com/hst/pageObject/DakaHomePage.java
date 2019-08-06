package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.hst.pageElement.*;
import com.hst.util.BaseTest;
import org.openqa.selenium.JavascriptExecutor;

public class DakaHomePage extends DakaHomePageElement  {
	
	public void click_EnterpriseLogin(WebDriver driver) throws InterruptedException {
		try {
			//使用js click可以增强元素点击事件的稳定性，如果还是不稳定，尝试升级selenium和firefox版本
			WebElement click = driver.findElement(DakaHomePageElement.enterpriseLoginButton);
			//String s = "driver.findElement(DakaHomePageElement.enterpriseLoginButton).click()";
			//js.executeScript(s);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", click);
			BaseTest.switchWindows();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
