package com.chenshy.demo;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-26
 */
public class SynchronizedDemo2 {
    //  flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    public synchronized void method() {
        System.out.println("synchronized 方法");
    }
}
