package com.example.demo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class Runner implements CommandLineRunner {

    private final Gson gson;

    @Autowired
    public Runner(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream stream = Runner.class.getResourceAsStream("/users.json");
        String test = "";
    }
}
