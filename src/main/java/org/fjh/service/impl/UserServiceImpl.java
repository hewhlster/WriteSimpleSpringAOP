package org.fjh.service.impl;


import org.fjh.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void register() {
        System.out.println("userServiceImpl-->regiser得到了执行");
    }

    @Override
    public void delete() {
        int a=10,b=0;
        b = a / b;
        System.out.println("userServiceImpl-->delete得到了执行");

    }
}
