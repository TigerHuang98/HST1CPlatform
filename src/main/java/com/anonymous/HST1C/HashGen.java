package com.anonymous.HST1C;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGen {
    public static void main (String[] args){
        String password ="123456";
        int size=100;
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String[] hashs=new String[size];
        for(int i=0;i<size;i++){
            hashs[i]=passwordEncoder.encode(password);
            System.out.println(hashs[i]);
        }
        for(int i=0;i<size;i++){
//            System.out.println(passwordEncoder.matches(password,hashs[i]));
        }
    }
}
