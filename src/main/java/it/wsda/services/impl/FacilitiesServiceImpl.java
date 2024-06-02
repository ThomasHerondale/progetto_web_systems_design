package it.wsda.services.impl;

import it.wsda.dto.FacilityDTO;
import it.wsda.entity.Facility;
import it.wsda.entity.Facility.Status;
import it.wsda.entity.Schedule;
import it.wsda.repository.FacilitiesRepository;
import it.wsda.services.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacilitiesServiceImpl implements FacilitiesService {
    private final FacilitiesRepository facilitiesRepository;

    @Autowired
    public FacilitiesServiceImpl(
            FacilitiesRepository facilitiesRepository
    ) {
        this.facilitiesRepository = facilitiesRepository;
    }

    @Override
    public FacilityDTO findFacilityById(int id) {
        var facility = facilitiesRepository.findFacilitiesById(id).orElseThrow();
        return FacilityDTO.fromEntity(facility);
    }

    @Override
    public Collection<FacilityDTO> getAllFacilities() {
        return facilitiesRepository.findAll()
                .stream()
                .map(FacilityDTO::fromEntity)
                .toList();
    }

    @Override
    public void updateFacility(FacilityDTO facilityDTO) {
        var optFacility = facilitiesRepository.findFacilitiesById(facilityDTO.getId());

        // update facility data if match is found
        if (optFacility.isPresent()) {
            optFacility.get().setStatus(Status.valueOf(facilityDTO.getStatus()));
            optFacility.get().setSchedule(Schedule.fromDTO(facilityDTO.getSchedule()));

            facilitiesRepository.save(optFacility.get());
        } else {
            throw new RuntimeException("No facility with id " + facilityDTO.getId() + " has been found.");
        }
    }

    @Override
    public void createFacility(FacilityDTO facilityDTO) {
        var facility = new Facility();
        facility.setId(facilityDTO.getId());
        facility.setDescription(facilityDTO.getDescription());
        facility.setLatitude(facilityDTO.getLatitude());
        facility.setLongitude(facilityDTO.getLongitude());
        facility.setStatus(Status.valueOf(facilityDTO.getStatus()));
        facility.setSchedule(Schedule.fromDTO(facilityDTO.getSchedule()));

        facilitiesRepository.save(facility);
    }
}
