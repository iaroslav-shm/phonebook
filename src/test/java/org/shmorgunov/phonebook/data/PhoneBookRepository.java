package org.shmorgunov.phonebook.data;

import org.shmorgunov.phonebook.domain.PhoneBook;
import org.springframework.data.repository.CrudRepository;


public interface PhoneBookRepository extends CrudRepository<PhoneBook, Long> {
}
