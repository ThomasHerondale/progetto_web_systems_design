package it.wsda.services.impl;

import it.wsda.dto.FacilityDTO;
import it.wsda.entity.Facility.Status;
import it.wsda.entity.Schedule;
import it.wsda.repository.FacilitiesRepository;
import it.wsda.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilitiesRepository repository;

    @Autowired
    public FacilityServiceImpl(FacilitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<FacilityDTO> getAllFacilities() {
        return repository.findAll()
                .stream()
                .map(FacilityDTO::fromEntity)
                .toList();
    }

    @Override
    public void updateFacility(FacilityDTO facilityDTO) {
        var optFacility = repository.findFacilitiesById(facilityDTO.getId());

        // update facility data if match is found
        if (optFacility.isPresent()) {
            optFacility.get().setStatus(Status.valueOf(facilityDTO.getStatus()));
            optFacility.get().setSchedule(Schedule.fromDTO(facilityDTO.getSchedule()));

            repository.save(optFacility.get());
        } else {
            throw new RuntimeException("No facility with id " + facilityDTO.getId() + " has been found.");
        }
    }
}
