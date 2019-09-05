package hr.ogcs.qa.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import hr.ogcs.qa.base.TestBase;
import hr.ogcs.qa.util.TestUtil;

public class DownloadPage extends TestBase {

	JSch jsch = new JSch();

	// Initializing the Page Objects:
	public DownloadPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='confirm']")
	WebElement exportButton;

	@FindBy(xpath = "//a[@class='space-export-download-path']")
	WebElement hereButton;

	public void exportSpace(String name) {

		driver.get("https://confluencedocker.og-cs.hr/spaces/exportspacexml.action?key=" + name);
		wait.until(ExpectedConditions.elementToBeClickable(exportButton));
		TestUtil.click(exportButton, "Export Button");
		wait.until(ExpectedConditions.visibilityOf(hereButton));
		TestUtil.click(hereButton, "Here Button");

	}

//	public void copyXmlFile() throws Exception {
//		String user = "techuser";
//	    String password = "Tg1503bacK!";
//	    String host = "85.114.45.205";
//	    int port=22;
//		
//		Session session = jsch.getSession(user, host);
//		session.setPassword(password);
//		session.connect();
//
//		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
//		sftpChannel.connect();
//
//		sftpChannel.put("C:/source/local/path/file.zip", "/target/remote/path/file.zip");
//		
//		
//	}

	public void upload() {
		String login = "techuser";
		String server = "85.114.45.205";
		;
		String password = "Tg1503bacK!";
//		String workingDirectory;
		int port = 22;

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(login, server, port);
			session.setPassword(password);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
//	        ChannelSftp channelSftp = (ChannelSftp) channel;
//	        channelSftp.cd(workingDirectory);
//	        File f = new File(filename);
//
//	        channelSftp.put(new FileInputStream(f), f.getName());
//
//	        f.delete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean checkIfFileExists() {
		System.out.println("Checking if file is downloaded");
		String link = driver.findElement(By.xpath("//a[@class='space-export-download-path']")).getAttribute("href");
		String fileName = link.substring(48);
		boolean test = false;
		long t= System.currentTimeMillis();
		long end = t+25000;
		
		File dir = new File("/tmp/downloads/");
		while (test == false && (System.currentTimeMillis() < end)) {
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.equals(fileName);
				}
			};

			String[] children = dir.list(filter);

			if (children == null) {
				System.out.println("Either dir does not exist or is not a directory");
			} else {
				for (int i = 0; i < children.length; i++) {
					if(fileName.equals(children[i])) {
					String filename = children[i];
					System.out.println("File " + filename + " is downloaded");
					test = true;
					return true;
					}
				}
			}
		}
		return false; 
	}
	
	public static void viewFiles(String directory) {
		System.out.print("View files enter \n");
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
	
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName() + "\n");
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName()  + "\n");
		  }
		}
		
	}


	
	
}
