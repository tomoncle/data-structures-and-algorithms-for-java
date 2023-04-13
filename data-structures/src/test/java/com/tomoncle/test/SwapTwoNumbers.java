package com.tomoncle.test;

import org.junit.Test;

/**
 * 两数交换
 *
 * @author tomoncle
 */
public class SwapTwoNumbers {
    @Test
    public void testSwap1() {
        int a = 10;
        int b = 3;
        // 临时变量
        int temp = a;
        a = b;
        b = temp;

        System.out.println(String.format("a=%d, b=%d", a, b));
    }

    @Test
    public void testSwap2() {
        int a = 10;
        int b = 3;
        //相加寄存
        a = a + b; // a+b
        b = a - b; // a+b-b = a
        a = a - b; // a+b-a = b

        System.out.println(String.format("a=%d, b=%d", a, b));
    }


    @Test
    public void testSwap3() {
        int a = 10;
        int b = 3;

        //使用异或运算：
        // n^m^m = n ，举例 6^5^5=6
        // n^m^n = m , 举例 6^9^6=9
        // n^n = 0   , 举例 8^8=0
        a = a ^ b; // a^b
        b = a ^ b; // a^b^b = a
        a = a ^ b; // a^b^a = b

        System.out.println(String.format("a=%d, b=%d", a, b));
    }

    @Test
    public void testBit(){
        System.out.println(6^9^6);
        System.out.println(8^8);
    }
}
