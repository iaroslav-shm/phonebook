package org.shmorgunov.phonebook.data;

import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneBookRepository extends CrudRepository<PhoneRecord, Long> {
}
