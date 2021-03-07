package test;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test3 implements Testable {

    public static final int PRIORITY = 2;

    @Test(priority = 7)
    public void test1() {
        System.out.println("Class 3 Test 1 done!");
    }

    @Test(priority = 5)
    public void test2() {
        System.out.println("Class 3 Test 2 done!");
    }

    @Test(priority = 8)
    public void test3() {
        System.out.println("Class 3 Test 3 done!");
    }

    @BeforeSuite
    public void init() {
        System.out.println("Class 3 Initialisation complete!");
    }

    @AfterSuite
    public void end() {
        System.out.println("Class 3 testing over!");
    }

//    @AfterSuite
//    public void end2() {
//        System.out.println("Class 3 testing over! Now for sure.");
//    }
}
