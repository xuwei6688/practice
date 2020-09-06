package com.xu.segmentTree;

/**
 * 线段树
 **/
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.merger = merger;

        this.data = (E[])new Object[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);

        tree = (E[]) new Object[4 * data.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    //在index位置，创建表示[l,r]区间的线段树
    private void buildSegmentTree(int index, int l, int r) {
        //递归终结条件，l==r说明是叶子节点，存放为融合的元素
        if (l == r) {
            tree[index] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid + 1, r);

        tree[index] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public void update(int index, E e) {
        update(0, 0, data.length - 1, index, e);
    }

    private void update(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        if (index >= mid + 1) {
          update(rightIndex, mid + 1, r, index, e);
        }else {
            update(leftIndex, l, mid, index, e);
        }

        tree[index] = merger.merge(tree[leftIndex],  tree[rightIndex]);
    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    //查询[queryL,queryR]区间的值]
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryR < 0 || queryL >= data.length || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("index is Illegal");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //在以treeIndex为根（区间为[l,r]）的线段树种，查询[queryL,queryR]范围的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //当前节点刚好是代查询区间
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        //如果查询的区间只位于左孩子或右孩子，直接到响应的节点查询
        if (queryL >= mid + 1) {
            return query(rightIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftIndex, l, mid, queryL, queryR);
        }

        //查询的区间横跨左右孩子，分别到左右孩子查询，然后合并结果
        E e1 = query(leftIndex, l, mid, queryL, mid);
        E e2 = query(rightIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(e1, e2);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <tree.length; i++) {
            sb.append(tree[i]);
            if (i != tree.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
