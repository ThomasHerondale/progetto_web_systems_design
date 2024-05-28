package it.wsda.repository;

import it.wsda.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, String> {

    // Questo metodo trova un palinsesto specifico dato il suo ID.
    Schedules findSchedulesById(String id);
}