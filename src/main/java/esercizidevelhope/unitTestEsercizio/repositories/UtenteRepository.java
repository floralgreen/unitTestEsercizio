package esercizidevelhope.unitTestEsercizio.repositories;

import esercizidevelhope.unitTestEsercizio.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
