package com.xu.segmentTree;

/**
 * @Author xuwei
 * @Date 2020/9/6
 * @Version V1.0
 **/
class NumArray {

    class  SegmentTree{
        int [] tree;
        int [] data;

        public SegmentTree(int[] data) {
            this.data = data;
            tree =  new int[4 * data.length];
            if (data.length == 0) {
                return;
            }
            buildSegmentTree(0, 0, data.length -1);
        }

        private int left(int index) {
            return 2 * index + 1;
        }

        private int right(int index) {
            return 2 * index + 2;
        }

        private void buildSegmentTree(int index, int l, int r) {
            if (l == r) {
                tree[index] = data[l];
                return;
            }

            int mid = l + (r - l) / 2;
            int leftIndex = left(index);
            int rightIndex = right(index);

            buildSegmentTree(leftIndex, l, mid);
            buildSegmentTree(rightIndex, mid + 1, r);

            tree[index] = tree[leftIndex] + tree[rightIndex];
        }

        public int query(int queryL, int queryR) {
            if (queryL < 0 || queryR >= data.length || queryL > queryR) {
                throw new IllegalArgumentException("index is illegal");
            }
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        private int query(int index, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[index];
            }

            int mid = l + (r - l) / 2;
            int leftIndex = left(index);
            int rightIndex = right(index);

            if (queryL >= mid + 1) {
                return query(rightIndex, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(leftIndex, l, mid, queryL, queryR);
            }

            int left = query(leftIndex, l, mid, queryL, mid);
            int right = query(rightIndex, mid + 1, r, mid + 1, queryR);
            return left + right;
        }

        public void update(int index, int val) {
            update(0, 0, data.length - 1, index, val);
        }

        private void update(int treeIndex, int l, int r, int index, int val) {
            if (l == r) {
                tree[treeIndex] = val;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftIndex = left(treeIndex);
            int rightIndex = right(treeIndex);

            if (index >= mid + 1) {
                update(rightIndex, mid + 1, r, index, val);
            }else{
                update(leftIndex, l, mid, index, val);
            }
            tree[treeIndex] = tree[leftIndex] + tree[rightIndex];
        }
    }

    SegmentTree segmentTree;
    public NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        segmentTree.update(i, val);
    }

    public int sumRange(int i, int j) {
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};

        NumArray numArray = new NumArray(nums);
        int i = numArray.sumRange(0, 2);
        System.out.println(i);
        numArray.update(1, 2);
        int i1 = numArray.sumRange(0, 2);
        System.out.println(i1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */