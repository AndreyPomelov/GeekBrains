package test;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test5 implements Testable {

    public static final int PRIORITY = 4;

    @Test(priority = 7)
    public void test1() {
        System.out.println("Class 5 Test 1 done!");
    }

    @Test(priority = 9)
    public void test2() {
        System.out.println("Class 5 Test 2 done!");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Class 5 Test 3 done!");
    }

    @BeforeSuite
    public void init() {
        System.out.println("Class 5 Initialisation complete!");
    }

    @AfterSuite
    public void end() {
        System.out.println("Class 5 testing over!");
    }
}
