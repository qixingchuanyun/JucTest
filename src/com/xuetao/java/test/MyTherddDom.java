package com.xuetao.java.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

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
public class MyTherddDom {


    public static void main(String[] args) {


        ExecutorService  executorService = Executors.newFixedThreadPool(3);
        SaleTicket saleTicket = new SaleTicket();

        try {
            for (int i = 0; i < 30; i++) {
                final int tim = i;
                executorService.execute(()->{
                    saleTicket.jianPiao();
                    //  System.out.println(Thread.currentThread().getName()+"受理业务"+"客户号："+tim);

                });

            }
        }finally {
            executorService.shutdown();
        }




    }
}
