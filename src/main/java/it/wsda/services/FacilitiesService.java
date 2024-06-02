package it.wsda.services;

import it.wsda.dto.FacilityDTO;

import java.util.Collection;

public interface FacilitiesService {

    FacilityDTO findFacilityById(int id);

    Collection<FacilityDTO> getAllFacilities();

    void updateFacility(FacilityDTO facilityDTO);

    void createFacility(FacilityDTO facilityDTO);
}
