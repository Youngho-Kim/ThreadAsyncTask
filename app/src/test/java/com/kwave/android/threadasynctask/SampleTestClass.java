package com.kwave.android.threadasynctask;

import com.kwave.android.threadasynctask.TDD.Calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by kwave on 2017-06-14.
 */




@RunWith(Parameterized.class)
public class SampleTestClass {

    int x;
    int y;
    int z;



//    int a = 0;
//    final int b;
//    static int c = 0;
//    {
////        b = 10;
//        System.out.printf("init code block %n");
//    }
//
//    static {
//        System.out.printf("static init code block %n");
//    }
//
//    public SampleTestClass() {
//        System.out.printf("initial Test class %n");
//    }
//
//    @BeforeClass
//    public static void beforeClass(){
//        System.out.printf("Create Test classes %n");
//    }
//
//    @Before
//    public void before(){
//        System.out.printf("start Test %n");
//    }


    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Integer[][]{
                {1,2,3}, {2,3,5}, {10,12,19}, {10,20,30}
        });
    }

    public SampleTestClass(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Test
    public void test1(){
//        System.out.printf("Do Test%d %n",1);
//        System.out.printf("sum %d and %d expect %d %n",4,1,5);
        System.out.printf("sum %d and %d expect %d %n",x,y,z);

//        assertEquals(5, Calculator.sum(4,1));
        assertEquals(z, Calculator.sum(x,y));

    }
//    @Test(expected = AssertionError.class)
    @Test
    public void test2(){
//        System.out.printf("Do Test%d %n",2);
//        System.out.printf("sum %d and %d expect not %d %n",4,2,5);
//        String reason = String.format(Locale.getDefault(),"sum %d and %d expect not %d %n",4,2,5);
        String reason = String.format(Locale.getDefault(),"sum %d and %d expect not %d %n",x,y,z);
//        assertEquals(reason,5, Calculator.sum(4,2));
        assertEquals(reason,z, Calculator.sum(x,y));
    }

    @Test
    public void test3(){
//        assertThat(Calculator.sum(4,2),is(6));
//        assertThat(Calculator.sum(4,2),not(is(6)));
        assertThat(Calculator.sum(x,y),not(is(z)));
        assertThat("HelloWorld",startsWith("Hello"));
        assertThat("HelloWorld",both(startsWith("Hello")).and(endsWith("world")));

    }



//    @After
//    public void after() {
//        System.out.printf("end Test %n");
//    }
//    @AfterClass
//    public static void AfterClass(){
//        System.out.printf("GC Test class %n");
//    }
}
