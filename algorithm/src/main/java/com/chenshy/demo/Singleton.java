package com.chenshy.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

/**
 * 单例模式
 *
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-26
 */
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    // 为 uniqueInstance 分配内存空间
                    // 初始化 uniqueInstance
                    // 将 uniqueInstance 指向分配的内存地址
                    //  禁止指令重排
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 10;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread thread = new MyThread();
        thread.start();

        MyThread2 thread2 = new MyThread2();
        Thread t = new Thread(thread2);
        t.start();

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new MyThread3());
        System.out.println(future.get());

        threadPool.execute(new MyThread2());

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 26; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyThread2();
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        //        executor.shutdown();
        //        while (!executor.isTerminated()) {
        //        }
        //        System.out.println("Finished all threads");
    }


}

/**
 * 方式一，继承Thread，重写run方法
 */
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread，重写run方法");
        System.out.println(Singleton.getInstance());
    }
}

/**
 * 方式二，实现Runnable接口
 */
class MyThread2 implements Runnable {


    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(100000);
        System.out.println("实现Runnable接口");
        System.out.println(Singleton.getInstance());
    }
}

class MyThread3 implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.out.println("实现Callable接口");
        System.out.println(Singleton.getInstance());
        return "call";
    }
}

