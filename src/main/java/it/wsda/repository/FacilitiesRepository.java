package it.wsda.repository;
import it.wsda.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facility, Integer> {

    // Questo metodo trova un impianto specifico dato il suo ID.
    Facility findFacilitiesById(Integer id);

    // Questo metodo trova tutti gli impianti con un determinato stato (attivo/inattivo).
    List<Facility> findAllByStatus(String status);
}