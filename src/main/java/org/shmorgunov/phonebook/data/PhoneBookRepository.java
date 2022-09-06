package org.shmorgunov.phonebook.data;

import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneRecord, Long> {
}
