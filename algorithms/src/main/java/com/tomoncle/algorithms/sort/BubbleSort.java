package com.tomoncle.algorithms.sort;

import java.util.Arrays;

/*
冒泡排序：

 * 算法步骤
    比较相邻的元素。如果第一个比第二个大，就交换他们两个。
    对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。

    针对所有的元素重复以上的步骤，除了最后一个。
    持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。


 * 时间复杂度：
    最好：O(n)
    最坏：O(n²)
    平均：O(n²)

 * 空间复杂度：O(1)

 * 算法稳定性：稳定

 */
public class BubbleSort {
    public static void main(String[] args) {
        // 定义一个无序数组
        int[] array = {3, 4, 5, 2, 1, 0, 9, 8, 7};

        // 外层循环 n-1 轮，
        // 为什么是i从1开始，因为循环一次，只能找出一个最大值，所以循环n-1次，最后一个不用比了就是最小的
        for (int i = 1; i < array.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            // 但这种改进对于提升性能来说并没有什么太大作用
            boolean flag = true;

            // 内层循环 n-i 轮，因为比较完一轮都已经把最大值放到数组末尾，所以不用再比了
            // 注意：j < array.length - i (i>=1) 因为 下面用到了 j+1 这个下标，不能下标越界
            for (int j = 0; j < array.length - i; j++) {
                // 从小到大
                if (array[j] > array[j + 1]) {
                    // 元素交换
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;

                    // 进行过交换就置为 false
                    flag = false;
                }
            }
            // 一轮过后没有进行过元素交换，则表示该数组是有序数组
            if (flag) {
                break;
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
