package com.xu;

import com.xu.segmentTree.SegmentTree;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author xuwei
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class SegmentTreeTest {
    @Test
    public void test1() {
        Integer [] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (e1, e2)->e1 + e2);

        assertEquals("[-3,1,-4,-2,3,-3,-1,-2,0,null,null,-5,2,null,null,null,null,null,null,null,null,null,null,null]", segmentTree.toString());

        assertEquals(segmentTree.query(0, 2), new Integer(1));
        assertEquals(segmentTree.query(2, 5), new Integer(-1));
        assertEquals(segmentTree.query(0, 5), new Integer(-3));
    }
}
