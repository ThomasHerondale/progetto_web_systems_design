package it.wsda.dto;

import it.wsda.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

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

    public static FacilityDTO fromJSON(JSONObject json) {
        var facility = new FacilityDTO();
        facility.setId(json.getInt("id"));
        facility.setDescription(json.getString("description"));
        facility.setLatitude(json.getDouble("latitude"));
        facility.setLongitude(json.getDouble("longitude"));
        facility.setStatus(json.getString("status"));
        facility.setSchedule(ScheduleDTO.fromJSON(json.getJSONObject("schedule")));

        return facility;
    }

    public JSONObject toJSON() {
        var json = new JSONObject();
        json.put("id", id);
        json.put("description", description);
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        json.put("status", status);
        if (schedule != null) json.put("schedule", schedule.toJSON());
        else json.put("schedule", JSONObject.NULL);

        return json;
    }
}