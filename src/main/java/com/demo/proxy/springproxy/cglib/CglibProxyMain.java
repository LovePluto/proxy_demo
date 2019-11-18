package com.demo.proxy.springproxy.cglib;

import com.demo.proxy.Person;
import com.demo.proxy.Programmer;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyMain {

    public static void main(String[] args) {
        Programmer programmer = new Programmer();

        Enhancer enhancer = new Enhancer();
        //设置生成代理类的目标对象（代理类对象是目标对象的子类）
        enhancer.setSuperclass(Programmer.class);
        //设置回调方法
        enhancer.setCallback(new PersonCglibProxy(programmer));
        //生成代理对象
        Person person = (Person) enhancer.create();

        person.playGame();
    }
}
