package com.tomoncle.structures.tree;

import java.util.Arrays;

/**
 * @author tomoncle
 */
/*
二叉堆: 二叉堆本质是一种完全二叉树，又分为：最大堆，最小堆；二叉堆的根节点叫做堆顶
 * 最大堆：任何一个父节点的值都 “大于等于”他左、右子节点的值
 * 最小堆：任何一个父节点的值都 “小于等于” 他左、右字节的的值

二叉堆虽然是完全二叉树，但是它的存储方式并不是链式存储，而是顺序存储，即二叉堆的所有数据存储在数组中
    存储公式：假设父节点p下标为n 则：p->n ;
        p.leftNode  -> 2n+1
        p.rightNode -> 2n+2

二叉堆的自我调整：以最小堆为例

插入：二叉堆插入的新节点，在二叉堆的最后一个位置
    1. 比如新节点插入的值为0
    2. 新节点的父节点5比0大，则新节点上浮，和父节点交换位置
    3. 继续用新节点0和父节点3比较，因为0小于3，则新节点0继续上浮
    4. 继续比较，最终新节点0上浮到堆顶

删除：二叉堆的删除节点过程和插入节点相反，所删除的是处于堆顶的节点
    1. 例如删除最小堆的堆顶节点1
    2. 这时候为了继续维护二叉树的结构，我们把堆的最后一个节点10"临时"补到原本堆顶的位置
    3. 接下来让处于堆顶的10和他的左右子节点比较大小，
       如果左（例如3）、右（例如2）子节点的值都比10小，则找一个最小的（右节点2），
       那么10要下沉，10和2交换位置
    4. 继续让10和他的左、右子节点比较，左右子节点最小的是7，则10继续下沉，和7交换位置
    5. 最终1删除，2到堆顶, 原来节点10的位置不变，只是临时参与


构建：从最后一个非叶子节点开始，依次做"下沉"调整


 */
public class BinaryHeap {

    private int[] array;

    /**
     * 最小堆：上浮最后一个元素（堆底元素上浮）
     * <p>
     * 时间复杂度：O(logN)
     *
     * @param array 待调整的堆
     */
    static void upLatestChild(int[] array) {
        // 获取子节点下标
        int childIndex = array.length - 1;
        // 获取父节点下标
        int parentIndex = (childIndex - 1) / 2;
        // 保存 插入的叶子节点的值，用于最后赋值
        int children = array[childIndex];
        // 叶子节点下标大于0，由于是最小堆，子节点数据小于父节点数据 ，就执行上浮
        while (childIndex > 0 && children < array[parentIndex]) {
            // 将父节点的值，下沉到子节点.
            array[childIndex] = array[parentIndex];
            // 下标往上移动，子节点下标变为父节点下标
            childIndex = parentIndex;
            // 父节点下标，变为父节点的父节点下标
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = children;
    }

    /**
     * 下沉指定的父节点元素
     * <p>
     * 时间复杂度：O(logN)
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点下标
     * @param length      堆的有效大小
     */
    static void downParent(int[] array, int parentIndex, int length) {
        // temp 保存父节点的值，用于最后赋值
        int parent = array[parentIndex];
        // 获取子节点下标，准确的说是左子节点的下标
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            // 如果有右节点，且右节点小于左节点的值，则定位到右节点
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < length && array[rightChildIndex] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于最小的一个子节点，则直接跳出循环
            if (parent <= array[childIndex]) {
                break;
            }
            // 因为父节点大于子节点，最小堆原则：执行交换
            array[parentIndex] = array[childIndex];
            // 下标往下移动，子节点下标变为父节点下标
            parentIndex = childIndex;
            // 子节点下标，变为原来子节点的子节点下标
            childIndex = 2 * childIndex + 1;

        }
        array[parentIndex] = parent;
    }


    /**
     * 构建最小堆
     * <p>
     * 时间复杂度：O(N)
     *
     * @param array 待调整的堆
     */
    static void buildMinBinaryHeap(int... array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downParent(array, i, array.length);
        }
    }


    public static void main(String[] args) {
        // 定义一个无序二叉堆，实质是数组
        int[] heap = {15, 3, 2, 6, 5, 7, 8, 9, 10, 0};
//        upLatestChild(heap);
        System.out.println(Arrays.toString(heap));
        buildMinBinaryHeap(heap);
        System.out.println(Arrays.toString(heap));
    }

}
