package ru.msu.cmc.webprak.DAO;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Clients;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Заполненные значения таблицы Clients
        1,  ,Иванов Иван Иванович,ivan_ivanov@mail.ru,iv_ivanov,12qwerTy
        2,  ,Сидоров Семен Семенович,sidorov@gmail.com,s_sid,sSqwerty
        3,postgres,Федотов Константин Анатольевич,fedot123@postgres.ru,post_fedot,qwerty
        4,msu,Кабалевская Галина Борисовна,kgb@cs.msu.ru,kgb,qwertY1
*/


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ClientsDAOTest {
    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void test_getall_getbyid_update() {
        Clients gt1 = new Clients(1L,"a", "Иванов Иван Иванович", "ivan_ivanov@mail.ru", "iv_ivanov", "12qwerTy");
        Clients gt2 = new Clients(2L,"b", "Сидоров Семен Семенович", "sidorov@gmail.com", "s_sid", "sSqwerty");
        Clients gt3 = new Clients(3L, "postgres", "Федотов Константин Анатольевич", "fedot123@postgres.ru", "post_fedot", "qwerty");
        Clients gt4 = new Clients(4L, "msu", "Кабалевская Галина Борисовна", "kgb@cs.msu.ru", "kgb", "qwertY1");

        List<Clients> gt_all = new ArrayList<>();
        gt_all.add(gt1);
        gt_all.add(gt2);
        gt_all.add(gt3);
        gt_all.add(gt4);

        List<Clients> answer_all = new ArrayList<>(clientsDAO.getAll());
        assertTrue((answer_all.size() == gt_all.size()) &&answer_all.containsAll(gt_all) && gt_all.containsAll(answer_all));


        Clients KonstFedotov = clientsDAO.getById(3L);
        assertEquals(KonstFedotov, gt3);


        Clients gt3_modified = new Clients(3L, "post","Федотов Иван Анатольевич" , "fedot12@postgres.ru", "post_fedott", "qwertyy");
        List<Clients> gt_all_modified = new ArrayList<>();
        gt_all_modified.add(gt1);
        gt_all_modified.add(gt2);
        gt_all_modified.add(gt3_modified);
        gt_all_modified.add(gt4);
        Clients IvanFedotov = KonstFedotov;
        IvanFedotov.setFace("Федотов Иван Анатольевич");
        IvanFedotov.setName("post");
        IvanFedotov.setEmail("fedot12@postgres.ru");
        IvanFedotov.setLogin("post_fedott");
        IvanFedotov.setPassword("qwertyy");
        clientsDAO.update(IvanFedotov);
        assertEquals(IvanFedotov, gt3_modified);
        List<Clients> answer_all_modified = new ArrayList<>(clientsDAO.getAll());

        clientsDAO.update(gt3);

        assertTrue(answer_all_modified.size() == gt_all_modified.size()
                && answer_all_modified.containsAll(gt_all_modified)
                && gt_all_modified.containsAll(answer_all_modified));

    }

    // Тест - список всех клиентов по определённому имени и по номеру телефона
    @Test
    void get_by_name() {
        Clients gt = new Clients(1L, "a", "Иванов Иван Иванович", "ivan_ivanov@mail.ru", "iv_ivanov", "12qwerTy");
        List<Clients> answer = clientsDAO.getAllClientsByValue("face", "Иванов Иван Иванович");
        assertEquals(1, answer.size());
        assertEquals(gt, answer.get(0));

        answer = clientsDAO.getAllClientsByValue("email", "ivan_ivanov@mail.ru");
        assertEquals(1, answer.size());
        assertEquals(gt, answer.get(0));


    }

    @Test
    void get_wrong_id() {
        Clients nobody = clientsDAO.getById(300000000L);
        assertNull(nobody);
    }
}