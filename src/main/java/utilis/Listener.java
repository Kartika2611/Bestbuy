package utilis;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.ProjectSpecification;


public class Listener  extends ProjectSpecification implements ITestListener{
		 
			
			//get physical report to extentreport class
			ExtentReports extent = Extentreportecommerce.getreport();
			ExtentTest test;

			//get implementation methods
			@Override
			public void onTestStart(ITestResult result) {
				test = extent.createTest(result.getMethod().getMethodName());
			}

			// if test pass the status is passed
			@Override
			public void onTestSuccess(ITestResult result) {
				test.log(Status.PASS, "TEST PASSED");
			}

			//if test failure the status is failure and take screenchot
			@Override
			public void onTestFailure(ITestResult result) {
				String name = null;
				test.fail(result.getThrowable());
				String filepath= "C:\\Users\\Kartika\\eclipse-workspace\\E-Commerce\\screenshots\\"+ name +".png";
				
				try {
					getScreenshot(result.getMethod().getMethodName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			}

			//test skipped 
			@Override
			public void onTestSkipped(ITestResult result) {
				System.out.println("Test Skipped");	
				}

			//test finish the report close
			@Override
			public void onFinish(ITestContext context) {
				extent.flush();
			}

		


	}


