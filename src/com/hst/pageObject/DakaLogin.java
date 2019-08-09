package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginElement;
import com.hst.pageElement.WorkHomeElement;
import com.hst.util.SeleniumUtil;

public class DakaLogin extends DakaLoginElement{
	

	public void do_Login(WebDriver driver,String username, String password, String verify_code) {
		try {
			driver.findElement(DakaLoginElement.username).clear();
			SeleniumUtil.click(driver.findElement(DakaLoginElement.username));
			driver.findElement(DakaLoginElement.username).sendKeys(username);
			
			driver.findElement(DakaLoginElement.password).clear();
			SeleniumUtil.click(driver.findElement(DakaLoginElement.password));
			driver.findElement(DakaLoginElement.password).sendKeys(password);
			
			driver.findElement(DakaLoginElement.verify_code).clear();
			SeleniumUtil.click(driver.findElement(DakaLoginElement.verify_code));
			driver.findElement(DakaLoginElement.verify_code).sendKeys(verify_code);
			SeleniumUtil.screenshots(Thread.currentThread().getStackTrace()[1].getMethodName());
			SeleniumUtil.click(driver.findElement(DakaLoginElement.do_login_button));
			driver.switchTo().frame(driver.findElement(WorkHomeElement.workArea));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
