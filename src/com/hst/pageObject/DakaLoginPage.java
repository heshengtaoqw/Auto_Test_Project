package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginPageElement;

public class DakaLoginPage extends DakaLoginPageElement{
	

	public void do_Login(WebDriver driver,String username, String password, String verify_code) {
		driver.findElement(DakaLoginPageElement.username).sendKeys(username);
		driver.findElement(DakaLoginPageElement.password).sendKeys(password);
		driver.findElement(DakaLoginPageElement.verify_code).sendKeys(verify_code);
		driver.findElement(DakaLoginPageElement.img_verifycode);
		driver.findElement(DakaLoginPageElement.f_password);
	}
}
