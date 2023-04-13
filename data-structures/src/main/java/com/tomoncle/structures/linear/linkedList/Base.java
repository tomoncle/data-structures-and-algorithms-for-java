package com.tomoncle.structures.linear.linkedList;

import java.util.HashMap;

/**
 * @author tomoncle
 */
/*

* 链表定义： 一种在物理上非连续、非顺序的数据结构，由若干节点（Node) 组成
              链表通过指针将一组零散的内存块串联在一起。其中，我们把内存块称为链表的“结点”。

* 缓存的大小有限，当缓存被用满时，哪些数据应该被清理出去，哪些数据应该被保留？这就需要缓存淘汰策略来决定。常见的策略有三种：
     * 1.先进先出策略 FIFO（First In，First Out）、
     * 2.最少使用策略 LFU（Least Frequently Used）、
     * 3.最近最少使用策略 LRU（Least Recently Used）。

 */

public class Base {
    /**
     * 单向链表: 单向链表的每一个节点包含两个部分：一部分存数据data, 一部分存指向下一个节点的指针next
     */
    static class Node {
        // 存储数据
        int data;
        // 后继指针
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "data: " + data;
        }
    }

    /**
     * 链表
     */
    static class NodeList {
        Node head;
        int size;

        NodeList(int... array) {
            this.buildNodeList(array);
        }

        /**
         * 构建链表
         *
         * @param array 数组
         */
        private void buildNodeList(int... array) {
            if (array.length < 1) {
                return;
            }
            // 设置头节点
            head = new Node(array[0]);
            // 元素循环插入链表尾部
            Node node = head;
            for (int i = 1; i < array.length; i++) {
                Node nextNode = new Node(array[i]);
                node.next = nextNode;
                node = nextNode;
            }
            size = array.length;
        }

        /**
         * 打印链表:
         * 字背景颜色：40黑 41红 42绿 43黄 44蓝 45紫 46深绿 47白
         */
        void output() {
            if (size == 0) {
                System.out.println("链表长度：" + size + ", 头节点：" + head + "");
                return;
            }

            Node node = head;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            boolean run = true;
            while (node != null && run) {
                // 判断是否有环，否则死循环
                if (map.containsKey(node.data)) {
                    // 下次循环跳出，本此不跳出，以便显示环的位置
                    run = false;
                    i = map.get(node.data);
                }
                map.put(node.data, i);
                // 打印
                sb.append("[");
                sb.append(String.format("\033[%dm 下标：%d \033[0m", 41, i));
                sb.append(",");
                sb.append(String.format("\033[%dm 值：%d \033[0m", 42, node.data));
                sb.append("] -> ");
                node = node.next;
                i++;
            }
            sb.append("NULL");
            System.out.println(sb.toString());
            System.out.println("链表长度：" + size + ", 头节点：" + head.data + "");
        }

        /**
         * 获取index下标的前一个节点
         */
        Node getPrevNode(int index) {
            Node node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            return node;
        }
    }
}

