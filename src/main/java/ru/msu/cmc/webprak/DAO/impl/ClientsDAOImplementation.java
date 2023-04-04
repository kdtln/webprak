package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.ClientsDAO;
import ru.msu.cmc.webprak.models.Clients;

import java.util.List;
import java.util.Objects;


@Repository
public class ClientsDAOImplementation extends CommonDAOImplementation<Clients, Long> implements ClientsDAO {

    public ClientsDAOImplementation() {
        super(Clients.class);
    }

    @Override
    public List<Clients> getAllClientsByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            String my_query_string = "FROM Clients WHERE " + col_name + " = :v";
            Query<Clients> query = session.createQuery(my_query_string, Clients.class);

            if ((Objects.equals(col_name, "id_client")) || (Objects.equals(col_name, "face"))) {
                query.setParameter("v", value);
            } else {
                query.setParameter("v", value);
            }
            return query.getResultList();
        }
    }
}