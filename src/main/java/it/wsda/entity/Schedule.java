package it.wsda.entity;

import it.wsda.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @Column(name = "id",nullable = false, length = 12)
    private String id;

    @Column(name = "file_path", length = 256)
    private String filePath;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Facility> facilities;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Signal> signals;

    public static Schedule fromDTO(ScheduleDTO dto) {
        var schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setFilePath(dto.getFilePath());
        return schedule;
    }
}