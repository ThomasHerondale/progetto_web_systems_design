package it.wsda.dto;

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
    private String scheduleId;
}