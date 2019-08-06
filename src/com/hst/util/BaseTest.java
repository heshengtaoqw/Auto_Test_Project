package com.hst.util;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.hst.util.Browser;


public class BaseTest {
	
	public String LoginURL; 
	private static String URL = "https://www.baidu.com";
	Browser useBrowser = new Browser();
	public static WebDriver driver;
	
	
	@BeforeSuite //BeforeSuite：在整个测试集运行之前运行；
	public void start() throws Exception {
		try {
			driver = useBrowser.startFirefox(URL);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@DataProvider(name = "login")  
    public Object[][] dp() throws Exception {
        return readCSV("D:\\login1.txt");    
    }
	
	public static Object[][] readCSV(String fileName) throws Exception
	  {
		 List<Object[]> records = new ArrayList<Object[]>();
	        String record;
	        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
	        file.readLine();                           
	        while ((record=file.readLine())!=null){        
	            String fields[] =  record.split(",");  
	            records.add(fields);                   
	        }
	        file.close();  //�ر��ļ�����

	        Object[][] results = new Object[records.size()][]; 
	        for (int i=0; i<records.size();i++){            
	            results[i] = records.get(i);               
	        }
	        return results;

	  }
	
	public static void switchWindows() {
		try {
			Thread.sleep(2000);
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

 
	@AfterSuite
	public void end() throws Exception {
		useBrowser.tearDownBrowser();
	}
}