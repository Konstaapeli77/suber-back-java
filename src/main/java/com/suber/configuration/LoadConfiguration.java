package com.suber.configuration;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.repository.CompanyRepository;
import com.suber.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, CompanyRepository companyRepository) {

        return args -> {
            if (personRepository.findByLastname("Baggins").size() < 1) {
                log.info("Preloading " + personRepository.save(new Person("Bilbo", "Baggins")));
                log.info("Preloading " + personRepository.save(new Person("Frodo", "Baggins")));
                log.info("Preloading " + companyRepository.save(new Company("Tieto Oyj", "1962361-1")));
                log.info("Preloading " + companyRepository.save(new Company("Eficode Oy", "1971814-3")));
            }
        };
    }

}