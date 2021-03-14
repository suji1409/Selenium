package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("Execution started for Test scenario : " +result.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Execution Passed for Test Scenario : " +result.getTestClass().getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Execution Failed for Test Scenario : " +result.getTestClass().getName());
        BaseTest.takeSnapShot(result.getMethod().getMethodName()+" FAILED");
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
        Reporter.log("Execution started for Test Suite : " +context.getSuite().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Execution Ended for Test Suite : " +context.getSuite().getName());
    }
}
