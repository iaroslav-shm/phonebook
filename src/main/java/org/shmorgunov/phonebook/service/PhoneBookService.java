package org.shmorgunov.phonebook.service;

import lombok.AllArgsConstructor;
import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneBookService {

    private PhoneBookRepository bookRepository;

    public List<PhoneRecord> listAllPhoneRecords() {
        return (List<PhoneRecord>) bookRepository.findAll();
    }

    public void saveRecord(PhoneRecord phoneRecord) {
        bookRepository.save(phoneRecord);
    }

    public PhoneRecord findPhoneRecordById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deletePhoneRecord(long id) {
        bookRepository.deleteById(id);
    }

}
