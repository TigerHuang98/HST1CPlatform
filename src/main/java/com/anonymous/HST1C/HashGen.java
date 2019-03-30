package com.anonymous.HST1C;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGen {
    public static void main (String[] args){
        String password ="123456";
        int size=100;
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String[] hashes=new String[size];
        for(int i=0;i<size;i++){
            hashes[i]=passwordEncoder.encode(password);
            System.out.println(hashes[i]);
        }
        for(int i=0;i<size;i++){
//            System.out.println(passwordEncoder.matches(password,hashes[i]));
        }
    }
}
