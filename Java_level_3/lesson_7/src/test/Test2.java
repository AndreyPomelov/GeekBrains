package test;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test2 implements Testable {

    public static final int PRIORITY = 1;

    @Test(priority = 6)
    public void test1() {
        System.out.println("Class 2 Test 1 done!");
    }

    @Test(priority = 4)
    public void test2() {
        System.out.println("Class 2 Test 2 done!");
    }

    @Test(priority = 8)
    public void test3() {
        System.out.println("Class 2 Test 3 done!");
    }

    @BeforeSuite
    public void init() {
        System.out.println("Class 2 Initialisation complete!");
    }

    @AfterSuite
    public void end() {
        System.out.println("Class 2 testing over!");
    }
}
