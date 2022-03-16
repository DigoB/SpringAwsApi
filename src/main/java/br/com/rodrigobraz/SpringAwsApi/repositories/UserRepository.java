package br.com.rodrigobraz.SpringAwsApi.repositories;

import br.com.rodrigobraz.SpringAwsApi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND password = :password")
    public Optional<User> login(String email, String password);

    Optional<User> findByEmail(String email);
}
