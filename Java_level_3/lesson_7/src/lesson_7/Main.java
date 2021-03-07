package lesson_7;

import annotation.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 10;
    public static final String FIELD_NAME = "PRIORITY";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        start(Test1.class, Test2.class, Test3.class, Test4.class, Test5.class);
    }

    public static <Testable> void start(Class<? extends Testable>... testClass) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int i = MIN_PRIORITY; i <= MAX_PRIORITY; i++) {
            for (Class<? extends Testable> a : testClass) {
                if (a.getField(FIELD_NAME).getInt(a) == i) {
                    runTestMethods(a);
                }
            }
        }
    }

    private static void runTestMethods(Class a) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        check(a);
        Testable t = (Testable) a.newInstance();
        Method[] methods = a.getDeclaredMethods();
        invokeBefore(methods, t);
        invokeTests(methods, t);
        invokeAfter(methods, t);
    }

    private static void invokeBefore(Method[] methods, Testable t) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) method.invoke(t);
        }
    }

    private static void invokeTests(Method[] methods, Testable t) throws InvocationTargetException, IllegalAccessException {
        for (int i = MIN_PRIORITY; i <= MAX_PRIORITY; i++) {
            for (Method method : methods) {
                if (method.getAnnotation(Test.class) != null && method.getAnnotation(Test.class).priority() == i)
                    method.invoke(t);
            }
        }
    }

    private static void invokeAfter(Method[] methods, Testable t) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            if (method.getAnnotation(AfterSuite.class) != null) method.invoke(t);
        }
    }

    private static void check(Class a) {
        Method[] methods = a.getDeclaredMethods();
        int before = 0;
        int after = 0;
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) before++;
            if (method.getAnnotation(AfterSuite.class) != null) after++;
        }
        if (before > 1 || after > 1) throw new RuntimeException("@AfterSuite и @BeforeSuite должны быть в единственном экземпляре!");
    }
}
