package com.hst.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

public class LoginPage {

	private String name = "j_username";
	private String pwd = "j_password";
	private String submit = "Submit";
	private String loginErrorPage = "http://10.31.0.56/loginError";
								

	//��¼����
	
	public void login(WebDriver driver, String username, String password) throws Exception {

		System.out.println("�û���Ϊ: " + username + ", ����Ϊ: " + password );
		driver.findElement(By.name(name)).sendKeys(username);
		driver.findElement(By.name(pwd)).sendKeys(password);
		
		// �����¼
		Thread.sleep(2000);
		driver.findElement(By.name(submit)).click();
		//driver.findElement(By.xpath("//span[contains(text(),'��¼')]")).click();
		// System.out.println(driver.getCurrentUrl());
	}

 
	//�ж��Ƿ��¼�ɹ�
	public boolean isLoginSuccess(WebDriver driver) throws Exception{
		
		boolean flag = false;
		try {
			
			if(driver.getCurrentUrl().equals(loginErrorPage)){
				System.out.println("��¼ʧ��");
				flag=false;
				return flag;
			}
			else if(driver.findElement(By.linkText("�½�����")).isDisplayed())
			{	
				System.out.println("��¼�ɹ�");
				flag = true;
				return flag;
			}
			else{
				System.out.println("��¼ҳ���쳣");
				flag=false;
				return flag;
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		return flag;
	}
}

/*
	public String loginStatus(WebDriver driver) throws Exception {
		
		try{			
			
			if(isLoginSuccess(WebDriver driver))
			String actualResult = driver.findElement(By.xpath("//class='main_txt'/span[1]")).getText();
			return actualResult;
		}

		catch(Exception e)
		{}
		return name;
		}

 

 

public  boolean isLoginPage(WebDriver driver) throws Exception {

boolean flag = false;

try {

if (driver.findElement(By.id(forLogin)).getAttribute("value").equals("��¼")) {

flag = true;

return flag;

}

} catch (Exception e) {

//     System.out.println(e);

return flag;

}

return flag;

}

}
*/