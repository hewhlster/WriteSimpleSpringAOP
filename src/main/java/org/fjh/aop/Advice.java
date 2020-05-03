package org.fjh.aop;

public class Advice {

    public void before(){
        System.out.println("advice-->before");
    }

    public void aftering(){
        System.out.println("advice-->aftering");

    }

    public void after(){
        System.out.println("advice-->after");

    }

    public void exception(){
        System.out.println("advice-->exception");

    }

    public void around(){
        System.out.println("advice-->around");

    }
}
