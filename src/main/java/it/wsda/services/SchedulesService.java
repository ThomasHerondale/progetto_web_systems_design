package it.wsda.services;

import it.wsda.dto.ScheduleDTO;

import java.util.Collection;

public interface SchedulesService {
    Collection<ScheduleDTO> getAllSchedules();
}
