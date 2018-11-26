package org.bookshop.system.bookshopsystem.services;

import org.bookshop.system.bookshopsystem.models.entity.Author;
import org.bookshop.system.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public void saveAuthorIntoDb(List<Author> authors) {
         this.authorRepository.save(authors);
    }


}