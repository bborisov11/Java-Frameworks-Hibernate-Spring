package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String driversName = scanner.nextLine();

        Ferarri ferarri = new Ferarri();

        System.out.println(ferarri.MODEL_NAME+"/"+ferarri.getBreaks()+"/"+ferarri.getGasPedal()+"/"+driversName);
    }
}
