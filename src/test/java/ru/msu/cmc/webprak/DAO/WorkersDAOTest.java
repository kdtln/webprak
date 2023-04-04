package ru.msu.cmc.webprak.DAO;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Workers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
1,Николаев Пётр Никифорович,Москва,+79150879678,niks@gmail.com,МГУ ВМК,Администратор,niks,dsfhgjhkjl,1
2,Юрьев Евгений Максимович,Подольск,89163572938,uru@yandex.ru,НИУ ВШЭ Юридический факультет,Юрист,uru,dfbhbdkfk,0
3,Алексеев Максим Петрович,Москва,+79268579709,aleks@mail.ru,МГУ Юридический факультет,Юрист,aleks,lnddskbjn,0

 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class WorkersDAOTest {
    @Autowired
    WorkersDAO workersDAO;

    @Test
    void test_get_by_name() {
        Workers gt = new Workers(1L, "Николаев Пётр Никифорович", "Москва", "+79150879678", "niks@gmail.com", "МГУ ВМК", "Администратор" ,"niks", "dsfhgjhkjl",1L);
        List<Workers> res = workersDAO.getAllWorkersByValue("name", "Николаев Пётр Никифорович");
        assertEquals(1, res.size());
        assertEquals(res.get(0), gt);
        res = workersDAO.getAllWorkersByValue("phone", "+79150879678");
        assertEquals(1, res.size());
        assertEquals(res.get(0), gt);

        res = workersDAO.getAllWorkersByValue("is_admin", "1");
        assertEquals(1, res.size());
        assertEquals(res.get(0), gt);
    }
}