package it.wsda.dto;

import it.wsda.entity.Signal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
public class SignalDTO {
    private int facilityId;
    private String advId;
    private String sessionId;
    private int duration;
    private Timestamp timestamp;

    public static SignalDTO fromEntity(Signal signal) {
        return new SignalDTO(
                signal.getFacility().getId(),
                signal.getAdvId(),
                signal.getSessionId(),
                signal.getDuration(),
                signal.getTimestamp()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignalDTO signalDTO = (SignalDTO) o;
        return Objects.equals(sessionId, signalDTO.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sessionId);
    }
}
