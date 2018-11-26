package com.company;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Class[] citizenInterfaces = Citizen.class.getInterfaces();
        if (Arrays.asList(citizenInterfaces).contains(Birthable.class)
                && Arrays.asList(citizenInterfaces).contains(Identifiable.class)) {
            Method[] methods = Birthable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            methods = Identifiable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            Scanner scanner = new Scanner(System.in);

            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            String id = scanner.nextLine();
            String birthdate = scanner.nextLine();
            Identifiable identifiable = new Citizen(name, age, id, birthdate);
            Birthable birthable = new Citizen(name, age, id, birthdate);
        }
    }

    interface Person {
        String getName();

        int getAge();
    }

    interface Identifiable {
        String getId();
    }

    interface Birthable {
        String getBirthdate();
    }

    static class Citizen implements Person, Identifiable, Birthable {

        private String name;
        private int age;
        private String id;
        private String birthdate;

        public Citizen(String name, int age, String id, String birthdate) {
            this.name = name;
            this.age = age;
            this.id = id;
            this.birthdate = birthdate;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getBirthdate() {
            return birthdate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getAge() {
            return age;
        }
    }
}
