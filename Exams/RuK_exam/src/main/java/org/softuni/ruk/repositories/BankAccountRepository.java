package org.softuni.ruk.repositories;

import org.softuni.ruk.domain.models.BankAccount;
import org.softuni.ruk.domain.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {
}
