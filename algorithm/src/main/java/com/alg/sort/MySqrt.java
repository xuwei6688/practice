package com.alg.sort;

public class MySqrt {
//    public float mySqrt(float x) {
//        float l =0,r=x;
//        while (l < r && (r - l) >= 1e-6) {
//            float mid = (l + r) / 2;
//            float y = mid * mid;
//            if (y > x) {
//                r = mid;
//            } else if (y < x) {
//                l = mid;
//            }else {
//                return mid;
//            }
//        }
//        return l;
//    }


    public int mySqrt(int x) {
        //处理两个特殊情况
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 1;
        int right = x;
        //二分法找，每次计算出中间的那个数mid，
        // - 如果mid*mid=x说明mid就是我们要找的那个数
        // - 如果mid*mid<x说明要找的数在mid右边，缩小查找范围，从[mid+1,right]区间查找
        // - 如果mid*mid>x说明要找的数在mid左边，缩小查找范围，从[left,mid-1]区间查找
        // 随着左右两端不断向我们要找的数逼近，最后可能就找到的我们要的那个数，直接从循环中return
        // 因为我们要取整数部分，即比实际的数字小，所以取right
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid > x / mid) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        float result = mySqrt.mySqrt(8);
        System.out.println(result);
    }
}
