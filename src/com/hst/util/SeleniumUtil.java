package com.hst.util;

import org.openqa.selenium.*;


public class SeleniumUtil extends BaseTest{
	
	public static void click(WebElement element) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
