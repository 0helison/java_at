package br.com.ponteshelison.medicalconsult.consult.repositories;

import br.com.ponteshelison.medicalconsult.consult.domain.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
}
