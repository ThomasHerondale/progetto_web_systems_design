package it.wsda.services.impl;

import it.wsda.dto.SignalDTO;
import it.wsda.repository.SignalsRepository;
import it.wsda.services.SignalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignalsServiceImpl implements SignalsService {
    SignalsRepository repository;

    @Autowired
    public SignalsServiceImpl(SignalsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> getAdvScreenTimes() {
        var signals = repository.findAll().stream()
                .map(SignalDTO::fromEntity)
                .distinct()     // get only one signal per sessionId
                .toList();

        var map = new HashMap<String, Integer>();
        for (var signal : signals) {
            map.merge(signal.getAdvId(), signal.getDuration(), Integer::sum);
        }

        return map;
    }

    @Override
    public Map<String, Integer> getAdvScreenTimes(Date startDate, Date endDate) {
        var signals = repository.findAllByTimeRange(startDate, endDate)
                .stream()
                .map(SignalDTO::fromEntity)
                .distinct()
                .toList();

        var map = new HashMap<String, Integer>();
        for (var signal : signals) {
            map.merge(signal.getAdvId(), signal.getDuration(), Integer::sum);
        }

        return map;
    }
}
