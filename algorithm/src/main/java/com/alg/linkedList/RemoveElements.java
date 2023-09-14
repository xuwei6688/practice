package com.alg.linkedList;

/**
 * @Author xuwei
 * @Date 2020/9/17
 * @Version V1.0
 **/
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode curNode = dummyHead;
        while (curNode.next != null) {
            if (curNode.next.val == val) {
                curNode.next = curNode.next.next;
            }else {
                curNode = curNode.next;
            }
        }
        return dummyHead.next;
    }


     public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
