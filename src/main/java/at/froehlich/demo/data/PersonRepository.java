package at.froehlich.demo.data;

import at.froehlich.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p from Person p WHERE p.lastName = ?1")
    List<Person> getPersonsWithLastName(String lastName);
}
