package it.wsda.services.impl;

import it.wsda.repository.SignalsRepository;
import it.wsda.services.SignalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignalsServiceImpl implements SignalsService {
    SignalsRepository repository;

    @Autowired
    public SignalsServiceImpl(SignalsRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getAdvertisementTotalDuration(String id) {
        return repository.findTotalDurationByAdv_id(id);
    }
}
