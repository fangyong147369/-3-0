package com.hc.sys.core.common.test;

import java.util.*;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/13 19:05
 * @Version: 1.0.0.0
 */
public class DepthFirst {
    public static void main(String [] args) {
        treeNode(3,125,1000);
    }
    public static void treeNode(int layer,int num,int total){
        List<Map> list=new ArrayList<Map>();
        Map map=new HashMap();
        if ((total/8)>=num)
        map.put("0",total/8);
        if ((total-total/8)>=num)
        map.put("11",total/8);
        map.put("12",total/8);
        map.put("13",total/8);
        map.put("14",total/8);
        map.put("15",total/8);
        map.put("16",total/8);
        map.put("17",total/8);
        map.put("18",total/8);
        map.put("121",(total/8)/8);
        map.put("122",(total/8)/8);
        map.put("123",(total/8)/8);
        map.put("124",(total/8)/8);
        map.put("125",(total/8)/8);
        map.put("126",(total/8)/8);
        map.put("126",(total/8)/8);
        map.put("128",(total/8)/8);
        list.add(map);
        list.get(0).forEach((K,V)-> System.out.println("K:"+K+" V:"+V));
        System.out.println(list.get(0).get("11"));
    }
}
