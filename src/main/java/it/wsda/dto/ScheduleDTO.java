package it.wsda.dto;

import it.wsda.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private String id;
    private String filePath;

    public static ScheduleDTO fromEntity(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getFilePath());
    }
}