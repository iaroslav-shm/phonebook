package org.shmorgunov.phonebook;

import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class PhonebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader( PhoneBookRepository bookRepository) {
        return args -> {
            bookRepository.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
            bookRepository.save(new PhoneRecord(1L,"Iron man","111", LocalDateTime.now()));
            bookRepository.save(new PhoneRecord(2L,"Thor","222", LocalDateTime.now()));
            bookRepository.save(new PhoneRecord(3L,"Black widow","333", LocalDateTime.now()));
            bookRepository.save(new PhoneRecord(4L,"Scarlet Witch","555", LocalDateTime.now()));
            bookRepository.save(new PhoneRecord(5L,"Top Gun Maverick","777", LocalDateTime.now()));
        };
    }

}
