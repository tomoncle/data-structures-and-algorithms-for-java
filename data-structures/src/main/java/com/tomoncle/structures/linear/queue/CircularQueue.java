package com.tomoncle.structures.linear.queue;

/**
 * @author tomoncle
 * 循环队列：
 * <p>
 * 为什么循环队列中的尾部必须指向一个空元素？众所周知，循环队列的容量为(N-1)，因为它必须留出一个空的空间来排队。判断队列是否已满。
 * <p>
 * 如果不想损失一个空间，可以使用标志位来标志队列是否已满，
 * 设置初始标记： boolean flag=false
 * <p>
 * 当入队列时，让rear往后移动，让flag=true
 * 当出队列时，让front往后移动，让flag=false
 * <p>
 * 当队列为空时： rear == front && flag==false
 * 当队列为满时： rear == front && flag == true
 * <p>
 * <p>
 * 计数count——队列中有效元素个数
 * <p>
 * 队列为空时，count == 0
 * 当有元素入队时，count++，当count和队列的maxsize相等时，代表队列已满
 */
@SuppressWarnings("all")
public class CircularQueue {
    // 队列, 队尾永远空出一位
    private int[] array;
    // 头指针
    private int start;
    // 尾指针
    private int end;

    public CircularQueue(int capacity) {
        array = new int[capacity + 1];
        start = 0;
        end = 0;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    private boolean isNull() {
        return start == end;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    private boolean isFull() {
        return (end + 1) % array.length == start;
    }

    private void output() {
        System.out.print("当前队列：");
        boolean isLeft = end < start;
        for (int i = start; i < (isLeft ? array.length : end); i++) {
            System.out.print(array[i] + " ");
            if (isLeft && i == array.length - 1) {
                i = -1;
                isLeft = false;
            }
        }
        System.out.println("            => 头指针：" + start + ", 尾指针：" + end);
    }

    /**
     * 入队
     *
     * @param value
     */
    private void add(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        array[end] = value;
        end = (end + 1) % array.length;
        output();
    }

    /**
     * 出队
     *
     * @return
     */
    private int pop() {
        if (isNull()) {
            throw new RuntimeException("队列已空");
        }
        int value = array[start];
        start = (start + 1) % array.length;
        output();
        return value;
    }


    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.add(11);
        circularQueue.add(12);
        circularQueue.add(13);
        circularQueue.add(14);
        circularQueue.add(15);

        circularQueue.pop();
        circularQueue.pop();
        circularQueue.pop();

        circularQueue.add(90);
        circularQueue.add(9);

    }
}
