package com.alg.linkedList;

import com.alg.baseStruct.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 类似问题：148
 **/
public class SwapPairs {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode preNode = dummyHead;
        while (preNode.next != null && preNode.next.next != null) {
            ListNode node1 = preNode.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            preNode.next = node2;
            node2.next = node1;
            node1.next = next;

            preNode = node1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2= new ListNode(2);
        ListNode listNode3= new ListNode(3);
        ListNode listNode4= new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode curNode = swapPairs(listNode1);
        while (curNode != null) {
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}
