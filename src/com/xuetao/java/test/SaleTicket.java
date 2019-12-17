package com.xuetao.java.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * **************************************************************
 *
 * @描述 :
 * @作者 :	薛涛
 * @版本 :	v1.0
 * @日期 :	$
 * @修改日志 :
 * @修改人 :
 * @修订后版本 :	v2.0
 * @修改时间 : ***************************************************************
 */
public class SaleTicket {

    private Integer  nub  = 30;

    private Lock  lock = new ReentrantLock();

    public  void jianPiao() {

               lock.lock();
               try {
                   if(nub>0){
                       System.out.println(Thread.currentThread().getName()+"---卖出："+nub);
                       nub = nub-1;
                   }else {
                       System.out.println(Thread.currentThread().getName()+"---卖完："+nub);
                   }
               }finally {
                   lock.unlock();
               }


    }

}
