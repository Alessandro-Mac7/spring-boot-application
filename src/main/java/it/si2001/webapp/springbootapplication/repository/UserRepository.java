package it.si2001.webapp.springbootapplication.repository;

import it.si2001.webapp.springbootapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "from User where email = :email")
    Optional<User> findByEMail(@Param("email") String email);

    @Query(value = "FROM User u WHERE u.typology.type = 'Customer'")
    List<User> findCustomer();
}
