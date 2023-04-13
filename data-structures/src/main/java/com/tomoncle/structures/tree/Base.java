package com.tomoncle.structures.tree;

/**
 * @author tomoncle
 */
public class Base {
    static class TreeNode {
        // 左子树
        TreeNode leftNode;
        // 右子树
        TreeNode rightNode;
        // 数据
        Integer data;

        TreeNode(Integer data) {
            this.data = data;
        }
    }
}
