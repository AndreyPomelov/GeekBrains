package test;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test1 implements Testable {

    public static final int PRIORITY = 3;

    @Test(priority = 5)
    public void test1() {
        System.out.println("Class 1 Test 1 done!");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Class 1 Test 2 done!");
    }

    @Test(priority = 5)
    public void test3() {
        System.out.println("Class 1 Test 3 done!");
    }

    @BeforeSuite
    public void init() {
        System.out.println("Class 1 Initialisation complete!");
    }

//    @BeforeSuite
//    public void init2() {
//        System.out.println("Class 1 Initialisation 2 complete!");
//    }

    @AfterSuite
    public void end() {
        System.out.println("Class 1 testing over!");
    }
}
