package com.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static String reportPath = "src/main/java/com/reports/extent-report.html";

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            extentReports.attachReporter(getHtmlReporter());
        }
        return extentReports;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        createExtentReportFolder(reportPath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        return htmlReporter;
    }

    private static void createExtentReportFolder(String reportPath) {
        File report = new File(reportPath);
        if (!report.getParentFile().exists()) {
            report.getParentFile().mkdirs();
        }
    }
}
