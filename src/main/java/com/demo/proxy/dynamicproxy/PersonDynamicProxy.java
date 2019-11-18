package com.demo.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonDynamicProxy implements InvocationHandler {

    public Object target;

    public PersonDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("程序员插上了《塞尔达传说：荒野之息》的游戏卡。");
        Object result = method.invoke(target, args);
        System.out.println("程序员没有通关《塞尔达传说：荒野之息》😣");
        return result;
    }
}
