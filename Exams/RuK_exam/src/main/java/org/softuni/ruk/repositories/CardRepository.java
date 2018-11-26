package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.models.Branch;
import org.softuni.ruk.domain.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
