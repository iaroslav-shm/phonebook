package org.shmorgunov.phonebook.service;

import lombok.AllArgsConstructor;
import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneBookService {

    private PhoneBookRepository bookRepository;

    public List<Record> listAllRecords() {
        return (List<Record>) bookRepository.findAll();
    }

    public void saveRecord(Record std) {
        bookRepository.save(std);
    }

    public Record findRecordById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteRecord(long id) {
        bookRepository.deleteById(id);
    }

}
