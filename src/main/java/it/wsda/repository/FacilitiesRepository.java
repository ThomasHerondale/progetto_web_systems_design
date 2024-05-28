package it.wsda.repository;
import it.wsda.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Integer> {

    // Questo metodo trova un impianto specifico dato il suo ID.
    Facilities findFacilitiesById(Integer id);

    // Questo metodo trova tutti gli impianti con un determinato stato (attivo/inattivo).
    List<Facilities> findAllByStatus(String status);
}