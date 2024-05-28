package it.wsda.repository;

import it.wsda.entity.Signal;
import it.wsda.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
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
}