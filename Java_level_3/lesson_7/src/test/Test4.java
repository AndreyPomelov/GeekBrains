package test;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test4 implements Testable {

    public static final int PRIORITY = 5;

    @Test(priority = 4)
    public void test1() {
        System.out.println("Class 4 Test 1 done!");
    }

    @Test(priority = 6)
    public void test2() {
        System.out.println("Class 4 Test 2 done!");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("Class 4 Test 3 done!");
    }

    @BeforeSuite
    public void init() {
        System.out.println("Class 4 Initialisation complete!");
    }

    @AfterSuite
    public void end() {
        System.out.println("Class 4 testing over!");
    }
}
