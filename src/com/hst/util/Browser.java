package com.hst.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Browser {
	
  public static WebDriver driver;
  
  @BeforeTest //BeforeTest：在测试集中的每个Test之前运行
	public WebDriver startFirefox(String url) throws Exception {
	  	try{
	  		//firefox下载地址 https://ftp.mozilla.org/pub/firefox/releases/65.0/win64/zh-CN/
	  		//selenium3.141   firefox65     gecko0.24 
	  		//selenium3.7.1   firefox57.0/62   gecko0.19.0
	  		//3.0版本以上要加该配置 D:\Program Files\Mozilla Firefox
	  		System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
	  		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();	
			//driver.get(url);
			//System.out.println("open blank page successfully");
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} 
		catch (Exception e) {
			System.out.println("open blank page failed, because: "+e);
		}
			return driver;
  	}

	public void tearDownBrowser() throws Exception {
		try {
			Thread.sleep(2000);
			driver.quit();
			System.out.println("close browser successfully");
		} 
		catch (Exception e) {
			System.out.println("close browser failed"+e);
		}
	}
	

}