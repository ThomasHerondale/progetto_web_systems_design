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
public class Signal {

    @Id
    @Column(name = "signal_id", nullable = false, length = 64)
    private String signalId;

    @Column(name = "facility_id", nullable = true)
    private Integer facilityId;

    @Column(name = "schedule_id", nullable = true, length = 12)
    private String scheduleId;

    @Column(name = "adv_id", nullable = true, length = 12)
    private String advId;

    @Column(nullable = true)
    private Integer duration;

    @Column(nullable = true)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Facility facility;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Schedule schedule;
}