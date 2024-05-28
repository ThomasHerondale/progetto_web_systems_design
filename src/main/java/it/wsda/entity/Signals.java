package it.wsda.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "signals")
public class Signals {

    @Id
    @Column(nullable = false, length = 64)
    private String signalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id", nullable = false)
    private Facilities facility;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedules schedule;

    @Column(nullable = true, length = 12)
    private String advId;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private Timestamp timestamp;
}