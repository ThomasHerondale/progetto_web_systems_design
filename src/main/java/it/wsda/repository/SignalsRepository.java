package it.wsda.repository;

import it.wsda.entity.Signals;
import it.wsda.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignalsRepository extends JpaRepository<Signals, String> {

    // Questo metodo trova un segnale specifico dato il suo ID.
    Signals findSignalsBySignalId(String signalId);

    // Questo metodo trova tutti i segnali associati a uno specifico impianto.
    List<Signals> findAllByFacility(Facilities facility);

    // Questo metodo trova tutti i segnali associati a un determinato ID del palinsesto.
    List<Signals> findAllByScheduleId(String scheduleId);
}