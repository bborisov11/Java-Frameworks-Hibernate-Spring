package org.bookshop.system.bookshopsystem;

import org.bookshop.system.bookshopsystem.models.entity.Author;
import org.bookshop.system.bookshopsystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {

    private static final String AUTHOR_RESOURCE_FILE = "D:\\SoftUni\\Java DB Advanced\\Spring data intro\\src\\main\\resources\\authors.txt";
    private final AuthorService authorService;

    @Autowired
    public Runner(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws IOException {
        initAuthor();
    }

    public void initAuthor() throws IOException {
        List<String> allAuthors = Files.readAllLines(Paths.get(AUTHOR_RESOURCE_FILE));

        List<Author> authors = allAuthors.stream().map(x ->
        {
            String[] listOfAuthor = x.split("\\s+");

            return new Author(listOfAuthor[0],listOfAuthor[1]);
        }).collect(Collectors.toList());

        this.authorService.saveAuthorIntoDb(authors);

    }
}
