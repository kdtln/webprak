package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Workers;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

public interface WorkersDAO extends CommonDAO<Workers, Long> {
    List<Workers> getAllWorkersByValue(String col_name, String value);
}