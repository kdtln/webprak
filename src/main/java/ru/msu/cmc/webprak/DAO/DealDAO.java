package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Deal;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

public interface DealDAO extends CommonDAO<Deal, Long> {
    List<Deal> getAllDealsByValue(String col_name, String value);
    List<Deal> getAllDealsByColRange(String col_name, String lowerDateBound, String upperDateBound);
}