package com.xuetao.java.test;

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
public class TestMain {


    public static void main(String[] args) {


        SaleTicket saleTicket = new SaleTicket();

        new Thread(()->{ for (int i = 0; i <35 ; i++) {saleTicket.jianPiao();} },"第AAAA位售票员").start();
        new Thread(()->{ for (int i = 0; i <35 ; i++){ saleTicket.jianPiao();} },"第BBBB位售票员").start();
        new Thread(()->{ for (int i = 0; i <35 ; i++){ saleTicket.jianPiao();} },"第CCCC位售票员").start();




    }
}
