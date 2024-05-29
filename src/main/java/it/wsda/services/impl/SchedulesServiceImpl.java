package it.wsda.services.impl;

import it.wsda.dto.ScheduleDTO;
import it.wsda.repository.SchedulesRepository;
import it.wsda.services.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SchedulesServiceImpl implements SchedulesService {
    private final SchedulesRepository schedulesRepository;

    @Autowired
    public SchedulesServiceImpl(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    @Override
    public Collection<ScheduleDTO> getAllSchedules() {
        return schedulesRepository.findAll()
                .stream()
                .map(ScheduleDTO::fromEntity)
                .toList();
    }
}
