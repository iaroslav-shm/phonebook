package org.shmorgunov.phonebook;

import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PhonebookApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(PhoneBookRepository bookRepository) {
        return args -> {
            bookRepository.deleteAll();
            bookRepository.save(new PhoneRecord("Иван Петров", "+79119581931"));
            bookRepository.save(new PhoneRecord("Роберт Игнатьев", "79589582244"));
            bookRepository.save(new PhoneRecord("Jane Dawson", "88126777102"));
            bookRepository.save(new PhoneRecord("Bob Marley", "+74212566400"));
            bookRepository.save(new PhoneRecord("Жанна Григорьева", "+89112001354"));
        };
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
