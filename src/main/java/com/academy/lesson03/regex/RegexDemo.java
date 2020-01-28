package com.academy.lesson03.regex;

import java.util.Arrays;

public class RegexDemo {
    public static void main(String[] args) {
        String str = "123name;lastname:status";
        System.out.println(str.matches("[\\d]{4}name"));
        System.out.println(str.matches("[\\d]{3}name"));

        String[] array = str.split("[:;]");
        System.out.println(Arrays.toString(array));
    }
}
