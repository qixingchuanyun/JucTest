package com.xuetao.java.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * **************************************************************
 *
 * @描述 :多线程之间按顺序调用，实现A先干->B次之->C最后
 *  *
 *  * 三个线程启动，要求如下：
 *  *
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * 接着
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * ......来10轮
 * @作者 :	薛涛
 * @版本 :	v1.0
 * @日期 :	$
 * @修改日志 :
 * @修改人 :
 * @修订后版本 :	v2.0
 * @修改时间 : ***************************************************************
 */
public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();

    }



}
class ShareResource{
    //1 A资源打印5次  2：B线程打印十次  3：C打印十五次
    private  Integer flag=1;
    private Lock lock = new ReentrantLock();


    //三把钥匙
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public  void print5(){
        lock.lock();
        try {
            //判断
            while (flag!=1){
                //A服务暂停
                c1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            //唤醒B
            flag=2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public  void print10(){
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            flag=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public  void print15(){
        lock.lock();
        try {
            while (flag!=3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+""+i);
            }
            flag=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}