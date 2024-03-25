package org.example;

public class Main {
    public static void main(String[] args) {

        String text = "Anna,Sofia,Gena";
        String[] split = text.split(",");

        for (String s : split) {
            System.out.println(s);
        }


        System.out.println("Hello world!");
    }
}