package it.wsda.entity;

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
public class Schedules {

    @Id
    @Column(nullable = false, length = 12)
    private String id;

    @Column(nullable = false, length = 256)
    private String filePath;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Facilities> facilities;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Signals> signals;
}