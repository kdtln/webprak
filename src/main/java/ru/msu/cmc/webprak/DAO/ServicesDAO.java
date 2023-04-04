package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Services;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

public interface ServicesDAO extends CommonDAO<Services, Long> {
    List<Services> getAllServicesByValue(String col_name, String value);
    List<Services> getAllServicesByColRange(String col_name, String lowerDateBound, String upperDateBound);
}