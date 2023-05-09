package ru.msu.cmc.webprak.DAO;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*

1,1,2,3,2023-01-16,2023-03-15,
2,2,2,2,2023-02-23,2023-02-25,
3,3,1,3,2023-01-16,2023-01-17,

 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class DealDAOTest {

    @Autowired
    private DealDAO dealDAO;
    @Autowired
    private ClientsDAO clientsDAO;
    @Autowired
    private WorkersDAO workersDAO;
    @Autowired
    private ServicesDAO servicesDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void selections_test() {
        Clients gtc1 = new Clients(1L,"a", "Иванов Иван Иванович", "ivan_ivanov@mail.ru", "iv_ivanov", "12qwerTy");
        Clients gtc2 = new Clients(2L,"b", "Сидоров Семен Семенович", "sidorov@gmail.com", "s_sid", "sSqwerty");
        Clients gtc3 = new Clients(3L, "postgres", "Федотов Константин Анатольевич", "fedot123@postgres.ru", "post_fedot", "qwerty");
        Clients gtc4 = new Clients(4L, "msu", "Кабалевская Галина Борисовна", "kgb@cs.msu.ru", "kgb", "qwertY1");

        Clients cl = clientsDAO.getById(1L);
        assertEquals(cl, gtc1);

        Workers gtw1 = new Workers(1L, "Николаев Пётр Никифорович", "Москва", "+79150879678", "niks@gmail.com", "МГУ ВМК", "Администратор" ,"niks", "dsfhgjhkjl",1L);
        Workers gtw2 = new Workers(2L,"Юрьев Евгений Максимович", "Подольск" ,"89163572938" ,"uru@yandex.ru","НИУ ВШЭ Юридический факультет", "Юрист","uru","dfbhbdkfk",0L);
        Workers gtw3 = new Workers(3L,"Алексеев Максим Петрович","Москва","+79268579709","aleks@mail.ru","МГУ Юридический факультет","Юрист","aleks","lnddskbjn",0L);

        Workers wk = workersDAO.getById(2L);
        assertEquals(wk, gtw2);

        Services gts3 = new Services(3L, "Цифровое право", 2000L, "Защита в области информационных технологий");
        Services gts1 = new Services(1L,"Защита потребителей", 1000L,"Любые услуги, консультации по защите потребителей");
        Services gts2 = new Services(2L, "Недвижимость", 1000L, "Всё, что связано с недвижимостью");

        Services sr = servicesDAO.getById(3L);
        assertEquals(sr, gts3);

        List<Deal> gt = new ArrayList<>();
        Deal gt1 = new Deal(1L, clientsDAO.getById(1L), workersDAO.getById(2L), servicesDAO.getById(2L), Date.valueOf("2023-01-16"), Date.valueOf("2023-03-15"), "цифра");
        Deal gt2 = new Deal(2L, clientsDAO.getById(2L), workersDAO.getById(2L), servicesDAO.getById(2L) ,Date.valueOf("2023-02-23"), Date.valueOf("2023-02-25"), "недвижимость");
        Deal gt3 = new Deal(3L, clientsDAO.getById(3L), workersDAO.getById(1L), servicesDAO.getById(3L), Date.valueOf("2023-01-16"), Date.valueOf("2023-01-17"), "цифра");

        gt.add(gt1);
        gt.add(gt2);
        gt.add(gt3);

        List<Deal> should_be_gt1 = dealDAO.getAllDealsByValue("descr", "недвижимость");
        assertEquals(1, should_be_gt1.size());

        assertEquals(should_be_gt1.get(0).getDescr(), gt.get(1).getDescr());
        assertEquals(should_be_gt1.get(0).getClient(), gt.get(1).getClient());
        assertEquals(should_be_gt1.get(0).getWorker(), gt.get(1).getWorker());
        assertEquals(should_be_gt1.get(0).getService(), gt.get(1).getService());


        should_be_gt1 = dealDAO.getAllDealsByColRange("end", "2023-01-23", "2023-02-25");
        assertEquals(1, should_be_gt1.size());

        assertEquals(should_be_gt1.get(0).getDescr(), gt.get(1).getDescr());
        assertEquals(should_be_gt1.get(0).getClient(), gt.get(1).getClient());
        assertEquals(should_be_gt1.get(0).getWorker(), gt.get(1).getWorker());
        assertEquals(should_be_gt1.get(0).getService(), gt.get(1).getService());

        gt = new ArrayList<>();
        gt.add(gt1);
        gt.add(gt2);

    }

    @Test
    void insertion_and_deleting_test() {
        Clients gtc1 = new Clients(1L,"a", "Иванов Иван Иванович", "ivan_ivanov@mail.ru", "iv_ivanov", "12qwerTy");
        Clients gtc2 = new Clients(2L,"b", "Сидоров Семен Семенович", "sidorov@gmail.com", "s_sid", "sSqwerty");
        Clients gtc3 = new Clients(3L, "postgres", "Федотов Константин Анатольевич", "fedot123@postgres.ru", "post_fedot", "qwerty");
        Clients gtc4 = new Clients(4L, "msu", "Кабалевская Галина Борисовна", "kgb@cs.msu.ru", "kgb", "qwertY1");

        Clients cl = clientsDAO.getById(1L);
        assertEquals(cl, gtc1);

        Workers gtw1 = new Workers(1L, "Николаев Пётр Никифорович", "Москва", "+79150879678", "niks@gmail.com", "МГУ ВМК", "Администратор" ,"niks", "dsfhgjhkjl",1L);
        Workers gtw2 = new Workers(2L,"Юрьев Евгений Максимович", "Подольск" ,"89163572938" ,"uru@yandex.ru","НИУ ВШЭ Юридический факультет", "Юрист","uru","dfbhbdkfk",0L);
        Workers gtw3 = new Workers(3L,"Алексеев Максим Петрович","Москва","+79268579709","aleks@mail.ru","МГУ Юридический факультет","Юрист","aleks","lnddskbjn",0L);

        Workers wk = workersDAO.getById(2L);
        assertEquals(wk, gtw2);

        Services gts3 = new Services(3L, "Цифровое право", 2000L, "Защита в области информационных технологий");
        Services gts1 = new Services(1L,"Защита потребителей", 1000L,"Любые услуги, консультации по защите потребителей");
        Services gts2 = new Services(2L, "Недвижимость", 1000L, "Всё, что связано с недвижимостью");

        Services sr = servicesDAO.getById(3L);
        assertEquals(sr, gts3);

        List<Deal> new_deals = new ArrayList<>();
        Deal new_deal_a = new Deal(null, clientsDAO.getById(2L), workersDAO.getById(3L), servicesDAO.getById(1L), Date.valueOf("2023-01-18"), Date.valueOf("2023-03-16"), "fjlsd");
        Deal new_deal_b = new Deal(null, clientsDAO.getById(1L), workersDAO.getById(3L), servicesDAO.getById(2L) ,Date.valueOf("2023-02-21"), Date.valueOf("2023-02-27"), "dfj");

        new_deals.add(new_deal_a);
        new_deals.add(new_deal_b);
        dealDAO.saveCollection(new_deals);

        List<Deal> check_a = dealDAO.getAllDealsByValue("start", "2023-01-18");
        List<Deal> check_b = dealDAO.getAllDealsByValue("descr", "dfj");

        assertEquals(1, check_a.size());
        assertEquals(1, check_b.size());

        assertEquals(check_a.get(0).getId(), new_deal_a.getId());
        assertEquals(check_b.get(0).getId(), new_deal_b.getId());

        dealDAO.delete(new_deal_a);
        dealDAO.deleteById(new_deal_b.getId());

        List<Deal> deleted_a = dealDAO.getAllDealsByValue("start", "2023-01-18");
        List<Deal> deleted_b = dealDAO.getAllDealsByValue("descr", "dfj");

        assertEquals(deleted_a.size(), 0);
        assertEquals(deleted_b.size(), 0);
    }

    @Test
    void new_tests() {
        List<Deal> list_from_workers = dealDAO.getAllDealsByValue("worker", "1");
        assertEquals(1, list_from_workers.size());
        assertEquals(list_from_workers.get(0).getClient().getId(), 3 );
    }
    @Test
    void updating_test() {
        List<Deal> would_be_updated_list = dealDAO.getAllDealsByValue("descr", "недвижимость");
        assertEquals(1, would_be_updated_list.size());

        Clients gtc2 = new Clients(2L,"b", "Сидоров Семен Семенович", "sidorov@gmail.com", "s_sid", "sSqwerty");


        Workers gtw2 = new Workers(2L,"Юрьев Евгений Максимович", "Подольск" ,"89163572938" ,"uru@yandex.ru","НИУ ВШЭ Юридический факультет", "Юрист","uru","dfbhbdkfk",0L);

        Workers wk = workersDAO.getById(2L);
        assertEquals(wk, gtw2);

        Services gts2 = new Services(2L, "Недвижимость", 1000L, "Всё, что связано с недвижимостью");


        List<Deal> gt = new ArrayList<>();
        Deal original = new Deal(2L, clientsDAO.getById(2L), workersDAO.getById(2L), servicesDAO.getById(2L) ,Date.valueOf("2023-02-23"), Date.valueOf("2023-02-25"), "недвижимость");

        assertEquals(would_be_updated_list.get(0).getId(), original.getId());

        Deal would_be_updated = would_be_updated_list.get(0);
        would_be_updated.setStart(Date.valueOf("2022-02-26"));
        dealDAO.update(would_be_updated);

        List<Deal> after_updating_list = dealDAO.getAllDealsByValue("descr", "недвижимость");
        Deal updated = after_updating_list.get(0);
        assertEquals(updated.getId(), would_be_updated.getId());

    }
    @Test
    void simple_test() {
        Deal deals = dealDAO.getById(2L);
        assertEquals(2L, deals.getClient().getId());
        List<Deal> deals_by_id_range = dealDAO.getAllDealsByColRange("id", "1", "3");
        assertEquals(deals_by_id_range.size(), 3);
    }




}