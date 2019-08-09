package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginElement;
import com.hst.pageElement.WorkHomeElement;
import com.hst.util.*;

public class WorkHome extends WorkHomeElement{
		
	

	public void workArea_createMeeting(WebDriver driver) throws InterruptedException{
		//driver.switch_to.frame(0)  # 1.用frame的index来定位，第一个是0
		//driver.switch_to.frame("frame1")  # 2.用id来定位
		//driver.switch_to.frame("myframe")  # 3.用name来定位
		//driver.switch_to.frame(driver.find_element_by_xpath("//iframe[contains(@src,'myframe')]"))
		driver.switchTo().frame(driver.findElement(WorkHomeElement.workAreaChild));
		Thread.sleep(500);//为了展示展开效果
		SeleniumUtil.click(driver.findElement(WorkHomeElement.workManage));	
		SeleniumUtil.click(driver.findElement(WorkHomeElement.meetingManage));
		SeleniumUtil.click(driver.findElement(WorkHomeElement.createMeeting));
	}

	
	
	
}
