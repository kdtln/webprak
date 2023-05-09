package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.DealDAO;
import ru.msu.cmc.webprak.models.Deal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class DealDAOImplementation extends CommonDAOImplementation<Deal, Long> implements DealDAO {
    public DealDAOImplementation () {
        super(Deal.class);
    }

    @Override
    public List<Deal> getAllDealsByValue(String col_name, String value) {
        List<Deal> result = new ArrayList<>();
        if (Objects.equals(col_name, "worker")) {
            List<Deal> all_deals = this.getAll().stream().toList();
            for (Deal deal : all_deals) {
                if (Objects.equals(deal.getWorker().getId(), Long.valueOf(value))) {
                    result.add(deal);
                }
            }
        }
        else if (Objects.equals(col_name, "service")) {
            List<Deal> all_deals = this.getAll().stream().toList();
            for (Deal deal : all_deals) {
                if (Objects.equals(deal.getService().getId(), Long.valueOf(value))) {
                    result.add(deal);
                }
            }
        }
        else if (Objects.equals(col_name, "client")) {
            List<Deal> all_deals = this.getAll().stream().toList();
            for (Deal deal : all_deals) {
                if (Objects.equals(deal.getClient().getId(), Long.valueOf(value))) {
                    result.add(deal);
                }
            }
        }
        else {
            try (Session session = sessionFactory.openSession()) {
                Query<Deal> query = session.createQuery
                        ("FROM Deal WHERE " + col_name + " = :v", Deal.class);

                if ((Objects.equals(col_name, "start")) || (Objects.equals(col_name, "end"))) {
                    query.setParameter("v", Date.valueOf(value));
                } else {
                    query.setParameter("v", value);
                }
                return query.getResultList();
            }
        }
        return result;
    }

    @Override
    public List<Deal> getAllDealsByColRange(String col_name, String lowerDateBound, String upperDateBound) {
        try (Session session = sessionFactory.openSession()) {
            Query<Deal> query = session.createQuery
                    ("FROM Deal WHERE " + col_name + " BETWEEN :lowerd and :upperd", Deal.class);

            if ((Objects.equals(col_name, "start")) || (Objects.equals(col_name, "end"))) {
                query.setParameter("lowerd", Date.valueOf(lowerDateBound));
                query.setParameter("upperd", Date.valueOf(upperDateBound));
            } else {
                query.setParameter("lowerd", Long.parseLong(lowerDateBound, 10));
                query.setParameter("upperd", Long.parseLong(upperDateBound, 10));
            }
            return query.getResultList();
        }
    }
}