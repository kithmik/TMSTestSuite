package com.tms.util.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Malsha Nishadini
 * Created extentTestMap which holds the information of thread ids and ExtentTest instances.
 * Put current thread id into extentTestMap using startTest() method.
 * Returned ExtentTest instance in extentTestMap by using current thread id invoking getTest() method.
 *
 */

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getExtentReports();

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

}





