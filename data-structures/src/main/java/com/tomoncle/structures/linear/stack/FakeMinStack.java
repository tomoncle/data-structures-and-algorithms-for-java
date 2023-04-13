package com.tomoncle.structures.linear.stack;

import java.util.Stack;

/**
 * 实现一个栈，该栈有出栈、入栈、取最小值3个方法，3个方法的时间复杂度都为 O(1)
 *
 * @author tomoncle
 */
public class FakeMinStack {
    // 主栈: 存储元素
    private Stack<Integer> mainStack = new Stack<Integer>();
    // 辅助栈：存储最小元素
    private Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 元素入栈
     * @param value x
     */
    private void push(Integer value) {
        // 元素入栈
        mainStack.push(value);
        // 假如辅助栈为空，或者 辅助栈栈顶元素小于插入元素，则插入辅助栈
        if (minStack.isEmpty() || value < minStack.peek()) {
            minStack.push(value);
        }
    }

    /**
     * 元素出栈
     * @return X
     */
    private Integer pop() {
        // 元素出栈
        Integer value = mainStack.pop();
        // 假如出栈元素和辅助栈栈顶元素相同，则辅助栈也出栈
        if (value.equals(minStack.peek())) {
            minStack.pop();
        }
        return value;
    }

    /**
     * 获取栈中最新元素
     * @return X
     */
    private Integer getMin() {
        if (mainStack.isEmpty()) {
            throw new RuntimeException("空栈");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        FakeMinStack fakeMinStack = new FakeMinStack();
        fakeMinStack.push(8);
        fakeMinStack.push(9);
        fakeMinStack.push(7);
        fakeMinStack.push(3);
        fakeMinStack.push(5);
        fakeMinStack.push(4);

        System.out.println("出栈：" + fakeMinStack.pop());
        System.out.println("最小值：" + fakeMinStack.getMin());
        System.out.println("出栈：" + fakeMinStack.pop());
        System.out.println("最小值：" + fakeMinStack.getMin());
        System.out.println("出栈：" + fakeMinStack.pop());
        System.out.println("最小值：" + fakeMinStack.getMin());
        System.out.println("出栈：" + fakeMinStack.pop());
        System.out.println("最小值：" + fakeMinStack.getMin());
        fakeMinStack.push(3);
        System.out.println("最小值：" + fakeMinStack.getMin());
        fakeMinStack.push(1);
        System.out.println("最小值：" + fakeMinStack.getMin());
    }
}
