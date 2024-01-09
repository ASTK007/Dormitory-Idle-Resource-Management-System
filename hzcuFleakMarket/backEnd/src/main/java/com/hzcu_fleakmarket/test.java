package com.hzcu_fleakmarket;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        String classpath = ResourceUtils.getURL("classpath:").getPath();
System.out.println(classpath);
}
}
