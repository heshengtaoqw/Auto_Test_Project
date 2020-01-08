package com.hst.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.URL;

public class Browser {
	
  public static WebDriver driver;
  public String browser;
  
  
  @BeforeTest //BeforeTest：在测试集中的每个Test之前运行
  @Parameters({"browser"})
	public WebDriver startFirefox(String url) throws Exception {
	  	try{
	  		//firefox下载地址 https://ftp.mozilla.org/pub/firefox/releases/65.0/win64/zh-CN/
	  		//selenium3.141   firefox65     gecko0.24 
	  		//selenium3.7.1   firefox57.0/62.03   gecko0.19.0
	  		//3.0版本以上要加该配置 D:\Program Files\Mozilla Firefox
	  		//
	  		
	  		System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
	  		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
	  		
	  		//自动化分布式测试
	  		/*
	  		if(browser.equals("firefox")) {
	  			driver = new RemoteWebDriver(new URL("nodeIp"), DesiredCapabilities.firefox());	
	  		}else if(browser.equals("chrome")) {
	  			driver = new RemoteWebDriver(new URL("nodeIp"), DesiredCapabilities.firefox());	
	  		}else {
	  			driver = new RemoteWebDriver(new URL("http://192.168.99.100:5555/wd/hub/"), DesiredCapabilities.internetExplorer());	
	  		}
	  		*/
	  		
	  		driver = new FirefoxDriver();
	  		driver.get(url);

		} 
		catch (Exception e) {
			System.out.println("open blank page failed, because: "+e);
		}
			return driver;
  	}

	public void tearDownBrowser() throws Exception {
		try {
			driver.quit();
			System.out.println("close browser successfully");
		} 
		catch (Exception e) {
			System.out.println("close browser failed "+e);
		}
	}
	

}