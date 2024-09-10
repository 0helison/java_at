package br.com.ponteshelison.medicalconsult.user.repositories;

import br.com.ponteshelison.medicalconsult.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
