package com.demo.proxy.dynamicproxy.myproxy;

import java.lang.reflect.Method;

public class MyPersonDynamicProxy implements MyInvocationHandler {

    private Object target;

    public MyPersonDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("程序员自己手写了一个 switch 游戏。");
        Object result = method.invoke(target, args);
        System.out.println("程序员通关了自己手写的 swtich 游戏！");
        return result;
    }
}
