package com.xuetao.java.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * **************************************************************
 *
 * @描述 :  写时复制
 * @作者 :	薛涛
 * @版本 :	v1.0
 * @日期 :	$
 * @修改日志 :
 * @修改人 :
 * @修订后版本 :	v2.0
 * @修改时间 : ***************************************************************
 */
public class NotSafeDemo {


    public static void main(String[] args) {

        //Map<String,String> map = new HashMap<>();多线程读写会报java.util.ConcurrentModificationException（并发修改异常）
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            final  Integer itm = i;
        new Thread(()->{
            map.put(Integer.toString(itm),UUID.randomUUID().toString().substring(0,2));
            System.out.println(map);
        },"").start();
        }
    }
}
