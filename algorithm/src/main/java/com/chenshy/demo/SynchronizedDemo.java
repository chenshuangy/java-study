package com.chenshy.demo;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-26
 */
public class SynchronizedDemo {
    public void method() {
        // 同步代码块
        // monitorenter,monitorexit
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
