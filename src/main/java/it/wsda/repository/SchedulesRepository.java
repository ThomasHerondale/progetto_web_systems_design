package it.wsda.repository;

import it.wsda.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedule, String> {

    // Questo metodo trova un palinsesto specifico dato il suo ID.
    Schedule findSchedulesById(String id);
}