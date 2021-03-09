package lesson_6;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class MainTest {

    private static Main main;

    private int[] sourceArray;
    private int[] resultArray;
    private int[] sourceArray1;
    private boolean bool;

    public MainTest(int[] sourceArray, int[] resultArray, int[] sourceArray1, boolean bool) {
        this.sourceArray = sourceArray;
        this.resultArray = resultArray;
        this.sourceArray1 = sourceArray1;
        this.bool = bool;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {new int[]{7,4,2,6}, new int[]{2,6}, new int[]{1,1,1,1}, false},
                {new int[]{7,4,2,4}, new int[]{}, new int[]{4,4,4,4}, false},
                {new int[]{7,4,4,1}, new int[]{1}, new int[]{4,1,4,1}, true},
                {new int[]{4,3,2,6}, new int[]{3,2,6}, new int[]{1,4,4,4}, true}
        });
    }

    @BeforeClass
    public static void initTest() {
        main = new Main();
        System.out.println("init suite");
    }

    @AfterClass
    public static void destroy() {
        main = null;
    }

    @Test
    public void testArrayProcessor1() {
        Assert.assertArrayEquals(resultArray, main.arrayProcessor1(sourceArray));
    }

    @Test
    public void testArrayProcessor2() {
        Assert.assertEquals(bool, main.arrayProcessor2(sourceArray1));
    }
}
