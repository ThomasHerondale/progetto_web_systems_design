package it.wsda.services;

import it.wsda.dto.ScheduleDTO;

import java.util.Collection;

public interface SchedulesService {
    Collection<ScheduleDTO> getAllSchedules();
    ScheduleDTO getScheduleById(String id);
    void createSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO getScheduleByFacilityId(int facilityId);
}
