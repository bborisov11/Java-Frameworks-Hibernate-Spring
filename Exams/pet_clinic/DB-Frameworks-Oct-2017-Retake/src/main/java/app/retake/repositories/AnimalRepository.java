package app.retake.repositories;

import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer>{
    Animal findByName(String name);
    Animal findByPassport(Passport passport);
    @Query(value = "SELECT a FROM Animal AS a JOIN a.passport AS p WHERE p.ownerPhoneNumber = :number")
    Set<Animal> findByPassportOwnerPhoneNumber(@Param("number") String phoneNumber);
}
