package org.bookshop.system.bookshopsystem.repositories;

import org.bookshop.system.bookshopsystem.models.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
