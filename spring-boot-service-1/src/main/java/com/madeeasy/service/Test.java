package com.madeeasy.service;

public class Test {

    public static void main(String[] args) {

        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.generateToken("pabitrabera");
        String userName = jwtUtils.getUserName(token);
        System.out.println("userName = " + userName);
        boolean flag = jwtUtils.validateToken(token, "pabitrabera");
        System.out.println("flag = " + flag);
    }
}