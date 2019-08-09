package com.hst.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;


public class SeleniumUtil extends BaseTest{
	
	public static void click(WebElement element) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchWindows() {
		try {
			Thread.sleep(1000);
			String currentWin = driver.getWindowHandle();
			Set<String> AllWins = driver.getWindowHandles();
			for(String i:AllWins) {
				if(currentWin.equals(i)) {
					continue;
				}
				driver.switchTo().window(i);
			}
			System.out.println("switch window successfully");
		} 
		catch (Exception e) {
			System.out.println("switch window failed: "+e);
		}
	}
	
	public static void screenshots(String s) {
		String destDir = ".//test-output//screenshots//";
		String destFile = destDir + s +".png";
			
	    File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);    
	    
	    try {
	    	if(FileUtils.directoryContains(new File(destDir),new File(destFile))) {
		    	FileUtils.forceDelete(new File(destFile));
	    	}
	    	FileUtils.moveFile(src, new File(destFile));
	    }    
	    catch (IOException e)
	     {
	    	System.out.println(e.getMessage());
	     }
	}
	

	
	
	
	
}
