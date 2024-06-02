package it.wsda.repository;

import it.wsda.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedule, String> {
    Schedule findScheduleById(String scheduleId);
    @Query("SELECT s FROM Schedule s WHERE s.id = (SELECT f.schedule.id FROM Facility f WHERE f.id = :facilityId)")
    Schedule findByFacilityId(@Param("facilityId") int facilityId);
}