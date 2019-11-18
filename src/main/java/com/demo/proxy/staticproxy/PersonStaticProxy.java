package com.demo.proxy.staticproxy;

import com.demo.proxy.Person;

public class PersonStaticProxy implements Person {

    private final Person person;

    public PersonStaticProxy(Person person) {
        this.person = person;
    }

    @Override
    public void playGame() {
        System.out.println("程序员插上了《马里奥：奥德赛》的游戏卡。");
        person.playGame();
        System.out.println("程序员通关了《马里奥：奥德赛》游戏！");
    }
}
