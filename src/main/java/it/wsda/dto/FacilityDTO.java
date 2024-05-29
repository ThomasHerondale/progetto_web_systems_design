package it.wsda.dto;

import it.wsda.entity.Facility;
import it.wsda.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO {
    private Integer id;
    private String description;
    private Double latitude;
    private Double longitude;
    private String status;
    private ScheduleDTO schedule;

    public static FacilityDTO fromEntity(Facility facility) {
        return new FacilityDTO(
                facility.getId(),
                facility.getDescription(),
                facility.getLatitude(),
                facility.getLongitude(),
                facility.getStatus().name(),
                ScheduleDTO.fromEntity(facility.getSchedule())
        );
    }
}