package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Smartphone implements PhoneCall,Browse {

    public void getCall(String number) {

        if(number.matches("[0-9]+")) {
            System.out.println("Calling... "+number);
        }
        else {
            System.out.println("Invalid number!");
        }
    }

    public void getBrowse(String site) {
        if(site.matches(".*\\d+.*")) {
            System.out.println("Invalid URL!");
        }
        else {
            System.out.println("Browsing: "+site+"!");
        }
    }
}
