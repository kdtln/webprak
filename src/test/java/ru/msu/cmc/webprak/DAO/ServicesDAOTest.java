package ru.msu.cmc.webprak.DAO;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Services;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ServicesDAOTest {
    @Autowired
    private ServicesDAO servicesDAO;
    @Autowired
    private SessionFactory sessionFactory;
    /*
    1,Защита потребителей,1000,"Любые услуги, консультации по защите потребителей"
    2,Недвижимость,1000,"Всё, что связано с недвижимостью"
    3,Цифровое право,2000,Защита в области информационных технологий
    */

    @Test
    void test_selection() {
        List<Services> services_for_2000 = servicesDAO.getAllServicesByColRange("price", "1500", "2100");
        Services gt_service_for_2000 = new Services(3L, "Цифровое право", 2000L, "Защита в области информационных технологий");
        assertEquals(1, services_for_2000.size());
        assertEquals(gt_service_for_2000, services_for_2000.get(0));

        List<Services> services_for_1000 = servicesDAO.getAllServicesByValue("price", "1000");
        assertEquals(2, services_for_1000.size());
        Services gt_service_for_1000 = new Services(1L,"Защита потребителей", 1000L,"Любые услуги, консультации по защите потребителей");
        assertEquals(gt_service_for_1000, services_for_1000.get(0));

        List<Services> services_for_property = servicesDAO.getAllServicesByValue("name", "Недвижимость");
        Services property_service = new Services(2L, "Недвижимость", 1000L, "Всё, что связано с недвижимостью");
        assertEquals(1, services_for_property.size());
        assertEquals(property_service, services_for_property.get(0));

        services_for_property = servicesDAO.getAllServicesByColRange("name", "Б", "В");
        assertEquals(0, services_for_property.size());

    }
}

