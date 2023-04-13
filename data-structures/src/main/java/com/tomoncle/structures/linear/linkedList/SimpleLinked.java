package com.tomoncle.structures.linear.linkedList;

import com.tomoncle.structures.linear.linkedList.Base.Node;

/**
 * @author tomoncle
 * 单向链表: 单向链表的每一个节点包含两个部分：一部分存数据data, 一部分存指向下一个节点的指针next
 */
@SuppressWarnings("all")
public class SimpleLinked {
    // 头节点
    private Node start;
    // 尾节点
    private Node end;
    // 链表长度
    private int size = 0;


    private void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出下标大小");
        }
        Node node = new Node(data);
        if (size == 0) {// 假如链表为空
            // 插入的节点，既是头节点，也是尾节点
            start = node;
            end = node;
        } else if (index == 0) {// 假如插入位置为头节点
            // 插入节点的后继指针指向原来的头节点
            node.next = start;
            // 新插入的节点，设置为头节点
            start = node;
        } else if (index == size) { // 插入的位置为尾节点
            // 原来的尾节点指向新插入元素
            end.next = node;
            // 新插入的节点，设置为尾节点
            end = node;
        } else {
            // 获取前序节点
            Node prevNode = getPrevNode(index);
            // 将当前节点的后续节点指向前序节点的后续节点
            node.next = prevNode.next;
            // 将前序节点的后续节点指向当前节点
            prevNode.next = node;
        }
        size++;
    }

    private Node delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出下标大小");
        }
        Node node = null;
        if (index == 0) { // 删除头节点
            // 将当前头节点赋值给删除变量
            node = start;
            // 将头节点指向当前头节点的后续节点
            start = start.next;
        } else if (index == size - 1) {// 删除尾节点
            // 将当前尾节点赋值给删除变量
            node = end;
            // 尾节点指向当前尾节点的前序节点
            end = getPrevNode(index);
            // 尾节点的后续指针置为空
            end.next = null;
        } else {// 随机删除
            // 获取要删除位置的前序节点
            Node prevNode = getPrevNode(index);
            // 将要删除的节点赋值给删除变量
            node = prevNode.next;
            // 前序节点后续节点指向删除节点后续节点
            prevNode.next = node.next;
        }
        size--;
        return node;
    }

    /**
     * 获取index下标的前一个节点
     *
     * @param index
     * @return
     */
    private Node getPrevNode(int index) {
        Node node = start;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        return node;
    }

    private void output() {
        Node node = start;
        if (node == null) {
            System.out.println("空链表");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("节点下标【" + i + "】：" + node.data);
            node = node.next;
        }
        System.out.println("头节点：" + start.data);
        System.out.println("尾节点：" + end.data);
    }

    public static void main(String[] args) {
        SimpleLinked simpleLinked = new SimpleLinked();
        simpleLinked.output();
        System.out.println("***************");
        simpleLinked.insert(0, 0);

        simpleLinked.insert(1, 1);
        simpleLinked.output();
        System.out.println("***************");
        simpleLinked.insert(2, 2);
        simpleLinked.insert(3, 3);
        simpleLinked.insert(4, 4);
        simpleLinked.output();
        System.out.println("***************");
        simpleLinked.delete(4);
        simpleLinked.delete(0);
        simpleLinked.output();
    }

}
