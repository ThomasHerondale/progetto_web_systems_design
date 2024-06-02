package it.wsda.dto;

import it.wsda.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private String id;
    private String filePath;

    public static ScheduleDTO fromEntity(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getFilePath());
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("filePath", filePath);
        return jsonObject;
    }

    public static ScheduleDTO fromJSON(JSONObject json) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(json.getString("id"));
        scheduleDTO.setFilePath(json.getString("filePath"));

        return scheduleDTO;
    }
}