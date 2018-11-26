package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split(" ");
        String[] browsers = scanner.nextLine().split(" ");

        Smartphone smartphone = new Smartphone();

        for (int i = 0; i < numbers.length; i++) {
            smartphone.getCall(numbers[i]);
        }

        for (int i = 0; i < browsers.length; i++) {
            smartphone.getBrowse(browsers[i]);
        }


    }
}
