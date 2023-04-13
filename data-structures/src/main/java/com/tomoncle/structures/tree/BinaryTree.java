package com.tomoncle.structures.tree;

import com.tomoncle.structures.tree.Base.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author tomoncle
 */
public class BinaryTree {

    private TreeNode buildBinaryTree(LinkedList<Integer> input) {
        TreeNode node = null;
        if (input == null || input.isEmpty()) {
            return null;
        }
        Integer data = input.removeFirst();
        // 如果data为空，则停止递归
        if (data != null) {
            node = new TreeNode(data);
            node.leftNode = buildBinaryTree(input);
            node.rightNode = buildBinaryTree(input);
        }
        return node;
    }

    /**
     * 前序遍历 ： 根 -  左 -> 右
     *
     * @return
     */
    public LinkedList<Integer> preOrder(TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (treeNode == null) {
            return list;
        }
        list.add(treeNode.data);
        list.addAll(preOrder(treeNode.leftNode));
        list.addAll(preOrder(treeNode.rightNode));
        return list;
    }

    /**
     * 使用栈实现先序遍历：
     * <p>
     * 1，2，3 压栈
     * 3，2 弹栈
     * 4 压栈
     * 4 弹栈
     * 1 弹栈
     * 5 压栈
     * 5 弹栈
     * 6 压栈
     * 6 弹栈
     *
     * @param treeNode 根节点
     * @return
     */
    public LinkedList<Integer> preOrderWithStack(TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (treeNode == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (treeNode != null || !stack.empty()) {
            // 只要左子树有值就压栈
            while (treeNode != null) {
                list.add(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftNode;
            }
            // 如果左子树值为空，则弹栈，并将当前节点置为弹出节点的右子树
            // 如果弹出节点的右子树为空，则继续弹栈，直至找到有右子树的节点
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                treeNode = pop.rightNode;
            }
        }
        return list;
    }


    /**
     * 中序遍历 ： 左 -> 根 -> 右
     *
     * @return
     */
    public LinkedList<Integer> inOrder(TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (treeNode == null) {
            return list;
        }
        list.addAll(inOrder(treeNode.leftNode));
        list.add(treeNode.data);
        list.addAll(inOrder(treeNode.rightNode));
        return list;
    }

    /**
     * 后序遍历 ： 左 -> 右 -> 根
     *
     * @return
     */
    public LinkedList<Integer> postOrder(TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (treeNode == null) {
            return list;
        }
        list.addAll(postOrder(treeNode.leftNode));
        list.addAll(postOrder(treeNode.rightNode));
        list.add(treeNode.data);
        return list;
    }


    /**
     * 层序遍历（广度优先遍历）
     * <p>
     * 根节点入队：1 入队
     * <p>
     * 根节点出队：1 出队
     * 根节点有左子树，左子树入队；2入队
     * 根节点有右子树，右子树入队。5入队
     * <p>
     * 左子树出队：2出队
     * 左子树有左子树，左子树入队；3入队
     * 左子树有右子树，右子树入队；4入队
     * <p>
     * 。。。。
     * <p>
     * 5出队，无左子树，有右子树；6入队
     * 3出队，无左右子树
     * 4出队，无左右子树
     * 6出队，无左右子树
     * <p>
     * 队列为空，结束
     * <p>
     * 1，2，5，3，4，6
     *
     * @param treeNode 根节点
     * @return LinkedList
     */
    public LinkedList<Integer> levelOrder(TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (treeNode == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 根节点入队
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            // 节点出队
            TreeNode node = queue.poll();
            // 拿到节点数据
            list.add(node.data);
            // 有左子树，左子树入队
            if (node.leftNode != null) {
                queue.offer(node.leftNode);
            }
            // 有右子树，右子树入队
            if (node.rightNode != null) {
                queue.offer(node.rightNode);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>(
                Arrays.asList(1, 2, 3, null, null, 4, null, null, 5, null, 6)
        );
        BinaryTree tree = new BinaryTree();
        TreeNode treeNode = tree.buildBinaryTree(list);

        System.out.println("前序遍历：" + tree.preOrder(treeNode));
        System.out.println("前序遍历：" + tree.preOrderWithStack(treeNode));
        System.out.println("中序遍历：" + tree.inOrder(treeNode));
        System.out.println("后序遍历：" + tree.postOrder(treeNode));
        System.out.println("层序遍历：" + tree.levelOrder(treeNode));
    }

}
