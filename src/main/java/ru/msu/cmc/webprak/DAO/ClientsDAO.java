package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Clients;

import java.util.List;

public interface ClientsDAO extends CommonDAO<Clients, Long> {
    List<Clients> getAllClientsByValue(String col_name, String value);

}