package com.hst.pageElement;

import org.openqa.selenium.*;

public class WorkHomeElement {
	
		//xpath("//input[contains(@aria-label,'�۸���Сֵ')]")
		//xpath = "//input[@value='abc']")
		//(xpath="//span[contains(text(),'abc')]")
		public static final By workManage = By.xpath("//dt[contains(text(),'�칫����')]");
		public static final By meetingManage = By.xpath("//dt[contains(text(),'�������')]");
		public static final By createMeeting = By.xpath("//dd[contains(text(),'�������')]");
		public static final By workFrameParent = By.xpath("//iframe[contains(@id,'_e_1')]");;
		//public static final String workFrameParent = "_e_1";
		public static final By workFrameChild = By.xpath("//div[contains(@id,'_e_20')]/iframe");
		
}
