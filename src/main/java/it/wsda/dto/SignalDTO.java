package it.wsda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class SignalDTO {
    private String facility;
    private String advId;
    private int duration;
    private Timestamp timestamp;
}
