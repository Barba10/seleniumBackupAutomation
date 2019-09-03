package hr.ogcs.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import hr.ogcs.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener{
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		//System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		//System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		//System.out.println("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		//System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		//System.out.println("Trying to click on: " + element.toString());
		//System.out.println("Element get text: " + element.getText());
		try {
			Thread.sleep(1000);
			System.out.print("Thread sleep 1000 \n");;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
	//	System.out.print("Element get text:" + element.getText() + "\n");
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		//System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		//System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		//System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		//System.out.println("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		//System.out.println("Exception occured: " + error);
		/*
		 * try { TestUtil.takeScreenshotAtEndOfTest(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		//System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		//System.out.println("Found Element By : " + by.toString());
	}
	
	public void afterGetText(WebElement element, WebDriver arg1, String text) {
		// TODO Auto-generated method stub
		//System.out.println("After get text" + element.toString());
		//System.out.println("Text we got " + text);
	}
	
	public void beforeGetText(WebElement element, WebDriver arg1) {
		// TODO Auto-generated method stub
		//System.out.println("Trying to get text:  " + element.toString());
	}
	
	

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}
