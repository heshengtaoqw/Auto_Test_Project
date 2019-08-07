package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginElement;
import com.hst.util.SeleniumUtil;

public class DakaLogin extends DakaLoginElement{
	

	public void do_Login(WebDriver driver,String username, String password, String verify_code) {
		try {
			driver.findElement(DakaLoginElement.username).clear();
			driver.findElement(DakaLoginElement.username).sendKeys(username);
			driver.findElement(DakaLoginElement.password).clear();
			driver.findElement(DakaLoginElement.password).sendKeys(password);
			driver.findElement(DakaLoginElement.verify_code).clear();
			driver.findElement(DakaLoginElement.verify_code).sendKeys(verify_code);
			SeleniumUtil.click(driver.findElement(DakaLoginElement.do_login_button));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
