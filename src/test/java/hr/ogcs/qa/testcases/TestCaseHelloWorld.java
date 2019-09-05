package hr.ogcs.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import hr.ogcs.qa.base.TestBase;
import hr.ogcs.qa.pages.DownloadPage;
import hr.ogcs.qa.pages.LoginPage;
import hr.ogcs.qa.util.ExcelUtils;
import hr.ogcs.qa.util.TestUtil;
import junit.framework.Assert;

public class TestCaseHelloWorld extends TestBase {

	LoginPage loginPage;
	DownloadPage downloadPage;


	File curDir = new File("/tmp/downloads/");
	public static void getAllFiles(File curDir) {
		
	    File[] filesList = curDir.listFiles();
	    for(File f : filesList){
	        if(f.isDirectory())
	            getAllFiles(f);
	        if(f.isFile()){
	            System.out.println(f.getName());
	        }
	    }

	}
	
	
	public TestCaseHelloWorld() {
		super();
	}

	@BeforeSuite
	public void setUp() throws MalformedURLException {
		initialization();
		System.out.println(System.getProperty("user.dir"));
//		TestUtil.deleteZippedReportDirectory();
		loginPage = new LoginPage();

		downloadPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "TestData")
	public Object[][] getData() throws Exception {

		Object[][] testObjArray = ExcelUtils.getTableArray(root + "/TestData.xlsx", "Sheet1");
		return (testObjArray);

	}

	@Test(dataProvider = "TestData")
	public void exportAndCopy(String data) throws Exception {
		downloadPage.exportSpace(data);
//		getAllFiles(curDir);
		DownloadPage.viewFiles("/tmp/downloads/");
		Assert.assertTrue(downloadPage.checkIfFileExists());

	}

//	@Test(dependsOnMethods = { "exportAndCopy" })
//	public void uploadToServer() throws IOException, JSchException, SftpException, InterruptedException {
//		System.out.println("Upload is starting");
//
//		TestUtil.pack("/tmp/downloads",  "/tmp/exportedSpaces" + date + ".zip");
//		System.out.println("Exports are zipped");
//
//		String user = "techuser";
//		String password = "Tg1503bacK!";
//		String host = "85.114.45.205";
//		int port = 22;
//
//		JSch jsch = new JSch();
//		Session session = jsch.getSession(user, host, port);
//		session.setPassword(password);
//		session.setConfig("StrictHostKeyChecking", "no");
//		session.connect();
//
//		System.out.println("Connection established.");
//		System.out.println("Creating SFTP Channel.");
//		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//		sftpChannel.connect();
//		System.out.println("SFTP Channel created.");
//		System.out.println("SFTP Channel put.");
//		System.out.println("File transfer");
//		sftpChannel.put("/tmp/exportedSpaces" + date + ".zip", "/home/techuser");
//		System.out.println("File transfered");
//		System.out.println("File transfer");
//
//		TestUtil.deleteFile("/tmp/exportedSpaces" + date + ".zip");
//		TestUtil.cleanDirectory("/tmp/downloads");
//
//	}
	
	
	
}
