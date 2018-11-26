package org.bookshop.system.bookshopsystem.repositories;

import org.bookshop.system.bookshopsystem.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
