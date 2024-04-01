package pawaracademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+ "\\report\\index.html"; 
		ExtentSparkReporter extentsparkreport = new ExtentSparkReporter(path);
		extentsparkreport.config().setReportName("Web Automation Result");
		extentsparkreport.config().setDocumentTitle("Test Result");
		
		ExtentReports extentReport = new  ExtentReports();
		extentReport.attachReporter(extentsparkreport);
		extentReport.setSystemInfo("Tester", "Mahesh Pawar");
		
		return extentReport;
		
	}
}
