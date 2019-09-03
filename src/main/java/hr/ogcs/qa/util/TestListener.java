package hr.ogcs.qa.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import hr.ogcs.qa.base.TestBase;

public class TestListener extends TestBase implements ITestListener{
	@Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
    	System.out.print("On finish \n");
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub	
    	//System.out.print("On start \n" + arg0.getName());

    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult arg0) {	
    	System.out.print("On test failure");
        // TODO Auto-generated method stub				
    	childTest.fail(arg0.getThrowable());
    	try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }		

    @Override		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
        // TODO Auto-generated method stub				
    	System.out.print("On test start " + arg0.getName() + "\n");

    }		

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
        // TODO Auto-generated method stub				

    }

}
