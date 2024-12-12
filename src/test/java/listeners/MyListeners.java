package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtendReporter;

public class MyListeners implements ITestListener {
    ExtentReports extendReport = ExtendReporter.getExtendReport();
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
      extentTest= extendReport.createTest(result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
       extentTest.log(Status.PASS,"test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL,"test failed");
        extentTest.fail(result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    extendReport.flush();
    }
}
