package com.tomoncle.structures.linear.array;

/**
 * @author tomoncle
 */
/*
数组定义： 数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。
    1.第一是线性表（Linear List）。顾名思义，线性表就是数据排成像一条线一样的结构。
    每个线性表上的数据最多只有前和后两个方向。其实除了数组，链表、队列、栈等也是线性表结构。

    2.第二个是连续的内存空间和相同类型的数据。



一、在大部分编程语言中，数组都是从 0 开始编号的，但你是否下意识地想过，为什么数组要从 0 开始编号，而不是从 1 开始呢？

从数组存储的内存模型上来看，“下标”最确切的定义应该是“偏移（offset）”。
前面也讲到，如果用 a 来表示数组的首地址，a[0]就是偏移为 0 的位置，也就是首地址，a[k]就表示偏移 k 个 type_size 的位置，
所以计算 a[k]的内存地址只需要用这个公式：

    a[k]_address = base_address + k * type_size

但是，如果数组从 1 开始计数，那我们计算数组元素 a[k]的内存地址就会变为：

    a[k]_address = base_address + (k-1)*type_size

对比两个公式，我们不难发现，从 1 开始编号，每次随机访问数组元素都多了一次减法运算，对于 CPU 来说，就是多了一次减法指令。
数组作为非常基础的数据结构，通过下标随机访问数组元素又是其非常基础的编程操作，效率的优化就要尽可能做到极致。
所以为了减少一次减法操作，数组选择了从 0 开始编号，而不是从 1 开始。

 */
@SuppressWarnings("all")
public class SimpleArray {

    private int[] array;
    private int size;

    public SimpleArray(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 数组插入范围 0 ~ size, 不允许随机插入
     *
     * @param index
     * @param el
     */
    private void insert(int index, int el) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组下标范围：0~" + size);
        }
        if (size >= array.length) {
            resize();
        }
        // 从右向左循环，右移 index - > size 之间的元素
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = el;
        size++;
    }


    private int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("数组下标范围：0~" + size);
        }
        int del = array[index];
        // 从左往右循环，将右侧元素左移一位
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return del;

    }

    /**
     * 数组扩容到原来的二倍大小
     */
    private void resize() {
        int[] newArray = new int[array.length << 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    private void output() {
        printArray(this.array);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("下标【" + i + "】：" + array[i]);
        }
    }

    public static void main(String[] args) {
        SimpleArray simpleArray = new SimpleArray(5);
        simpleArray.insert(0, 5);
        simpleArray.insert(1, 11);
        simpleArray.insert(2, 12);
        simpleArray.insert(3, 13);
        simpleArray.insert(4, 14);
        simpleArray.insert(4, 144);
        simpleArray.insert(4, 14444);
        simpleArray.output();
        System.out.println("***************");
        System.out.println(simpleArray.delete(4));
        simpleArray.output();
    }

}
