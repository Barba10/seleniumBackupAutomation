package hr.ogcs.qa.testcases;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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

	public TestCaseHelloWorld() {
		super();
	}

	@BeforeSuite
	public void setUp() throws MalformedURLException {
		initialization();
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
		Thread.sleep(5000);
//		File file = new File(root +  "/exports" + "/datoteka" + date +".txt");
//		file.createNewFile();
		DownloadPage.viewFiles(root  + "/exports");
		Assert.assertTrue(downloadPage.checkIfFileExists());

//		if(downloadPage.checkIfFileExists() != true)
//			Assert.fail();

	}

	@Test(dependsOnMethods = { "exportAndCopy" })
	public void uploadToServer() throws IOException, JSchException, SftpException, InterruptedException {
		System.out.println("Upload is starting");

		TestUtil.pack(root + "/exports", root + "/exportedSpaces" + date + ".zip");
		System.out.println("Exports are zipped");

		String user = "techuser";
		String password = "Tg1503bacK!";
		String host = "85.114.45.205";
		int port = 22;

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();

		System.out.println("Connection established.");
		System.out.println("Creating SFTP Channel.");
		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();
		System.out.println("SFTP Channel created.");
		System.out.println("SFTP Channel put.");

		sftpChannel.put(root + "/exportedSpaces" + date + ".zip", "/home/techuser");

		System.out.println("File transfer");

		TestUtil.deleteFile(root + "/exportedSpaces" + date + ".zip");
		TestUtil.cleanDirectory(root + "/exports");

	}
}
