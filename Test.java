package com.hst.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;

public class Test {
	
	public static void main( String args[]) throws InterruptedException
	{
		System.setProperty("webdriver","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.baidu.com");
		WebElement element = driver.findElement(By.id("kw"));
		WebElement element2 = driver.findElement(By.id("su"));
		element.sendKeys("abc");
		element2.click();
		Thread.sleep(3000);
		driver.close();
	}

}
