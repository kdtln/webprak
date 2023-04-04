package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.WorkersDAO;
import ru.msu.cmc.webprak.models.Workers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class WorkersDAOImplementation extends CommonDAOImplementation<Workers, Long> implements WorkersDAO {
    public WorkersDAOImplementation() {
        super(Workers.class);
    }

    @Override
    public List<Workers> getAllWorkersByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            String my_query_string = "FROM Workers WHERE " + col_name + " = :v";
            Query<Workers> query = session.createQuery(my_query_string, Workers.class);
            if ((Objects.equals(col_name, "id_worker"))
                    || (Objects.equals(col_name, "is_admin"))) {
                query.setParameter("v", Long.parseLong(value));
            } else {
                query.setParameter("v", value);
            }
            return query.getResultList();
        }
    }
}
