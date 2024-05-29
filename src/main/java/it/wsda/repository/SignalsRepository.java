package it.wsda.repository;

import it.wsda.entity.Signal;
import it.wsda.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignalsRepository extends JpaRepository<Signal, String> {

    // Questo metodo trova un segnale specifico dato il suo ID.
    Signal findSignalsBySignalId(String signalId);

    // Questo metodo trova tutti i segnali associati a uno specifico impianto.
    List<Signal> findAllByFacility(Facility facility);

    // Questo metodo trova tutti i segnali associati a un determinato ID del palinsesto.
    List<Signal> findAllByScheduleId(String scheduleId);

    @Query("""
        SELECT SUM(session_duration.duration) AS total_duration
        FROM (
                 SELECT s.duration
                 FROM Signal s
                 WHERE s.advId = ?
                 GROUP BY s.sessionId
             ) AS session_duration
    """)
    int findTotalDurationByAdv_id(String adv_id);
}