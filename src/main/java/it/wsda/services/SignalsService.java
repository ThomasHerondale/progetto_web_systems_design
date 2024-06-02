package it.wsda.services;

import java.util.Date;
import java.util.Map;

public interface SignalsService {
    Map<String, Integer> getAdvScreenTimes();
    Map<String, Integer> getAdvScreenTimes(Date startDate, Date endDate);
}
