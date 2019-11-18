package com.demo.proxy.springproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonCglibProxy implements MethodInterceptor {


    private Object target;

    public PersonCglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("程序员插上了《马里奥：奥德赛》的游戏卡。");
        System.out.println("程序员使用了《马里奥：奥德赛》的通关秘籍。");
        Object result = method.invoke(target, objects);
        System.out.println("程序员打通了《马里奥：奥德赛》，并且达成了全收集成就！");
        return result;
    }

    public Object getProxyObject() {
        Enhancer enhancer = new Enhancer();

        //设置生成代理类的目标对象（代理类对象是目标对象的子类）
        enhancer.setSuperclass(target.getClass());
        //设置回调方法
        enhancer.setCallback(this);
        //生成代理对象
        return enhancer.create();
    }

}
