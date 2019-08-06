package com.hst.util;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;


/**
 * @author xy-incito-wy
 * @Description 包装�?有selenium的操作以及�?�用方法，简化用例中代码�?
 * */
public class SeleniumUtil {
	/** 使用Log4j，第�?步就是获取日志记录器，这个记录器将负责控制日志信�? */
	public static Logger logger = Logger.getLogger(SeleniumUtil.class.getName());
	public ITestResult it = null;
	public WebDriver driver = null;
	public WebDriver window = null;


	/***
	 * 启动浏览器并打开页面
	 * */
	public void launchBrowser(String browserName, ITestContext context,String webUrl,int timeOut) {
		SelectBrowser select = new SelectBrowser();
		driver = select.selectExplorerByName(browserName, context);
		try {
			maxWindow(browserName);
			waitForPageLoading(timeOut);
			get(webUrl);
		} catch (TimeoutException e) {
			logger.warn("注意：页面没有完全加载出来，刷新重试！！");
			refresh();
			 JavascriptExecutor js = (JavascriptExecutor)driver;
			String status= (String)js.executeScript("return document.readyState");
		
			
			logger.info("打印状�?�："+status);
		}

	}

	
	/**
	 * �?大化浏览器操�?
	 * */
	public void maxWindow(String browserName) {
		logger.info("�?大化浏览�?:" + browserName);
		driver.manage().window().maximize();
	}

	/**
	 * 设定浏览器窗口大小： 设置浏览器窗口的大小有下面两个比较常见的用�?�：<br>
	 * 1、在统一的浏览器大小下运行用例，可以比较容易的跟�?些基于图像比对的工具进行结合
	 * ，提升测试的灵活性及普遍适用性�?�比如可以跟sikuli结合，使用sikuli操作flash�?<br>
	 * 2、在不同的浏览器大小下访问测试站点，对测试页面截图并保存，然后观察或使用图像比对工具对被测页面的前端样式进行评测�?
	 * 比如可以将浏览器设置成移动端大小(320x480)，然后访问移动站点，对其样式进行评估�?<br>
	 * */
	public void setBrowserSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	/**
	 * 包装查找元素的方�? element
	 * */
	public WebElement findElementBy(By by) {
		return driver.findElement(by);
	}

	/**
	 * 包装查找元素的方�? elements
	 * */
	public List<WebElement> findElementsBy(By by) {
		return driver.findElements(by);
	}

	/**
	 * 包装点击操作
	 * */
	public void click(By byElement) {

		try {
			clickTheClickable(byElement, System.currentTimeMillis(), 2500);
		} catch (StaleElementReferenceException e) {
			logger.error("The element you clicked:[" + byElement + "] is no longer exist!");
			Assert.fail("The element you clicked:[" + byElement + "] is no longer exist!");
		} catch (Exception e) {
			logger.error("Failed to click element [" + byElement + "]");
			Assert.fail("Failed to click element [" + byElement + "]",e);
		}
		logger.info("点击元素 [" + byElement + "]");
	}

	/** 不能点击时�?�重试点击操�? */
	public void clickTheClickable(By byElement, long startTime, int timeOut) throws Exception {
		try {
			findElementBy(byElement).click();
		} 
		catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeOut) {
				logger.warn(byElement+ " is unclickable");
				throw new Exception(e);
			} else {
				Thread.sleep(500);
				logger.warn(byElement + " is unclickable, try again");
				clickTheClickable(byElement, startTime, timeOut);
			}
		}
	}

	/**
	 * 获得页面的标�?
	 * */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 获得元素的文�?
	 * */
	public String getText(By elementLocator) {
		return driver.findElement(elementLocator).getText().trim();
	}

	/**
	 * 获得元素 属�?�的文本
	 * */
	public String getAttributeText(By elementLocator, String attribute) {
		return driver.findElement(elementLocator).getAttribute(attribute).trim();
	}

	/**
	 * 包装清除操作
	 * */
	public void clear(By byElement) {
		try {
			findElementBy(byElement).clear();
		} catch (Exception e) {
			logger.error("清除元素 [" + byElement + "] 上的内容失败!");
		}
		logger.info("清除元素 [" + byElement  + "]上的内容");
	}

	/**
	 * 向输入框输入内容
	 * */
	public void type(By byElement, String key) {
		try {
			findElementBy(byElement).sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("输入 [" + key + "] �? 元素[" + byElement + "]失败");
			Assert.fail("输入 [" + key + "] �? 元素[" + byElement + "]失败");
		}
		logger.info("输入：[" + key + "] �? [" + byElement + "]");
	}

	/**
	 * 模拟键盘操作�?,比如Ctrl+A,Ctrl+C�? 参数详解�?<br>
	 * 1、WebElement element - 要被操作的元�? <br>
	 * 2、Keys key- 键盘上的功能�? 比如ctrl ,alt�? <br>
	 * 3、String keyword - 键盘上的字母
	 * */
	public void pressKeysOnKeyboard(WebElement element, Keys key, String keyword) {

		element.sendKeys(Keys.chord(key, keyword));
	}

	/**
	 * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
	 * */
	public void waitForElementToLoad(int timeOut, final By By) {
		logger.info("�?始查找元素[" + By + "]");
		try {
			/*(new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(By);
					return element.isDisplayed();
				}
			});*/
			} 
		catch (TimeoutException e) {
			logger.error("超时!! " + timeOut + " 秒之后还没找到元�? [" + By + "]");
			Assert.fail("超时!! " + timeOut + " 秒之后还没找到元�? [" + By + "]");

		}
		logger.info("找到了元�? [" + By + "]");
	}


	/**
	 * 判断文本是不是和�?求要求的文本�?�?
	 * **/
	public void isTextCorrect(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			logger.error("期望的文字是 [" + expected + "] 但是找到�? [" + actual + "]");
			Assert.fail("期望的文字是 [" + expected + "] 但是找到�? [" + actual + "]");

		}
		logger.info("找到了期望的文字: [" + expected + "]");

	}

	/**
	 * 判断编辑框是不是可编�?
	 * */
	public void isInputEdit(WebElement element) {

	}

	/**
	 * 等待alert出现
	 * */
	public Alert switchToPromptedAlertAfterWait(long waitMillisecondsForAlert) throws NoAlertPresentException {
		final int ONE_ROUND_WAIT = 200;
		NoAlertPresentException lastException = null;

		long endTime = System.currentTimeMillis() + waitMillisecondsForAlert;

		for (long i = 0; i < waitMillisecondsForAlert; i += ONE_ROUND_WAIT) {

			try {
				Alert alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				lastException = e;
			}
			pause(ONE_ROUND_WAIT);

			if (System.currentTimeMillis() > endTime) {
				break;
			}
		}
		throw lastException;
	}

	/**
	 * 暂停当前用例的执行，暂停的时间为：sleepTime
	 * */
	public void pause(int sleepTime) {
		if (sleepTime <= 0) {
			return;
		}
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �?�?
	 * */
	public void quit() {
		driver.quit();
	}

	/**
	 * 切换frame - 根据String类型（frame名字�?
	 * */
	public void inFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	/**
	 * 切换frame - 根据frame在当前页面中的顺序来定位
	 * */
	public void inFrame(int frameNum) {
		driver.switchTo().frame(frameNum);
	}

	/**
	 * 切换frame - 根据页面元素定位
	 * */
	public void switchFrame(WebElement element) {
		try {
			logger.info("正在跳进frame:[" + getLocatorByElement(element, ">") + "]");
			driver.switchTo().frame(element);
		} catch (Exception e) {
			logger.info("跳进frame: [" + getLocatorByElement(element, ">") + "] 失败");
			Assert.fail("跳进frame: [" + getLocatorByElement(element, ">") + "] 失败");
		}
		logger.info("进入frame: [" + getLocatorByElement(element, ">") +"]成功 ");
	}

	/**
	 * 选择下拉选项 -根据value
	 * */
	public void selectByValue(By by, String value) {
		Select s = new Select(driver.findElement(by));
		s.selectByValue(value);
	}

	/**
	 * 选择下拉选项 -根据index角标
	 * */
	public void selectByIndex(By by, int index) {
		Select s = new Select(driver.findElement(by));
		s.selectByIndex(index);
	}

	/** �?查checkbox是不是勾�? */
	public boolean doesCheckboxSelected(By elementLocator) {
		if (findElementBy(elementLocator).isSelected() == true) {
			logger.info("CheckBox: " + getLocatorByElement(findElementBy(elementLocator), ">") + " 被勾�?");
			return true;
		} else
			logger.info("CheckBox: " + getLocatorByElement(findElementBy(elementLocator), ">") + " 没有被勾�?");
		return false;

	}

	/**
	 * 选择下拉选项 -根据文本内容
	 * */
	public void selectByText(By by, String text) {
		Select s = new Select(driver.findElement(by));
		s.selectByVisibleText(text);
	}
	
	/**
	 * 获得当前select选择的�??
	 * */
	public List<WebElement> getCurrentSelectValue(By by){
		List<WebElement> options = null;
		Select s = new Select(driver.findElement(by));
			options =  s.getAllSelectedOptions();
			return options;
	}

	/**
	 * 执行JavaScript 方法
	 * */
	public void executeJS(String js) {
		((JavascriptExecutor) driver).executeScript(js);
		logger.info("执行JavaScript语句：[" + js + "]");
	}
	
	/**
	 * 获得输入框的�? 这个方法 是针对某些input输入�? 没有value属�?�，但是又想取得input�? 值得方法
	 * */
	public String getInputValue(String chose,String choseValue) {
		String value = null;
		switch(chose.toLowerCase()){
		case "name":
			 String jsName = "return document.getElementsByName('"+choseValue+"')[0].value;"; //把JS执行的�?? 返回出去
			 value = (String)((JavascriptExecutor) driver).executeScript(jsName);
			 break;
			
		case "id":
			 String jsId = "return document.getElementById('"+choseValue+"').value;"; //把JS执行的�?? 返回出去
			 value = (String)((JavascriptExecutor) driver).executeScript(jsId);
			 break;
		
			default:
				logger.error("未定义的chose:"+chose);
				Assert.fail("未定义的chose:"+chose);
		
		}
		return value;

	}

	/**
	 * 执行JavaScript 方法和对�?
	 * 用法：seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(MyOrdersPage.MOP_TAB_ORDERCLOSE));
	 * */
	public void executeJS(String js, Object... args) {
		((JavascriptExecutor) driver).executeScript(js, args);
		logger.info("执行JavaScript语句：[" + js + "]");
	}

	/**
	 * get方法包装
	 * */
	public void get(String url) {
		driver.get(url);
		logger.info("打开测试页面:[" + url + "]");
	}

	/**
	 * close方法包装
	 * */
	public void close() {
		driver.close();
	}

	/**
	 * 刷新方法包装
	 * */
	public void refresh() {
		driver.navigate().refresh();
		logger.info("页面刷新成功�?");
	}

	/**
	 * 后�??方法包装
	 * */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 前进方法包装
	 * */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 包装selenium模拟鼠标操作 - 鼠标移动到指定元�?
	 * */
	public void mouseMoveToElement(By by) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.moveToElement(driver.findElement(by));
		mouse.perform();
	}

	/**
	 * 包装selenium模拟鼠标操作 - 鼠标移动到指定元�?
	 * */
	public void mouseMoveToElement(WebElement element) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.moveToElement(element);
		mouse.perform();
	}
	
	/**
	 * 包装selenium模拟鼠标操作 - 鼠标右击
	 * */
	public void mouseRightClick(By element) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.contextClick(findElementBy(element));
		mouse.perform();
	}

	/**
	 * 添加cookies,做自动登陆的必要方法
	 * */
	public void addCookies(int sleepTime) {
		pause(sleepTime);
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie c : cookies) {
			System.out.println(c.getName() + "->" + c.getValue());
			if (c.getName().equals("logisticSessionid")) {
				Cookie cook = new Cookie(c.getName(), c.getValue());
				driver.manage().addCookie(cook);
				System.out.println(c.getName() + "->" + c.getValue());
				System.out.println("添加成功");
			} else {
				System.out.println("没有找到logisticSessionid");
			}

		}

	}

	/** 获得CSS value */
	public String getCSSValue(WebElement e, String key) {

		return e.getCssValue(key);
	}

	/** 使用testng的assetTrue方法 */
	public void assertTrue(WebElement e, String content) {
		String str = e.getText();
		Assert.assertTrue(str.contains(content), "字符串数组中不含有：" + content);

	}

	/** 跳出frame */
	public void outFrame() {
		driver.switchTo().defaultContent();
	}

	// webdriver中可以设置很多的超时时间
	/** implicitlyWait。识别对象时的超时时间�?�过了这个时间如果对象还没找到的话就会抛出NoSuchElement异常 */
	public void implicitlyWait(int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/** setScriptTimeout。异步脚本的超时时间。webdriver可以异步执行脚本，这个是设置异步执行脚本脚本返回结果的超时时�? */
	public void setScriptTimeout(int timeOut) {
		driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * pageLoadTimeout。页面加载时的超时时间�?�因为webdriver会等页面加载完毕在进行后面的操作�?
	 * �?以如果页面在这个超时时间内没有加载完成，那么webdriver就会抛出异常
	 */

	public void waitForPageLoading(int pageLoadTime) {
		driver.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
	}

	/** 根据元素来获取此元素的定位�?? */
	public String getLocatorByElement(WebElement element, String expectText) {
		String text = element.toString();
		String expect = null;
		try {
			expect = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("failed to find the string [" + expectText + "]");

		}

		return expect;

	}

	/**
	 * 这是�?堆相同的elements�? 选择 其中方的 �?�? 然后在这个�?�定的中 继续定位
	 * */
	public WebElement getOneElement(By bys, By by, int index) {
		return findElementsBy(bys).get(index).findElement(by);
	}

	/**
	 * 上传文件，需要点击弹出上传照片的窗口才行
	 * 
	 * @param brower
	 *            使用的浏览器名称
	 * @param file
	 *            �?要上传的文件及文件名
	 */
	public void handleUpload(String browser, File file) {
		String filePath = file.getAbsolutePath();
		String executeFile = "res/script/autoit/Upload.exe";
		String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @Description 对于windows GUI弹出框，要求输入用户名和密码时，
	 *              seleniumm不能直接操作，需要�?�助http://modifyusername:modifypassword@yoururl 这种方法
	 * 
	 * */
	public void loginOnWinGUI(String username, String password, String url) {
		driver.get(username + ":" + password + "@" + url);
	}

	/** �?查元素是否显�? */
	public boolean isDisplayed(WebElement element) {
		boolean isDisplay = false;
		if (element.isDisplayed()) {
			logger.info("The element: [" + getLocatorByElement(element, ">") + "] is displayed");
			isDisplay = true;
		} else if (element.isDisplayed() == false) {
			logger.warn("The element: [" + getLocatorByElement(element, ">") + "] is not displayed");

			isDisplay = false;
		}
		return isDisplay;
	}
	
	/**�?查元素是不是存在*/
	public  boolean doesElementsExist(By byElement){
		try{
		findElementBy(byElement);
		return true;
		}catch(NoSuchElementException nee){
			
			return false;
		}
		
		
	}
	
	/** �?查元素是否被勾�?? */
	public boolean isSelected(WebElement element) {
		boolean flag = false;
		if (element.isSelected() == true) {
			logger.info("The element: [" + getLocatorByElement(element, ">") + "] is selected");
			flag = true;
		} else if (element.isSelected() == false) {
			logger.info("The element: [" + getLocatorByElement(element, ">") + "] is not selected");
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断实际文本时�?�包含期望文�?
	 * 
	 * @param actual
	 *            实际文本
	 * @param expect
	 *            期望文本
	 */
	public void isContains(String actual, String expect) {
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			logger.error("The [" + actual + "] is not contains [" + expect + "]");
			Assert.fail("The [" + actual + "] is not contains [" + expect + "]");
		}
		logger.info("The [" + actual + "] is contains [" + expect + "]");
	}


	/** 获得屏幕的分辨率 - �? */
	public static double getScreenWidth() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}

}
