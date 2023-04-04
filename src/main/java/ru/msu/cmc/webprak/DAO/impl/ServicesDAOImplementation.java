package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.ServicesDAO;
import ru.msu.cmc.webprak.models.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class ServicesDAOImplementation extends CommonDAOImplementation<Services, Long> implements ServicesDAO {
    public ServicesDAOImplementation() {
        super(Services.class);
    }

    @Override
    public List<Services> getAllServicesByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<Services> query = session.createQuery("FROM Services WHERE " + col_name + " = :v", Services.class);
            if ((Objects.equals(col_name, "name")) || (Objects.equals(col_name, "describe"))) {
                query.setParameter("v", value);
            } else {
                query.setParameter("v", Long.parseLong(value));
            }
            return query.getResultList();
        }
    }

    @Override
    public List<Services> getAllServicesByColRange(String col_name, String lower_bound, String upper_bound) {
        try (Session session = sessionFactory.openSession()) {
            Query<Services> query = session.createQuery("FROM Services WHERE " + col_name + " BETWEEN :l AND :u", Services.class);
            if ((Objects.equals(col_name, "name")) || (Objects.equals(col_name, "describe"))) {
                query.setParameter("l", lower_bound);
                query.setParameter("u", upper_bound);
            } else {
                query.setParameter("l", Long.parseLong(lower_bound, 10));
                query.setParameter("u", Long.parseLong(upper_bound, 10));
            }
            return query.getResultList();
        }
    }
}