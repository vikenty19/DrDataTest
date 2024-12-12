package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporter {
    public static ExtentReports getExtendReport(){
        String extendReportFilePath = System.getProperty("user.dir")+"/ExtendReports/extendReport.html";
        //create ExtendSparkReporter -add maven dependency
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extendReportFilePath);
        //create name
        extentSparkReporter.config().setReportName("TutorialNinja Test results");
        //set doc title
        extentSparkReporter.config().setDocumentTitle("TutorialNinja Test Automation results");
        //create ExtendReport
        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Selenium Version","4.25.0");
        extentReport.setSystemInfo("Operating System","Windows 10");
        extentReport.setSystemInfo("Executed by", "Vikenty Plakhov");
        return extentReport;

    }
}
