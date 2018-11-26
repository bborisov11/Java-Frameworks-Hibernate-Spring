package org.softuni.mostwanted.repositories;


import org.softuni.mostwanted.domain.models.Race;
import org.softuni.mostwanted.domain.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TownRepository extends JpaRepository<Town,Integer> {
    Town findByName(String name);
}
