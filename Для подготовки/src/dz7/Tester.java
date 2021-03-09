package dz7;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tester {
    private static Method beforeMethod = null;
    private static List<Method> testMethodsOrdered = new LinkedList<>();
    private static Method afterMethod = null;


    /*
     * Two calls are possible
     * */

    public static void main(String[] args) {
        start(Testable.class);
        clearFields();
        start(Testable.class.getName());
    }

    private static void start(Class testedClass) {
        try {
            sortMethods(testedClass);
        } catch (RuntimeException re) {
            System.out.println("Many before or after methods encountered!");
            return;
        }
        invokeMethods();
    }

    private static void start(String className) {
        Class testedClass = null;
        try {
            testedClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        start(testedClass);
    }

    private static void sortMethods(@NotNull Class testedClass) {
        Method[] methods = testedClass.getDeclaredMethods();
        List<Method> beforeSuiteMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterSuiteMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) beforeSuiteMethods.add(method);
            if (method.getAnnotation(Test.class) != null) testMethods.add(method);
            if (method.getAnnotation(AfterSuite.class) != null) afterSuiteMethods.add(method);
        }
        extracted(beforeSuiteMethods, afterSuiteMethods);
        beforeMethod = beforeSuiteMethods.get(0);
        beforeMethod.setAccessible(true);
        afterMethod = afterSuiteMethods.get(0);
        afterMethod.setAccessible(true);
        int priority = Priority.MAXIMUM;
        do {
            for (Method method : testMethods) {
                if (method.getAnnotation(Test.class).priority() == priority) {
                    method.setAccessible(true);
                    testMethodsOrdered.add(method);
                }
            }
            priority--;
        } while (priority >= Priority.MINIMUM);
    }

    private static void extracted(List<Method> beforeSuiteMethods, List<Method> afterSuiteMethods) {
        if (beforeSuiteMethods.size() != 1 || afterSuiteMethods.size() != 1)
            throw new RuntimeException();
    }

    private static void invokeMethods() {
        String str1 = "hello", str2 = "world";//Init smth to pass into the methods
        byte byteNumber = 26;
        int intNumber = 1024;
        float floatNumber = 26.26f;
        double doubleNumber = 266144.524288;
        boolean bool = true;
        try {
            Testable testable = (Testable) beforeMethod.invoke(null);
            testMethodsOrdered.forEach((method) -> {
                Class[] type = method.getParameterTypes();
                System.out.printf("Priority: %d. Action: ", method.getAnnotation(Test.class).priority());
                try {
                    Class currentType = type[0];
                    if (type.length == 2) method.invoke(testable, str1, str2);
                    else if (currentType == byte.class) method.invoke(testable, byteNumber);
                    else if (currentType == int.class) method.invoke(testable, intNumber);
                    else if (currentType == float.class) method.invoke(testable, floatNumber);
                    else if (currentType == double.class) method.invoke(testable, doubleNumber);
                    else if (currentType == boolean.class) method.invoke(testable, bool);
                        //boolean.class does not work!
                    else if (currentType == String.class) method.invoke(testable, str1);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            afterMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void clearFields() {
        beforeMethod = null;
        testMethodsOrdered = new LinkedList<>();
        afterMethod = null;
    }
}
