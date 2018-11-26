package org.bookshop.system.bookshopsystem.services;

import org.bookshop.system.bookshopsystem.models.entity.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    void saveAuthorIntoDb(List<Author> authors);
}
