package com.alg;

import com.alg.baseStruct.ListNode;

import java.util.*;


public class Test {

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
//        int[] nums = new int[10000];
//        Random random = new Random();
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = random.nextInt(100000);
//        }
//
//        nums = new Test().sortArray(nums);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] < nums[i - 1]) {
//                throw new IllegalStateException();
//            }
//        }
//1,4,3,2,5,2
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(4);
//        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;

//        ListNode node = new Test().partition(node1, 3);
//        System.out.println(node);

        System.out.println(14&1);
    }

    public ListNode oddEvenList(ListNode node) {
        ListNode left = new ListNode(0);
        ListNode leftTail = left;

        ListNode right = new ListNode(0);
        ListNode rightTail = right;

        int index = 1;
        while (node != null) {
            if ((index & 1) == 1) {
                leftTail.next = node;
                leftTail = leftTail.next;
            }else {
                rightTail.next = right;
                rightTail = rightTail.next;
            }
            node = node.next;
            index++;
        }

        rightTail.next = null;
        leftTail.next = right;
        return left.next;
    }
}
