package com.demo.proxy.staticproxy;

import com.demo.proxy.Person;
import com.demo.proxy.Programmer;

public class StaticProxyMain {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        Person person = new PersonStaticProxy(programmer);
        person.playGame();
    }
}
