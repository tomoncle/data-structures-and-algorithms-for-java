package com.tomoncle.algorithms.sort;

import java.util.Arrays;

/*
@author tomoncle

插入排序： 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，
对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。

插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。

1. 算法步骤
    将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。

    从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
    如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。


 */
public class InsertSort {

    static void sort(){
        // 定义一个无序数组
        int[] array = {3, 4, 5, 2, 1, 0, 9, 8, 7};

        for (int i = 1; i < array.length; i++) {
            // 记录要插入的数据
            int tmp = array[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < array[j - 1]) {
                // 比插入元素大的数，都依次后移
                // 比如2插入到1，3中:
                // 进入while循环：
                // ·第一次比较：i=2,j=2, tmp=2, array[2] = array[1] = 3; j=1 此时数组【1，3，2】变为【1，3，3】
                // ·第二次比较：i=2,j=1, tmp=2, array[0] = 1; j=1 此时 tmp > array[0];跳出while循环, 此时数组保持【1，3，3】
                array[j] = array[j - 1];
                // 下标往前移动
                j--;
            }

            // 存在比其小的数，插入
            // 比如2插入到1，3中: 跳出上面 while 循环，array[1]=2, 此时数组【1，3，3】变为【1，2，3】
            if (j != i) {
                array[j] = tmp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    static void sort2(){
        // 定义一个无序数组
        int[] array = {3, 4, 5, 2, 1, 0, 9, 8, 7};

        for (int i = 1; i < array.length; i++) {
            // 默认已经排好序的位置下标
            int prevIndex = i-1;
            // 记录要插入的数据
            int current = array[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            while (prevIndex >= 0 && array[prevIndex] > current) {
                // 比插入元素大的数，都依次后移
                // 比如2插入到1，3中:
                // 进入while循环：
                // ·第一次比较：i=2,j=2, tmp=2, array[2] = array[1] = 3; j=1 此时数组【1，3，2】变为【1，3，3】
                // ·第二次比较：i=2,j=1, tmp=2, array[0] = 1; j=1 此时 tmp > array[0];跳出while循环, 此时数组保持【1，3，3】
                array[prevIndex+1] = array[prevIndex];
                // 下标往前移动
                prevIndex--;
            }
            array[prevIndex+1] = current;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        sort();
        sort2();
    }
}
