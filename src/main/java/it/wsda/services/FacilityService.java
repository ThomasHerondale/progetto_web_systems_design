package it.wsda.services;

import it.wsda.dto.FacilityDTO;

import java.util.Collection;

public interface FacilityService {
    Collection<FacilityDTO> getAllFacilities();

    void updateFacility(FacilityDTO facilityDTO);

    void createFacility(FacilityDTO facilityDTO);
}
