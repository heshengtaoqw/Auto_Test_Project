package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginElement;
import com.hst.pageElement.WorkHomeElement;
import com.hst.util.*;

public class WorkHome extends WorkHomeElement{
		
	

	public void workArea_createMeeting(WebDriver driver) throws InterruptedException{
		//driver.switch_to.frame(0)  # 1.��frame��index����λ����һ����0
		//driver.switch_to.frame("frame1")  # 2.��id����λ
		//driver.switch_to.frame("myframe")  # 3.��name����λ
		//driver.switch_to.frame(driver.find_element_by_xpath("//iframe[contains(@src,'myframe')]"))
		driver.switchTo().frame(driver.findElement(WorkHomeElement.workAreaChild));
		Thread.sleep(500);//Ϊ��չʾչ��Ч��
		SeleniumUtil.click(driver.findElement(WorkHomeElement.workManage));	
		SeleniumUtil.click(driver.findElement(WorkHomeElement.meetingManage));
		SeleniumUtil.click(driver.findElement(WorkHomeElement.createMeeting));
	}

	
	
	
}
