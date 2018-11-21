package com.hc.sys.core.common.test;

import javax.swing.*;
import java.util.*;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/19 16:34
 * @Version: 1.0.0.0
 */
public class TreeNodeTest {
    static Map map =new HashMap();
    static Map mapData =new HashMap();
    static  List list=new ArrayList();
    static  List  listData=new ArrayList();
    static  int n=0;
    public TreeNodeTest(Map map, List list) {
        this.map = map;
        this.list = list;
    }
    public static void testTreeNode(){
        for (int i = 1; i <10 ; i++) {
            map.put("name"+i,"tom"+i);
            map.put("mail"+i,"16689146@qq.com"+i);
        }

    }
    public static void main(String[] args) {
        testTreeNode();
        int [] array={1,5,3,6,8,-7,4,5,8,6,6,48};
        Arrays.sort(array);
    }
}
