package com.tms.util.listners;

import com.tms.base.BaseTest;
import com.tms.util.excelutils.ExcelUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListners extends BaseTest implements ITestListener {

//    private static String getTestMethodName(ITestResult iTestResult) {
//        return iTestResult.getMethod().getConstructorOrMethod().getName();
//    }


//    @Override
//    public void onTestSuccess(ITestResult iTestResult) {
//        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
//        //ExtentReports log operation for passed tests.
////        getTest().log(Status.PASS, "Test passed");
//        //Update the test result on excel sheet
//        ExcelUtil.setCellData("PASSED", ExcelUtil.rowNumber, ExcelUtil.columnNumber);
//    }

    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result)
    {
        System.out.println("The name of the testcase failed is :"+Result.getName());
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("The name of the testcase Skipped is :"+Result.getName());
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {
        System.out.println(Result.getName()+" test case started");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("The name of the testcase passed is :"+Result.getName());
        ExcelUtil.setCellData("PASSED", ExcelUtil.rowNumber, ExcelUtil.columnNumber);
    }

}
