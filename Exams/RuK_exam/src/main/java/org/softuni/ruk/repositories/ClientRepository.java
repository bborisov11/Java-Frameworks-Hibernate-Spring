package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.models.Branch;
import org.softuni.ruk.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findById(Integer id);
}
