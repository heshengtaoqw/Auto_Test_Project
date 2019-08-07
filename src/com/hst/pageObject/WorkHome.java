package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hst.pageElement.DakaLoginElement;
import com.hst.pageElement.WorkHomeElement;
import com.hst.util.*;

public class WorkHome extends WorkHomeElement{
		
	public void switch_iframe(WebDriver driver) {
		try {
			//driver.switch_to.frame(0)  # 1.��frame��index����λ����һ����0
			//driver.switch_to.frame("frame1")  # 2.��id����λ
			//driver.switch_to.frame("myframe")  # 3.��name����λ
			//driver.switch_to.frame(driver.find_element_by_xpath("//iframe[contains(@src,'myframe')]"))	
			driver.switchTo().frame(driver.findElement(WorkHomeElement.workFrameParent));
			driver.switchTo().frame(driver.findElement(WorkHomeElement.workFrameChild));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void click_workManage(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);//Ϊ��չʾչ��Ч��
		SeleniumUtil.click(driver.findElement(WorkHomeElement.workManage));	
	}
	
	public void click_meetingManage(WebDriver driver) throws InterruptedException {
		SeleniumUtil.click(driver.findElement(WorkHomeElement.meetingManage));
	}
	
	public void click_createMeeting(WebDriver driver) {
		SeleniumUtil.click(driver.findElement(WorkHomeElement.createMeeting));
	}

	
}
