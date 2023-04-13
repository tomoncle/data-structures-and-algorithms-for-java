package com.tomoncle.structures.linear.linkedList;

import com.tomoncle.structures.linear.linkedList.Base.Node;
import com.tomoncle.structures.linear.linkedList.Base.NodeList;

/**
 * 算法，示例
 *
 * @author tomoncle
 */
public class SolutionSamples {

    /**
     * 链表反转
     * <p>
     * 从头节点开始，依次将当前节点的next指针指向前序节点，来实现链表反转
     * <p>
     * NodeList nodeList = new NodeList(1, 2, 3, 4, 5, 6);
     * nodeList.output();
     * SolutionSamples.reversal(nodeList);
     * nodeList.output();
     */
    static void reversal(NodeList nodeList) {
        // 当前节点
        Node node = nodeList.head;
        // 前序节点，因为头节点要变尾节点，尾节点要指向空，所以初始为空
        Node prevNode = null;
        // 后续节点
        Node nextNode;

        while (node != null) {// 循环链表，依次向前移动
            // 获取后续节点
            nextNode = node.next;
            // 将当前节点的后续指针指向前序节点
            node.next = prevNode;
            // 将当前节点移动到前序节点
            prevNode = node;
            // 将后续节点移动到当前节点
            node = nextNode;
        }
        if (prevNode != null) {
            // 遍历完毕，当前节点为空，其前序节点，应该为原来的尾节点，变为头节点
            nodeList.head = prevNode;
        }

    }

    /**
     * 构建一个有环的链表
     *
     * @return
     */
    static NodeList buildCycleNodeLIst() {
        // 初始化链表：1->2->3->4->5-
        NodeList nodeList = new NodeList(1, 2, 3, 4, 5);
        // 在链表上构建一个环 1->2->3->4->5->6->7->4
        Node head = nodeList.head;
        Node node4 = head.next.next.next;
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node4.next.next = node6;
        node6.next = node7;
        node7.next = node4;
        nodeList.head = head;
        nodeList.size = 7;
        nodeList.output();
        return nodeList;
    }

    /**
     * 判断链表是否有环
     * <p>
     * 使用两个指针：
     * 第一个指针p1: 每次移动1个节点，
     * 第二个指针p2: 每次移动2个节点，
     * <p>
     * 开始循环链表：
     * 然后比较两个指针指向的节点是否相同，如果相同则表示有环。
     * <p>
     * <p>
     * 其他方法： 使用 hashSet 循环存储，如果发现下次遍历时 hashSet中存在，则表示有环
     *
     * @param nodeList 链表
     */
    static void isCycle(NodeList nodeList) {
        Node p1 = nodeList.head;
        Node p2 = nodeList.head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2) {
                System.out.println("链表有环");
                return;
            }
        }
        System.out.println("链表无环");
    }

    /**
     * 判断环的长度
     * <p>
     * 从第一次相遇点，开始，第二次相遇时，p2比p1正好多走一圈，走的步数就是环长度
     * 环长 = 速度差 * 前进次数 = 前进次数
     *
     * @param nodeList
     */
    static void isCycleLength(NodeList nodeList) {
        Node p1 = nodeList.head;
        Node p2 = nodeList.head;
        boolean meet = false;
        int i = 0;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            i++;
            if (p1 == p2) {
                // 第第二次相遇
                if (meet) {
                    System.out.println("环长度：" + i);
                    return;
                }
                // 第一次相遇
                i = 0;
                meet = true;

            }
        }
        System.out.println("链表无环");
    }


    /**
     * 求入环点位置
     * <p>
     * 假设从链表头节点到入环点的位置距离是D，
     * 从入环点到两指针首次相遇的位置距离是S1，
     * 从两指针首次相遇的位置到入环点距离是S2,
     * <p>
     * 那p1指针走的距离： D + S1
     * 那p2指针走的距离比P1多走了n圈(n>=1)： D + S1 + n(S1+S2), 因为p2的速度是P1的二倍，所以也可以等于：2(D+S1)
     * 所以  2(D+S1) = D + S1 + n(S1+S2) ---> D+S1 = n(S1+S2)  ---> D = (n-1)S1 + n * S2 --> D = (n-1)(S1+S2) + S2
     * 即表示: 链表头节点到入环点的距离 等于 首次相遇点绕环 (n-1) 圈再回到入环点的距离 --> D = S2
     * <p>
     * 那如果这样的话，那将其中一个指针放回头节点，另外一个指针在首次相遇位置，两指针每次都移动 1 步。
     * 再次相遇的距离，就是 D
     *
     * @param nodeList
     */
    static void isCyclePosition(NodeList nodeList) {
        Node p1 = nodeList.head;
        Node p2 = nodeList.head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            // 第一次相遇
            if (p1 == p2) {
                break;
            }
        }
        // 那将其中一个指针放回头节点，另外一个指针在首次相遇位置，两指针每次都移动 1 步
        // 进行二次相遇，再次相遇的位置就是入环位置
        p1 = nodeList.head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
            // 再次相遇
            if (p1 == p2) {
                System.out.println("入环点位置：" + p1.data);
                return;
            }
        }
        System.out.println("链表无环");
    }


    public static void main(String[] args) {

        NodeList x = new NodeList(1, 2, 3, 4, 5);
        x.output();
        isCycle(x);
        System.out.println();

        NodeList y = buildCycleNodeLIst();
        isCycle(y);
        isCycleLength(y);
        isCyclePosition(y);
    }

}
