package com.demo.proxy.dynamicproxy;

import com.demo.proxy.Person;
import com.demo.proxy.Programmer;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {

    public static void main(String[] args) {
        PersonDynamicProxy personDynamicProxy = new PersonDynamicProxy(new Programmer());
        Person person = (Person) (Proxy.newProxyInstance(
                Person.class.getClassLoader(), new Class[]{Person.class}, personDynamicProxy));
        person.playGame();
    }
}
