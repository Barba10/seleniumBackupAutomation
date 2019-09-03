package hr.ogcs.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hr.ogcs.qa.base.TestBase;
import hr.ogcs.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//Page Factory - Object Repository for LoginPage:
		@FindBy(id="os_username")
		WebElement username;
		
		@FindBy(name="os_password")
		WebElement password;
		
		@FindBy(id="loginButton")
		WebElement login_button;
		

		
		//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}

		public DownloadPage login(String un, String pwd){
//	    	childTest = parentTest.createNode("Logging In");
//	    	childTest.info("Vault Login Adress: https://login.veevavault.com");
	    	
			TestUtil.type(username, "UserName", un);
			TestUtil.type(password, "Password", pwd);
			TestUtil.click(login_button, "Log In");		
			return new DownloadPage();
		}

}
