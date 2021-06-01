package com.tms.util.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * @author Malsha Nishadini
 * Created an ExtentReports object and set the location of report HTML file.
 *
 */

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("TMS Test Automation Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}





