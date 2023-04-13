package com.tomoncle.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tomoncle
 */
public class SourceRead {
    @Test
    public void arrayList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        //
        List<Integer> list1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println(list1);
        //
        List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(list2);
    }
}
