package com.demo.proxy.dynamicproxy.myproxy;

import com.demo.proxy.Person;
import com.demo.proxy.Programmer;

public class MyDynamicProxyMain {

    public static void main(String[] args) {
        MyPersonDynamicProxy myPersonDynamicProxy = new MyPersonDynamicProxy(new Programmer());

        Person person = (Person) (MyProxy.newProxyInstance(
                new MyClassLoader(),
                Person.class,
                myPersonDynamicProxy));

        person.playGame();

    }
}
