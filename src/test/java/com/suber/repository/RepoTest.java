package com.suber.repository;

import com.suber.data.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class RepoTest {

    @Autowired
    CompanyRepository repository;

    @Test
    void testDatabase() {
        /*
        repository.save(new Company("Korkki Oy", "12345-2"));

        List<Company> companies = repository.findByName("Korkki Oy");

        System.out.println("size:" + companies);
*/
    }


}
