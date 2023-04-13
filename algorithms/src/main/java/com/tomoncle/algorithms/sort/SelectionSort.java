package com.tomoncle.algorithms.sort;

import java.util.Arrays;

/*
@author tomoncle

选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。
所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。

1. 算法步骤

    首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。

    再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。

    重复第二步，直到所有元素均排序完毕。

 * 时间复杂度：
    最好：O(n²)
    最坏：O(n²)
    平均：O(n²)

 * 空间复杂度：O(1)

 * 算法稳定性：不稳定
 */
public class SelectionSort {

    public static void main(String[] args) {
        // 定义一个无序数组
        int[] array = {3, 4, 5, 2, 1, 0, 9, 8, 7};
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < array.length - 1; i++) {
            // 最小值下标
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }

        System.out.println(Arrays.toString(array));
    }

}
