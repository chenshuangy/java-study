package com.chenshy.demo;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-26
 */
public class TargetObject {

    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
